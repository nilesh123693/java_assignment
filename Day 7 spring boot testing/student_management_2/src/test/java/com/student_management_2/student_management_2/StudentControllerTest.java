package com.student_management_2.student_management_2;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.student_management_2.student_management_2.Entity.Student;
import com.student_management_2.student_management_2.controller.StudentController;
import com.student_management_2.student_management_2.service.StudentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class StudentControllerTest {
    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    private Student student1;
    private Student student2;

    @Before
    public void setUp() {
        student1 = new Student(1, "nilesh", "prajapat","khargone","abc@gmail.com","1234",21,"btech");
        student2 = new Student(1, "sam", "joshi","indore","xyz@gmail.com","1234",21,"btech");
    }

    @Test
    public void testGetStudentByIdSuccess() {
        when(studentService.getStudentById(1)).thenReturn(student1);
        ResponseEntity<Student> response = studentController.getStudentById(1);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(student1, response.getBody());
    }
    @Test
    public void testGetStudentByIdNotFound() {
        when(studentService.getStudentById(2)).thenReturn(null);
        ResponseEntity<Student> response = studentController.getStudentById(2);
        Assertions.assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());

    }

    @Test
    public void testGetAllStudent() {
        List<Student> list  = new ArrayList<>();
        list.add(student1);
        list.add(student2);
        when(studentService.getAllStudent()).thenReturn(list);
        ResponseEntity<List<Student>> listResponseEntity = studentController.getAllStudents();
        Assertions.assertEquals(HttpStatus.OK,listResponseEntity.getStatusCode());
        Assertions.assertEquals(list, listResponseEntity.getBody());
        Assertions.assertEquals(2,listResponseEntity.getBody().size());
    }

    @Test
    public void testGetAllStudent2() {
        Mockito.when(studentService.getAllStudent()).thenReturn(new ArrayList<>());
        ResponseEntity<List<Student>> listResponseEntity = studentController.getAllStudents();
        Assertions.assertEquals(HttpStatus.NO_CONTENT,listResponseEntity.getStatusCode());
    }
    @Test
    public void testGetAllByAge_Success() {
        List<Student> list  = new ArrayList<>();
        list.add(student1);
        list.add(student2);
        Mockito.when(studentService.getAllStudentByAge(student1.getAge())).thenReturn(list);
        ResponseEntity<List<Student>> listResponseEntity = studentController.getAllByAge(student1.getAge());
        Assertions.assertEquals(HttpStatus.OK,listResponseEntity.getStatusCode());
        Assertions.assertEquals(2,listResponseEntity.getBody().size());

    }

    @Test
    public void testGetAllByAge_NotSuccess() {
        Mockito.when(studentService.getAllStudentByAge(23)).thenReturn(new ArrayList<>());
        ResponseEntity<List<Student>> listResponseEntity = studentController.getAllByAge(23);
        Assertions.assertEquals(HttpStatus.NO_CONTENT,listResponseEntity.getStatusCode());
        Assertions.assertNull(listResponseEntity.getBody());
    }

    @Test
    public void testGetStudentByEmailSuccess() {
        Mockito.when(studentService.getStudentByEmail(student1.getEmail())).thenReturn(student1);
        ResponseEntity<Student> response = studentController.getStudentByEmail(student1.getEmail());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(student1, response.getBody());
    }
    @Test
    public void testGetStudentByEmailNotFound() {
        when(studentService.getStudentByEmail("ttt@gmail.com")).thenReturn(null);
        ResponseEntity<Student> response = studentController.getStudentByEmail("ttt@gmail.com");
        Assertions.assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());

    }

    @Test
    public void testGetAllByFname_Success() {
        List<Student> list  = new ArrayList<>();
        list.add(student1);
        list.add(student2);
        Mockito.when(studentService.getAllStudentByFname(student1.getFname())).thenReturn(list);
        ResponseEntity<List<Student>> listResponseEntity = studentController.getAllByFname(student1.getFname());
        Assertions.assertEquals(HttpStatus.OK,listResponseEntity.getStatusCode());
        Assertions.assertEquals(2,listResponseEntity.getBody().size());

    }

    @Test
    public void testGetAllByFname_NotSuccess() {
        Mockito.when(studentService.getAllStudentByFname("tarun")).thenReturn(new ArrayList<>());
        ResponseEntity<List<Student>> listResponseEntity = studentController.getAllByFname("tarun");
        Assertions.assertEquals(HttpStatus.NO_CONTENT,listResponseEntity.getStatusCode());
        Assertions.assertNull(listResponseEntity.getBody());
    }

    @Test
    public void testCountByAge() {
        Mockito.when(studentService.countStudentsByAge(student1.getAge())).thenReturn(1L);
        ResponseEntity<Long> res = studentController.countAllStudentByAge(student1.getAge());
        Assertions.assertEquals(HttpStatus.OK,res.getStatusCode());
        Assertions.assertEquals(1L, res.getBody());

    }

    @Test
    public void testAddStudent_Success() {
        Mockito.when(studentService.addStudent(student1)).thenReturn("student data added");
        ResponseEntity<String> res = studentController.addStudent(student1);
        Assertions.assertEquals(HttpStatus.OK,res.getStatusCode());
        Assertions.assertEquals("student data added",res.getBody());

    }
    @Test
    public void testAddStudent_NotSuccess() {
        Mockito.when(studentService.addStudent(student1)).thenReturn(null);
        ResponseEntity<String> res = studentController.addStudent(student1);
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, res.getStatusCode());
        Assertions.assertEquals("id or email already  exists", res.getBody());

    }

    @Test
    public void testUpdateStudent_Success() {
        Mockito.when(studentService.updateStudentById(student1.getId(),student2)).thenReturn("data updated successfully");
        ResponseEntity<String> res = studentController.updateStudentById(student1.getId(),student2);
        Assertions.assertEquals(HttpStatus.OK,res.getStatusCode());
        Assertions.assertEquals("data updated sucessfully", res.getBody());
    }

    @Test
    public void testUpdateStudent_NotSuccess() {
        Mockito.when(studentService.updateStudentById(student1.getId(),student2)).thenReturn(null);
        ResponseEntity<String> res = studentController.updateStudentById(student1.getId(),student2);
        Assertions.assertEquals(HttpStatus.NOT_FOUND,res.getStatusCode());
        Assertions.assertEquals("failed to update", res.getBody());
    }

    @Test
    public void testDeleteStudent_Success() {
        Mockito.when(studentService.deleteStudentById(student1.getId())).thenReturn("data deleted");
        ResponseEntity<String> res = studentController.deleteStudentById(student1.getId());
        Assertions.assertEquals(HttpStatus.OK,res.getStatusCode());
        Assertions.assertEquals("student record deleted sucessfully",res.getBody());
    }

    @Test
    public void testDeleteStudent_NotSuccess() {
        Mockito.when(studentService.deleteStudentById(6)).thenReturn(null);
        ResponseEntity<String> res = studentController.deleteStudentById(6);
        Assertions.assertEquals(HttpStatus.NOT_FOUND,res.getStatusCode());
        Assertions.assertEquals("id does not exit",res.getBody());
    }






    }
