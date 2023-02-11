package com.studentTesting;

import com.studentTesting.entitty.student;
import com.studentTesting.repository.studentRepository;
import com.studentTesting.service.studentservice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class studentserviceTests {
    @Autowired
    private studentservice stservice;

    @Mock
    studentRepository studentrepository;

    @Test
    void testGetStudent() {
        student st = new student(1,"nilesh","prajapat","khargone");
        Mockito.when(studentrepository.findById(Mockito.anyInt())).thenReturn(Optional.of(st));
        student student1 = stservice.getStudent(1);
        Assertions.assertNotNull(student1);
    }
    @Test
    void testGelAllStudent() {
        List<student> list = new ArrayList<>();
        student student1 = new student(1,"abc","xyz","india");
        student student2 = new student(2,"tarun","mno","khargone");
        list.add(student1);
        list.add(student2);
        Mockito.when(studentrepository.findAll()).thenReturn(list);
        List<student> list1 = studentrepository.findAll();
        Assertions.assertNotNull(list);
        Assertions.assertEquals(2,list1.size());
    }
    @Test
    void testAddStudentWhenNotPresent() {
        student st = new student(1,"nilesh","prajapat","khargone");
        Mockito.when(studentrepository.save(st)).thenReturn(st);
        String str = stservice.addStudent(st);
        Assertions.assertEquals(str,"student added successfully");
    }
    @Test
    void testUpdateStudent() {
        student st = new student(1,"nilesh","prajapat","khargone");
        studentrepository.save(st);

        Mockito.when(studentrepository.existsById(1)).thenReturn(true);
        String str = stservice.updateStudent(st,5);
        Assertions.assertEquals(str,"student not found");
        Assertions.assertNotNull(str);
    }
    @Test
    void testUpdateWhenPresent() {
        student st = new student(1,"abc","xyz","khargone");
        Mockito.when(studentrepository.save(Mockito.any(student.class))).thenReturn(st);
        String str = stservice.updateStudent(st,1);
        Assertions.assertNotEquals(str,"student data updated");
        Assertions.assertNotNull(str);
    }

    @Test
    void testDeleteStudent() {
        student st = new student(1,"nilesh","prajapat","khargone");
        studentrepository.save(st);
        Mockito.when(studentrepository.existsById(Mockito.anyInt())).thenReturn(true);
        String str = stservice.deleteStudent(3);
        Assertions.assertNotNull(str);
        Assertions.assertEquals(str,"student does not exist with this id");
    }



}
