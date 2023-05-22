package com.Rashmoni.Springbootlearning.controller;

import com.Rashmoni.Springbootlearning.entity.Department;
import com.Rashmoni.Springbootlearning.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService departmentService;

    private Department department;

    @BeforeEach
    void setUp() {
                 department = Department.builder()
                .departmentId(1L)
                .departmentName("IT")
                .departmentAddress("Kolkata")
                .departmentCode("IT-06")
                .build();
    }

    @Test
    void saveDepartment() throws Exception {
        Department Inputdepartment = Department.builder()
                .departmentCode("IT-06")
                .departmentAddress("Kolkata")
                .departmentName("IT")
                .build();

        Mockito.when(departmentService.saveDepartment(Inputdepartment))
                .thenReturn(department);

        mockMvc.perform(post("/departments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(" {\n" +
                                "        \"departmentName\": \"IT\",\n" +
                                "        \"departmentAddress\": \"Kolkata\",\n" +
                                "        \"departmentCode\": \"IT-06\"\n" +
                                "}"))
                .andExpect(status().isOk());
    }

    @Test
    void fetchDepartmentById() throws Exception {
        Mockito.when(departmentService.fetchDepartmentById(1L))
                .thenReturn(department);

        mockMvc.perform(get("/departments/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.departmentName").
                        value(department.getDepartmentName()));
    }
}