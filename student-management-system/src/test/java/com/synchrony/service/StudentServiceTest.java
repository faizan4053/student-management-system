package com.synchrony.service;

import com.synchrony.dtos.StudentResponseDTO;
import com.synchrony.entity.Student;
import com.synchrony.exceptions.StudentNotExistsException;
import com.synchrony.repository.StudentRepository;
import com.synchrony.utils.SystemUtilities;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;

class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }



    @Test
    void testGetStudentByNameExists() {
        String name = "John";
        Student student = new Student(1l,name, 20, "10th Grade", 1234567890L);
        when(repository.findByName(name)).thenReturn(Optional.of(student));

        StudentResponseDTO responseDTO = SystemUtilities.convertToResponseDTO(student);

        StepVerifier.create(studentService.getStudentByName(name))
                .expectNextMatches(dto -> dto.getName().equals(name))
                .verifyComplete();

        verify(repository, times(1)).findByName(name);
    }

    @Test
    void testGetAllStudents() {
        // Create mock data
        List<Student> students = Arrays.asList(
                new Student(1L, "John", 20, "10th Grade", 1234567890L),
                new Student(2L, "Jane", 22, "12th Grade", 1234567890L)
        );

        // Mock repository
        when(repository.findAll()).thenReturn(students);

        List<StudentResponseDTO> responseDTOs = students.stream()
                .map(res->SystemUtilities.convertToResponseDTO(res))
                .collect(Collectors.toList());



        // Verify behavior
        StepVerifier.create(studentService.getAllStudents())
                .expectNextMatches(dto -> dto.getName().equals("John"))
                .expectNextMatches(dto -> dto.getName().equals("Jane"))
                .verifyComplete();

        verify(repository, times(1)).findAll();
    }


    @Test
    void testGetStudentByNameNotExists() {
        String name = "Unknown";
        when(repository.findByName(name)).thenReturn(Optional.empty());

        StepVerifier.create(studentService.getStudentByName(name))
                .expectErrorMatches(throwable -> throwable instanceof StudentNotExistsException && throwable.getMessage().contains(name))
                .verify();

        verify(repository, times(1)).findByName(name);
    }

}
