package com.synchrony.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponseDTO {
    private String name;
    private Integer age;
    private String studentClass;
    private Long phoneNumber;
}
