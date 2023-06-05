package com.example.employeemanagement.repository;

import com.example.employeemanagement.entity.Employee;
import com.example.employeemanagement.entity.EmployeeCompanyXref;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeCompanyXrefRepo extends JpaRepository<EmployeeCompanyXref,Integer> {

    @Query("select e.employee from EmployeeCompanyXref e where  e.company.id=:id")
    List<Employee> findallemployeebycompanyid(@Param("id") int id);

}
