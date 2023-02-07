package com.student_management_2.student_management_2.repository;

import com.student_management_2.student_management_2.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {


    boolean existsByEmail(String email);

    List<Student> findAllByFname(String name);


    List<Student> findAllByAge(Integer age);

    Student findAllByEmail(String email);

    Long countByAge(Integer age);
}
