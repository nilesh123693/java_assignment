package com.studentTesting;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.studentTesting.controller.StudentController;
import com.studentTesting.entitty.student;
import com.studentTesting.service.studentservice;
import jakarta.annotation.security.RunAs;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;


@RunWith(SpringRunner.class)
@WebMvcTest(value = StudentController.class)
public class StudentControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private studentservice studentservice;

    @Test
    public void testAddStudent() throws Exception {
        student st  = new student(1,"nilesh","prajapat","khargone");

        String inputInJson = this.mapToJson(st);
        String URI = "/student/addStudent";
        Mockito.when(studentservice.addStudent(Mockito.any(student.class))).thenReturn("data added successfully");
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(URI)
                .accept(MediaType.APPLICATION_JSON).content(inputInJson)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String outputInJson = response.getContentAsString();
        Assertions.assertThat(outputInJson).isEqualTo("data added successfully");

    }
    @Test
    public void testGetStudentById() throws  Exception {
        student st = new student(1,"nilesh","prajapat","khargone");
        Mockito.when(studentservice.getStudent(Mockito.anyInt())).thenReturn(st);
        String URI = "/student/getById/1";
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(URI)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String excepted = this.mapToJson(st);
        String output = result.getResponse().getContentAsString();
        Assertions.assertThat(output).isEqualTo(excepted);
    }
    @Test
    public void testGetAllStudent() throws  Exception {
        student student1 = new student(1,"tarun","dhamande","khargone");
        student student2 = new student(2,"sanu","yadav","khargone");
        List<student> list = new ArrayList<>();
        list.add(student1);
        list.add(student2);
        String URI = "/student/getAll";
        Mockito.when(studentservice.getAllStudent()).thenReturn(list);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(URI)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String output = result.getResponse().getContentAsString();
        String expected = this.mapToJson(list);
        Assertions.assertThat(output).isEqualTo(expected);
    }

    @Test
    public void testUpdateStudent() throws  Exception {
        student student1 = new student(1,"sam","joshi", "indore");
        String inputInJson = this.mapToJson(student1);
        String URI = "/student/UpdateStudent/1";
        Mockito.when(studentservice.updateStudent(Mockito.any(student.class),Mockito.anyInt())).thenReturn("student data updated successfully");
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put(URI)
                .accept(MediaType.APPLICATION_JSON)
                .content(inputInJson)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String output = response.getContentAsString();
        Assertions.assertThat(output).isEqualTo("student data updated successfully");
    }
    @Test
    public void testDeleteStudent() throws Exception {
        student student1 = new student(1,"ashish","rathod","mogargav");
        String  URI = "/student/DeleteStudent/1";
        Mockito.when(studentservice.deleteStudent(Mockito.anyInt())).thenReturn(student1.getFname());
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete(URI)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result  = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String output = response.getContentAsString();
        Assertions.assertThat(output).isEqualTo(student1.getFname());
    }

    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return  objectMapper.writeValueAsString(object);
    }

}
