package com.SpringDataJpa.Spring.JPA;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class SpringJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringJpaApplication.class, args);
	}

}

//@SpringBootApplication
//@EnableJpaRepositories("com.SpringDataJpa.Spring.JPA.repository")
//@EntityScan("com.SpringDataJpa.Spring.JPA.entity")
//public class SpringJpaApplication {
//	public static void main(String[] args) {
//		SpringApplication.run(SpringJpaApplication.class, args);
//	}
//}

