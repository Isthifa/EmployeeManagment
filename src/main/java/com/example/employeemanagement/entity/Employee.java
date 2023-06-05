package com.example.employeemanagement.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @Column(nullable = false)
    @Size(min = 2,message = "at two characters required")
    private String empName;

    @Column(nullable = false)
    private int salary;

    @Column(nullable = false)
    @Email
    private String email;

    @JsonFormat(pattern = "yyyy/MM/dd")
    @Column(nullable = false)
    private Date dateofBirth;

    @Column(nullable = false)
    private int pincode;

    @Column(nullable = false)
    private int age;


}
