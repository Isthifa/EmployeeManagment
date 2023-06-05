package com.example.employeemanagement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Company implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    @Size(min = 2,message = "Company name should have at least 2 character")
    private String compName;

    @Size(min = 2,message = "Address must have least 2 char")
    private String address;

    @Column(nullable = false)
    @NotBlank
    private String email;

    @Column(nullable = false)
    private Long phoneNo;
}
