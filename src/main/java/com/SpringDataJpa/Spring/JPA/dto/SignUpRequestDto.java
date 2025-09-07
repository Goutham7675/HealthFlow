package com.SpringDataJpa.Spring.JPA.dto;

import com.SpringDataJpa.Spring.JPA.entity.type.RoleType;
import lombok.Data;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
//@Getter
//@Setter
@AllArgsConstructor
@NoArgsConstructor
//@RequiredArgsConstructor
public class SignUpRequestDto {
    private String username;
    private String password;
    private String name;
    private Set<RoleType> roles = new HashSet<>();
}
