package com.studentTesting.repository;

import com.studentTesting.entitty.student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface studentRepository extends JpaRepository<student, Integer> {

}