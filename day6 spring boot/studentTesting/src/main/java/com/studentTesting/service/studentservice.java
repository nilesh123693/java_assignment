package com.studentTesting.service;

import com.studentTesting.entitty.student;
import com.studentTesting.repository.studentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class studentservice {
    @Autowired
    private studentRepository studentrepository;

    public List<student> getAllStudent() {
        return this.studentrepository.findAll();
    }
    public student getStudent(int id) {
        if(studentrepository.existsById(id)) {
            return this.studentrepository.findById(id).get();
        }
        return new student();
    }
    public String addStudent(student st) {
        if(studentrepository.existsById(st.getId())) {
            return "student already present with this id";
        }
        else {
            studentrepository.save(st);
            return "student added successfully";
        }
    }
    public String updateStudent(student st, int id) {
        if(studentrepository.existsById(id)==false) {

            return "student not found";
        }
        studentrepository.save(st);
        return "student data updated";

    }
    public String deleteStudent(int id) {
        if(studentrepository.existsById(id)) {
            studentrepository.deleteById(id);
            return "student record deleted";
        }
        return "student does not exist with this id";
    }


}
