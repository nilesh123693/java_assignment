package com.studentTesting.controller;

import com.studentTesting.entitty.student;
import com.studentTesting.service.studentservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private studentservice studentService;
    @GetMapping("/getAll")
    public List<student> getAllStudent() {

        return studentService.getAllStudent();
    }
    @GetMapping("/getById/{id}")
    public student getStudent(@PathVariable("id") int id) {

        return studentService.getStudent(id);
    }
    @PostMapping("/addStudent")
    public String addStudent(@RequestBody student st) {

        return studentService.addStudent(st);
    }

    @PutMapping("/UpdateStudent/{id}")
    public String updateStudent(@RequestBody student st, @PathVariable("id") int id) {
        return studentService.updateStudent(st,id);
    }

    @DeleteMapping("/DeleteStudent/{id}")
    public String deleteStudent(@PathVariable("id") int id) {

        return this.studentService.deleteStudent(id);
    }


}