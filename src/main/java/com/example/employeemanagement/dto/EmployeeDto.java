package com.example.employeemanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDto {
    private String empName;

    private int Salary;

    private String email;

    private Date dateofbirth;

    private int age;

    private int pincode;


}
