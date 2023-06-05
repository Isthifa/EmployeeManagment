package com.example.employeemanagement.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmpCompanyXrefDTO {

    @JsonIgnore
    private int id;

    private int employeeId;

    private int companyId;
}
