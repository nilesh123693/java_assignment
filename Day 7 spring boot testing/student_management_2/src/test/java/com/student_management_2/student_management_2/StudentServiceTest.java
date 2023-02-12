package com.student_management_2.student_management_2;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.student_management_2.student_management_2.Entity.Student;
import com.student_management_2.student_management_2.repository.StudentRepository;
import com.student_management_2.student_management_2.service.StudentService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StudentServiceTest {
    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    Student student1 = new Student(1, "tarun","dhamande","khargone","abc@gmail.com","1234",21,"btech");
    Student student2 = new Student(1, "tarun","joshi","khargone","abc@gmail.com","1234",24,"btech");

    @Test
    public void testGetStudentById_Success() {
        Integer studentId = 1;
        Student expectedStudent = new Student(studentId, "tarun","dhamande","khargone","abc@gmail.com","1234",21,"btech");
        when(studentRepository.findById(studentId)).thenReturn(Optional.of(expectedStudent));

        Student actualStudent = studentService.getStudentById(studentId);

        Assertions.assertEquals(expectedStudent, actualStudent);
    }
    @Test
    public void testGetStudentById_NotFound() {
        Integer studentId = 1;
        Student expectedStudent = new Student(studentId, "tarun","dhamande","khargone","abc@gmail.com","1234",21,"btech");
        when(studentRepository.findById(2)).thenReturn(Optional.empty());
        Student actualStudent = studentService.getStudentById(2);
        Assertions.assertNull(actualStudent);

    }

    @Test
    public void testGetAllStudent() {
        List<Student> list = new ArrayList<>();
        list.add(student1);
        list.add(student2);
        Mockito.when(studentRepository.findAll()).thenReturn(list);
        List<Student> list1 = studentService.getAllStudent();
        Assertions.assertEquals(2,list1.size());
        Assertions.assertNotNull(list1);
        Assertions.assertEquals(student1.getFname(),list1.get(0).getFname());

    }

    @Test
    public void testGetByEmail() {
        Mockito.when(studentRepository.findByEmail(student1.getEmail())).thenReturn(Optional.of(student1));
        Student student = studentService.getStudentByEmail(student1.getEmail());
        Assertions.assertEquals(student,student1);
    }
    @Test
    public void testGetByEmail_NotFound() {
        Mockito.when(studentRepository.findByEmail("ttt@gmail.com")).thenReturn(Optional.empty());
        Student student = studentService.getStudentByEmail("ttt@gmial.com");
        Assertions.assertEquals(student,null);
    }

    @Test
    public void testGetAllStudentByFname() {
        List<Student> list = new ArrayList<>();
        list.add(student1);
        list.add(student2);
        Mockito.when(studentRepository.findAllByFname("tarun")).thenReturn(list);
        List<Student> studentList = studentService.getAllStudentByFname("tarun");
        Assertions.assertEquals(2,studentList.size());
        Assertions.assertEquals(list,studentList);

    }

    @Test
    public void testAddStudent_Success() {
        Mockito.when(studentRepository.existsById(student1.getId())).thenReturn(false);
        Mockito.when(studentRepository.existsByEmail(student1.getEmail())).thenReturn(false);
        String str = studentService.addStudent(student1);
        Assertions.assertEquals("student data added successfully",str);
        verify(studentRepository).save(student1);
    }

    @Test
    public void testAddStudent_NotSuccess() {
        Mockito.when(studentRepository.existsById(student1.getId())).thenReturn(true);
        Mockito.when(studentRepository.existsByEmail(student1.getEmail())).thenReturn(true);
        String str = studentService.addStudent(student1);
        Assertions.assertEquals(null,str);
        verify(studentRepository,never()).save(student1);

    }

    @Test
    public void testUpdateStudent_Success() {

        Mockito.when(studentRepository.existsById(student1.getId())).thenReturn(true);
        String str = studentService.updateStudentById(student1.getId(),student2);
        Assertions.assertEquals("student data updated successfully",str);
        verify(studentRepository).save(student2);
    }

    @Test
    public void testUpdateStudent_NotSuccess() {
        Mockito.when(studentRepository.existsById(student1.getId())).thenReturn(false);
        String str = studentService.updateStudentById(student1.getId(),student2);
        Assertions.assertEquals(null, str);
        verify(studentRepository,never()).save(student2);

    }

    @Test
    public void testdeleteStudentById_Success() {
        Mockito.when(studentRepository.existsById(student1.getId())).thenReturn(true);
        String str = studentService.deleteStudentById(student1.getId());
        Assertions.assertEquals("student data deleted successfully",str);
        verify(studentRepository).deleteById(student1.getId());
    }
    @Test
    public void testdeleteStudentById_NotSuccess() {
        Mockito.when(studentRepository.existsById(student1.getId())).thenReturn(false);
        String str = studentService.deleteStudentById(student1.getId());
        Assertions.assertEquals(null,str);
        verify(studentRepository,never()).deleteById(student1.getId());
    }
    @Test
    public void testCountByAge() {
        Mockito.when(studentRepository.countByAge(student1.getAge())).thenReturn(1L);
        Long res = studentService.countStudentsByAge(student1.getAge());
        Assertions.assertEquals(1L,res);
    }
}
