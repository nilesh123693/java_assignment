package com.example.demo.controller;

import com.example.demo.entity.student;
import com.example.demo.service.studentservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    private studentservice studentService;
    @GetMapping("/students")
    public List<student> getallstudent() {
        return studentService.getAllstudent();
    }
    @GetMapping("/student/{id}")
    public student getstudent(@PathVariable("id") int id) {
        return studentService.getstudent(id);
    }
    @PostMapping("/student")
    public String addstudent(@RequestBody student st) {
        return studentService.addstudent(st);
    }

    @PutMapping("/student/{id}")
    public String updatestudent(@RequestBody student st, @PathVariable("id") int id) {
        return studentService.updatestudent(st,id);
    }

    @DeleteMapping("/student/{id}")
    public String deletestudent(@PathVariable("id") int id) {
        return this.studentService.deletestudent(id);
    }


}
