package com.synchrony.service;

import com.synchrony.dtos.StudentDTO;
import com.synchrony.dtos.StudentResponseDTO;
import com.synchrony.entity.Student;
import com.synchrony.exceptions.StudentNotExistsException;
import com.synchrony.exceptions.SuccessResponse;
import com.synchrony.repository.StudentRepository;
import com.synchrony.utils.SystemUtilities;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public Flux<StudentResponseDTO> getAllStudents() {
        return Flux.fromIterable(repository.findAll().stream()
                .map(student -> SystemUtilities.convertToResponseDTO(student))
                .collect(Collectors.toList()));
    }

    public Mono<StudentResponseDTO> getStudentByName(String name) {
        Optional<Student> student = repository.findByName(name);
        return student.map(s -> Mono.just(SystemUtilities.convertToResponseDTO(s))).orElse(Mono.error(new StudentNotExistsException("No student with name "+ name + " exists!")));
    }

    public StudentResponseDTO saveStudent(StudentDTO studentDTO) {
        Student student = SystemUtilities.convertToEntity(studentDTO);
        Student savedStudent = repository.save(student);
        return SystemUtilities.convertToResponseDTO(savedStudent);
    }

    public Mono<StudentResponseDTO> updateStudent(String name, StudentDTO updatedStudentDTO) {
        Optional<Student> existingStudent = repository.findByName(name);
        if (existingStudent.isPresent()) {
            Student student = existingStudent.get();
            student.setAge(updatedStudentDTO.getAge());
            student.setStudentClass(updatedStudentDTO.getStudentClass());
            student.setPhoneNumber(updatedStudentDTO.getPhoneNumber());
            Student updatedStudent = repository.save(student);
            return Mono.just(SystemUtilities.convertToResponseDTO(updatedStudent));
        }
        return Mono.error(new StudentNotExistsException("No student with name "+ name + " exists!"));
    }

    public Mono<SuccessResponse> deleteStudent(String name) {

        boolean checkExistence = repository.existsByName(name);
        if(repository.existsByName(name)){
            repository.deleteByName(name);
            return Mono.just(new SuccessResponse("Student with name " + name + " is deleted successfully !"));
        }
        return Mono.error(new StudentNotExistsException("No student with name "+ name + " exists for deletion!"));
    }

//    public Mono<Boolean> deleteStudent1(String name) {
//        boolean res=repository.deleteByName1(name);
//        return Mono.just(res);
//    }

}