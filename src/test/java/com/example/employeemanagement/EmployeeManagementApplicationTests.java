package com.example.employeemanagement;

import com.example.employeemanagement.controller.EmpCompanyController;
import com.example.employeemanagement.entity.Employee;
import com.example.employeemanagement.repository.EmployeeRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.metamodel.mapping.ValueMapping;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
class EmployeeManagementApplicationTests {

    private static final String ENDPOINT_URL="/v1/empsave";

    @InjectMocks
    private EmpCompanyController empCompanyController;

    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private EmployeeRepository employeeRepository;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setup()
    {
        this.mockMvc= MockMvcBuilders.standaloneSetup(this.empCompanyController).build();
    }

    @Test
    public void createnewemployeeTest() throws Exception
    {
        Employee demoEmployee=new  Employee(1,"emp1",50000,"emp1@gmail.com",new Date(1999-01-01), 57001,24);
        when(employeeRepository.save(any())).thenReturn(demoEmployee);
        mockMvc.perform(MockMvcRequestBuilders
                        .post(ENDPOINT_URL)
                        .content(Objects.requireNonNull(objectMapper.writeValueAsString(demoEmployee)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }
    @Test
    public void shouldreturnalltheproductsfromdb() throws Exception
    {
        when(employeeRepository.findAll()).thenReturn(Arrays.asList(
                new  Employee(1,"emp1",50000,"emp1@gmail.com",new Date(1999-01-01), 57001,24),
                new Employee(2,"emp2",50000,"emp1@gmail.com",new Date(1999-01-02), 57002,24)));
        mockMvc.perform(MockMvcRequestBuilders
                        .get(ENDPOINT_URL)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.results[0].id").value(1));
    }


}
