package com.synchrony.utils;

import com.synchrony.dtos.StudentDTO;
import com.synchrony.dtos.StudentResponseDTO;
import com.synchrony.entity.Student;

public class SystemUtilities {

    public static StudentResponseDTO convertToResponseDTO(Student student) {
        return  StudentResponseDTO.builder()
                .name(student.getName())
                .age(student.getAge())
                .studentClass(student.getStudentClass())
                .phoneNumber(student.getPhoneNumber())
                .build();
    }

    public static Student convertToEntity(StudentDTO dto) {
        Student student = new Student();
        student.setName(dto.getName());
        student.setAge(dto.getAge());
        student.setStudentClass(dto.getStudentClass());
        student.setPhoneNumber(dto.getPhoneNumber());
        return student;
    }
}
