package com.tianqiauto.cachetest.service;

import com.tianqiauto.cachetest.entity.Employee;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

/**
 * @author TIS-swg
 */
public interface IEmployeeService extends IService<Employee> {
    Employee getEmpById(Integer id) ;
    Employee updateEmp(Employee employee);
    void deleteEmp(Integer id);
}
