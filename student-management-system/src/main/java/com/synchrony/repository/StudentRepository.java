package com.synchrony.repository;

import com.synchrony.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByName(String name);

    @Transactional
    void deleteByName(String name);
    boolean existsByName(String name);

//    boolean deleteByName1(String name);
}