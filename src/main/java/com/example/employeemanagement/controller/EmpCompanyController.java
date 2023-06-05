package com.example.employeemanagement.controller;

import com.example.employeemanagement.dto.*;
import com.example.employeemanagement.entity.Employee;
import com.example.employeemanagement.entity.EmployeeCompanyXref;
import com.example.employeemanagement.repository.EmployeeCompanyXrefRepo;
import com.example.employeemanagement.service.Services;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class EmpCompanyController {

    @Autowired
    private Services services;

    @PostMapping("/empsave")
    public ResponseEntity<?> employeesave(@RequestBody EmployeeDto employeeDto)
    {
        EmployeeDto Employee=services.EmployeeSave(employeeDto);
        return new ResponseEntity<>("Data saved \n"+Employee,HttpStatus.OK);
    }

    @PostMapping("/compsave")
    public ResponseEntity<?> companysave(@RequestBody CompanyDto companyDto)
    {
        CompanyDto companyDto1=services.CompanySave(companyDto);
        return new ResponseEntity<>("Data Saved \n"+companyDto1,HttpStatus.OK);
    }

    @PostMapping("/empcompsave")
    public ResponseEntity<?> empcompsave(@RequestBody EmpCompanyXrefDTO empCompanyXrefDTO)
    {
        EmpCompanyXrefDTO empCompanyXrefDTO1=services.EmployeeCompanyXref(empCompanyXrefDTO);
        return new ResponseEntity<>("Data saved\n"+empCompanyXrefDTO1,HttpStatus.OK);
    }

    @GetMapping("/findall")
    public List<EmployeeCompanyXref> employeeCompanyXrefList()
    {
        return services.empcomplist();
    }
    @GetMapping("/findemp/{id}")
    public List<Employeebycompanydto> findbycompanyid(@PathVariable int id)
    {
        return services.findallemployeebycompanyid(id);
    }

    @GetMapping("/salary/{n}/{size}")
    public ApiResponse<Page<Employee>> findbynthsalary(@PathVariable("n") int n, @PathVariable("size") int size)
    {
        return services.findbyNthhighestsalary(n,size);
    }

}
