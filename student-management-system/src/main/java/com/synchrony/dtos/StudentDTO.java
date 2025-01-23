package com.synchrony.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    private String name;
    private Integer age;
    private String studentClass;
    private Long phoneNumber;
}
