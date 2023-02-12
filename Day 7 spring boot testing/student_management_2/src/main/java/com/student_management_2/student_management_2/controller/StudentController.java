package com.student_management_2.student_management_2.controller;

import com.student_management_2.student_management_2.Entity.Student;
import com.student_management_2.student_management_2.repository.StudentRepository;
import com.student_management_2.student_management_2.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.SimpleTimeZone;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/getAllStudent")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> list = studentService.getAllStudent();
        if(list.size()==0) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.of(Optional.of(list));
    }
    @GetMapping("/getById/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") Integer id) {
        Student student = studentService.getStudentById(id);
        if(student==null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(student));
    }
    @GetMapping("/getByAge/{age}")
    public ResponseEntity<List<Student>> getAllByAge(@PathVariable("age") Integer age) {
        List<Student> list = studentService.getAllStudentByAge(age);
        if(list.size()==0) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.of(Optional.of(list));
    }
    @GetMapping("/getByEmail/{email}")
    public ResponseEntity<Student> getStudentByEmail(@PathVariable("email") String email) {
        Student student = studentService.getStudentByEmail(email);
        if(student==null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(student));
    }
    @GetMapping("/getByFname/{fname}")
    public ResponseEntity<List<Student>> getAllByFname(@PathVariable("fname") String fname) {
        List<Student> list = studentService.getAllStudentByFname(fname);
        if(list.size()==0) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.of(Optional.of(list));
    }

    @GetMapping("/countByAge/{age}")
    public ResponseEntity<Long> countAllStudentByAge(@PathVariable("age") Integer age) {
        Long res = studentService.countStudentsByAge(age);
        if(res==0L) {
            return new ResponseEntity<Long>(0L,HttpStatus.NO_CONTENT);
        }
        return  new ResponseEntity<Long>(res,HttpStatus.OK);
    }

    @PostMapping("/add-student")
    public ResponseEntity<String> addStudent(@RequestBody Student student) {
        String str  = studentService.addStudent(student);
        if(str==null) {
           return new ResponseEntity<String>("id or email already  exists", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>("student data added", HttpStatus.OK);
    }

    @PutMapping("/update-student/{id}")
    public ResponseEntity<String> updateStudentById(@PathVariable("id") Integer id, @RequestBody Student student) {
        String str = studentService.updateStudentById(id, student);
        if(str==null) {
            return  new ResponseEntity<String>("failed to update", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>("data updated sucessfully",HttpStatus.OK);
    }


    @DeleteMapping("/delete-student/{id}")
    public ResponseEntity<String> deleteStudentById(@PathVariable("id") Integer id) {
        String str = studentService.deleteStudentById(id);
        if(str==null) {
            return new ResponseEntity<String>("id does not exit",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>("student record deleted sucessfully",HttpStatus.OK);
    }


}
