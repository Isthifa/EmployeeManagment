package com.example.employeemanagement.service;

import com.example.employeemanagement.dto.*;
import com.example.employeemanagement.entity.Employee;
import com.example.employeemanagement.entity.EmployeeCompanyXref;
import org.springframework.data.domain.Page;

import java.util.List;

public interface Services {

    CompanyDto CompanySave(CompanyDto companyDto);

    EmployeeDto EmployeeSave(EmployeeDto employeeDto);

    EmpCompanyXrefDTO EmployeeCompanyXref(EmpCompanyXrefDTO companyXrefDTO);

    List<EmployeeCompanyXref> empcomplist();

    List<Employeebycompanydto> findallemployeebycompanyid(int id);

    ApiResponse<Page<Employee>> findbyNthhighestsalary(int offset, int pageSize);


}
