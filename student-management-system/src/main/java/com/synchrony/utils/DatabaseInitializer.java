package com.synchrony.utils;

import com.synchrony.dtos.StudentDTO;
import com.synchrony.entity.Student;
import com.synchrony.repository.StudentRepository;
import com.synchrony.service.StudentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    private final StudentRepository studentRepository;

    public DatabaseInitializer(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // Check if the database already has data
        if (studentRepository.count() == 0) {
            // Array of 20 student names
            String[] studentNames = {
                    "John Doe", "Jane Smith", "Michael Johnson", "Emily Davis",
                    "Chris Brown", "Sarah Wilson", "Matthew Moore", "Olivia Taylor",
                    "David Anderson", "Sophia Thomas", "James Jackson", "Emma White",
                    "Ethan Harris", "Isabella Martin", "Daniel Thompson", "Mia Garcia",
                    "Alexander Clark", "Charlotte Lee", "Logan Walker", "Amelia Hall"
            };

            // Array of corresponding phone numbers
            Long[] phoneNumbers = {
                    1234567890L, 1234567891L, 1234567892L, 1234567893L,
                    1234567894L, 1234567895L, 1234567896L, 1234567897L,
                    1234567898L, 1234567899L, 9876543210L, 9876543211L,
                    9876543212L, 9876543213L, 9876543214L, 9876543215L,
                    9876543216L, 9876543217L, 9876543218L, 9876543219L
            };

            // Add 20 students to the database
            for (int i = 0; i < studentNames.length; i++) {
                studentRepository.save(
                        Student.builder()
                                .name(studentNames[i])
                                .age(18 + (i % 5)) // Random age between 18 and 22
                                .studentClass("Class " + (i % 10 + 1)) // Classes 1 to 10
                                .phoneNumber(phoneNumbers[i]) // Unique phone numbers
                                .build()
                );
                Optional<Student> student=studentRepository.findByName(studentNames[i]);
                System.out.println(student.get().getName());
            }
            System.out.println("Database has been initialized with 20 students.");
        } else {
            System.out.println("Database already contains data. Initialization skipped.");
        }
    }
}

