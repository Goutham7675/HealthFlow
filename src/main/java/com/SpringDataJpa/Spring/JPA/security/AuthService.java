package com.SpringDataJpa.Spring.JPA.security;


import com.SpringDataJpa.Spring.JPA.dto.LoginRequestDto;
import com.SpringDataJpa.Spring.JPA.dto.LoginResponseDto;
import com.SpringDataJpa.Spring.JPA.dto.SignupResponseDto;
import com.SpringDataJpa.Spring.JPA.dto.SignUpRequestDto;
import com.SpringDataJpa.Spring.JPA.entity.Patient;
import com.SpringDataJpa.Spring.JPA.entity.type.AuthProviderType;
import com.SpringDataJpa.Spring.JPA.entity.type.RoleType;
import com.SpringDataJpa.Spring.JPA.repository.PatientRepository;
import com.SpringDataJpa.Spring.JPA.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import com.SpringDataJpa.Spring.JPA.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final PatientRepository patientRepository;

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword()
            )
        );

        User user = (User) authentication.getPrincipal();

        String token = authUtil.generateAccessToken(user);

        return new LoginResponseDto(token, user.getId());
    }

    public User signUpInternal(SignUpRequestDto signUpRequestDto, AuthProviderType authProviderType, String providerId) {
        User user = userRepository.findByUsername(signUpRequestDto.getUsername()).orElse(null);
        if(user != null){
            throw new IllegalArgumentException("Username already exists");
        }
//        user = userRepository.save(User.builder()
//                .username(signUpRequestDto.getUsername())
////                .password(signUpRequestDto.getPassword() == null ? null : passwordEncoder.encode(signUpRequestDto.getPassword()))
//                .build()
//        );
        user = User.builder()
            .username(signUpRequestDto.getUsername())
            .providerId(providerId)
            .providerType(authProviderType)
//            .roles(Set.of(RoleType.PATIENT))
            .roles(signUpRequestDto.getRoles())
            .build();

        if(authProviderType == AuthProviderType.EMAIL){
            user.setPassword(passwordEncoder.encode(signUpRequestDto.getPassword()));
        }

        user =  userRepository.save(user);
        Patient patient = Patient.builder()
                .name(signUpRequestDto.getName())
                .email(signUpRequestDto.getUsername())
                .user(user)
                .build();
        patientRepository.save(patient);
//        return userRepository.save(user);
        return user;
    }


    // Login Controller
    public SignupResponseDto signup(SignUpRequestDto signupRequestDto) {
//        Optional<User> existingUser = userRepository.findByUsername(signupRequestDto.getUsername());
//        if(existingUser.isPresent()) throw new IllegalArgumentException("Username already exists");
//
//        User user = userRepository.save(User.builder()
//                .username(signupRequestDto.getUsername())
//                .password(passwordEncoder.encode(signupRequestDto.getPassword()))
//                .build()
//        );
//        User user = signup(signupRequestDto);
        User user = signUpInternal(signupRequestDto, AuthProviderType.EMAIL, null);

        return new SignupResponseDto(user.getId(), user.getUsername());
    }

    @Transactional
    public ResponseEntity<LoginResponseDto>
            handleOAuth2Loginrequest(OAuth2User oAuth2User, String registrationId) {

        /*
            1. fetch provideType and providerId
            2. save the provideId and providerType with user to avoid creating multiple accounts
                despite using same credentials
            3. if account exists then login directly
                else first signup and then login automatically
        */

        AuthProviderType providerType = authUtil.getAuthProviderTypeFromRegistrationId(registrationId);
        String providerId = authUtil.determineProviderIdFromOAuth2User(oAuth2User, registrationId);

        // to store in database

        User user = userRepository.findByProviderIdAndProviderType(providerId, providerType).orElse(null);
        String email = oAuth2User.getAttribute("email");
        String name  = oAuth2User.getAttribute("name");

        User emailUser = userRepository.findByUsername(email).orElse(null);
        if(user == null && emailUser == null) {
            String username = authUtil.determineUserNameFromOAuth2User(oAuth2User, registrationId, providerId);
            SignUpRequestDto signUpRequestDto = new SignUpRequestDto();
            signUpRequestDto.setUsername(username);
            signUpRequestDto.setPassword(null);
            signup(signUpRequestDto);

            user = signUpInternal(new SignUpRequestDto(username, null, name, Set.of(RoleType.PATIENT)), providerType, providerId);
            // fetch newly created user and attach provider details
            user = userRepository.findByUsername(username).orElseThrow();
            user.setProviderId(providerId);
            user.setProviderType(providerType);
            userRepository.save(user);
        } else if(user != null && email != null && !email.isBlank() && !email.equals(user.getUsername())) {
            user.setUsername(email);
            userRepository.save(user);
        } else if (user != null) {
            // user exists; proceed to generate token below
        } else {
            throw new BadCredentialsException("Email already registered with provider" + (emailUser != null ? emailUser.getProviderType() : ""));
        }
        LoginResponseDto  loginResponseDto = new LoginResponseDto(authUtil.generateAccessToken(user), user.getId());
        return ResponseEntity.ok(loginResponseDto);
    }
}
