package com.student_management_2.student_management_2.service;

import com.student_management_2.student_management_2.Entity.Student;
import com.student_management_2.student_management_2.repository.StudentRepository;
import org.apache.catalina.valves.StuckThreadDetectionValve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudent() {

        return studentRepository.findAll();
    }

    public Student getStudentById(Integer id) {
        if(studentRepository.existsById(id)) {
            return studentRepository.findById(id).get();
        }
        return null;
    }
    public List<Student> getAllStudentByAge(Integer age) {
        return studentRepository.findAllByAge(age);
    }
    public Student getStudentByEmail(String email) {
        if(studentRepository.existsByEmail(email)) {
            return studentRepository.findAllByEmail(email);
        }
        return null;
    }
    public List<Student> getAllStudentByFname(String name) {
        return studentRepository.findAllByFname(name);
    }

    public String addStudent(Student student) {
        if(studentRepository.existsById(student.getId())) {
            return null;
        }
        if(studentRepository.existsByEmail(student.getEmail())){
            return null;
        }
        studentRepository.save(student);
        return "student data added successfully";
    }

    public String updateStudentById(Integer id, Student student) {
        if(studentRepository.existsById(id) && student.getId()==id) {
            studentRepository.save(student);
            return "student data added successfully";
        }
        return null;
    }
    public String deleteStudentById(Integer id) {
        if(studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return "student data deleted successfully";
        }
        return null;
    }

    public Long countStudentsByAge(Integer age) {
        return studentRepository.countByAge(age);
    }
}
