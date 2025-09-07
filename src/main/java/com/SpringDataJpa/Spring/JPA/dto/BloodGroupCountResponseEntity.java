package com.SpringDataJpa.Spring.JPA.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BloodGroupCountResponseEntity {
    private String bloodGroup;
    private Long count;
}
