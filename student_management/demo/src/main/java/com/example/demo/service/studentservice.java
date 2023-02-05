package com.example.demo.service;

import com.example.demo.entity.student;
import com.example.demo.repository.studentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class studentservice {
    @Autowired
    private studentRepository studentrepository;

    public List<student> getAllstudent() {
        return this.studentrepository.findAll();
    }
    public student getstudent(int id) {
        if(studentrepository.existsById(id)) {
           return this.studentrepository.findById(id).get();
        }
        return null;
    }
    public String addstudent(student st) {
        studentrepository.save(st);
        return "student added sucessfully";
    }
    public String updatestudent(student st, int id) {
        if(studentrepository.existsById(id)) {
            studentrepository.save(st);
            return "student data updated";
        }
        return "student not found";
    }
    public String deletestudent(int id) {
        if(studentrepository.existsById(id)) {
            studentrepository.deleteById(id);
            return "student record deleted";
        }
        return "student does not exist with this id";
    }


}
