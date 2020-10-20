package com.tianqiauto.cachetest.controller;

import com.tianqiauto.cachetest.entity.Employee;
import com.tianqiauto.cachetest.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @Autowired
    IEmployeeService employService;

    @GetMapping("/emp/{id}")
    public Employee getEmpById(@PathVariable("id") Integer id) {
        Employee empById = employService.getEmpById(id);
        return empById;
    }

    @GetMapping("/emp")
    public Employee updateEmp(Employee employee){
        Employee a = employService.updateEmp(employee);
        return a;
    }

    @GetMapping("/delEmp")
    public  String deleteEmp(Integer id){
        employService.deleteEmp(id);
        return "success";
    }
}
