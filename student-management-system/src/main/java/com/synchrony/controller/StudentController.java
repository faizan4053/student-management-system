package com.synchrony.controller;

import com.synchrony.dtos.StudentDTO;
import com.synchrony.dtos.StudentResponseDTO;
import com.synchrony.exceptions.SuccessResponse;
import com.synchrony.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@CrossOrigin(origins = "*")
public class StudentController {
    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping
    public Flux<StudentResponseDTO> getAllStudents() {
        return service.getAllStudents();
    }

    @GetMapping("/{name}")
    public Mono<StudentResponseDTO> getStudentByName(@PathVariable String name) {
        return service.getStudentByName(name);
    }

    @PostMapping
    public StudentResponseDTO addStudent(@Valid @RequestBody StudentDTO studentDTO) {
        return service.saveStudent(studentDTO);
    }

    @PutMapping("/{name}")
    public Mono<StudentResponseDTO> updateStudent(@PathVariable String name, @RequestBody StudentDTO studentDTO) {
        return service.updateStudent(name, studentDTO);
    }

    @DeleteMapping("/{name}")
    public Mono<SuccessResponse> deleteStudent(@PathVariable String name) {
        return service.deleteStudent(name);
    }

}