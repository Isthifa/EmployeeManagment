package com.example.employeemanagement.service;

import com.example.employeemanagement.dto.*;
import com.example.employeemanagement.entity.Company;
import com.example.employeemanagement.entity.Employee;
import com.example.employeemanagement.entity.EmployeeCompanyXref;
import com.example.employeemanagement.repository.CompanyRepository;
import com.example.employeemanagement.repository.EmployeeCompanyXrefRepo;
import com.example.employeemanagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServicesImpl implements Services{

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeCompanyXrefRepo empCompanyXrefrepo;

    @Override
    public CompanyDto CompanySave(CompanyDto companyDto) {
        Company company= Company.builder()
                .compName(companyDto.getCompanyname())
                .address(companyDto.getAddress())
                .email(companyDto.getEmail())
                .phoneNo(companyDto.getPhoneNo())
                .build();
        companyRepository.save(company);
        return companyDto;
    }

    @Override
    public EmployeeDto EmployeeSave(EmployeeDto employeeDto) {

        Employee employee=Employee.builder()
                .empName(employeeDto.getEmpName())
                .salary(employeeDto.getSalary())
                .email(employeeDto.getEmail())
                .dateofBirth(employeeDto.getDateofbirth())
                .pincode(employeeDto.getPincode())
                .age(employeeDto.getAge())
                .build();
        employeeRepository.save(employee);

        return employeeDto;
    }

    @Override
    public EmpCompanyXrefDTO EmployeeCompanyXref(EmpCompanyXrefDTO companyXrefDTO) {
        EmployeeCompanyXref employeeCompanyXref= new EmployeeCompanyXref();
        Optional<Employee> optionalEmployee=employeeRepository.findById(companyXrefDTO.getEmployeeId());
        if(optionalEmployee.isPresent())
        {
            employeeCompanyXref.setEmployee(optionalEmployee.get());
        }
        else
        {
            throw new RuntimeException("user not found");
        }
        Optional<Company> optionalCompany=companyRepository.findById(companyXrefDTO.getCompanyId());
        if(optionalCompany.isPresent())
        {
            employeeCompanyXref.setCompany(optionalCompany.get());
        }
        else
        {
            throw new RuntimeException("not found");
        }
        empCompanyXrefrepo.save(employeeCompanyXref);
        return companyXrefDTO;
    }

    @Override
    public List<EmployeeCompanyXref> empcomplist() {
        return empCompanyXrefrepo.findAll();
    }

    @Override
    public List<Employeebycompanydto> findallemployeebycompanyid(int id) {
        List<Employee> employee = empCompanyXrefrepo.findallemployeebycompanyid(id);
        List<Employeebycompanydto> employeebycompanydto = new ArrayList<>();
        for (Employee e : employee) {
           employeebycompanydto.add(new Employeebycompanydto(e.getEmpName(),e.getEmail(),e.getSalary()));
        }
        return employeebycompanydto;
    }

    @Override
    public ApiResponse<Page<Employee>> findbyNthhighestsalary(int offset, int pageSize) {
        Pageable pageable=PageRequest.of(offset,pageSize);
         return new ApiResponse<>(pageSize,employeeRepository.findempbynthhighestsalary(pageable));

    }
}
