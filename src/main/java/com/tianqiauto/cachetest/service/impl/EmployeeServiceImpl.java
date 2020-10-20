package com.tianqiauto.cachetest.service.impl;

import com.tianqiauto.cachetest.entity.Employee;
import com.tianqiauto.cachetest.mapper.EmployeeMapper;
import com.tianqiauto.cachetest.service.IEmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author TIS-swg
 */
@Service
@CacheConfig(cacheNames = "emp")
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

    @Cacheable( key = "#id")
    public Employee getEmpById(Integer id){
        System.out.println("查询" + id + "号员工!");
        Employee employee = this.getById(id);
        return  employee;
    }

    //    @CachePut: 既调用方法，又更新缓存数据；同步更新缓存
    //    修改了数据库的某个数据，同时更新缓存
    //    运行：
    //        1.先调用目标方法
    //        2.将目标方法的结果缓存起来
    @CachePut(key = "#employee.id")
    public Employee updateEmp(Employee employee){
        System.out.println("updateEmp "+employee);
        Boolean result = this.updateById(employee);
        if (result){
            employee = this.getEmpById(employee.getId());
        }
        return employee;
    }
    //       @CacheEvict:缓存清除
    //       key:指定要清除的数据
    //       allEntries = true : 指定清除这个缓存中的所有数据
    //       beforeInvocation=fales: 缓存的清除是否在方法之前执行
    //       默认代表缓存清除操作是在方法执行之后执行；如果出现异常缓存就不会清除
    //       beforeInvocation=true  代表清除缓存操作是在方法运行之前执行，无论方法是否出现异常，缓存都清除

    @CacheEvict(beforeInvocation = true )
    public void deleteEmp(Integer id){
        System.out.println("delteEmp: " + id);
        this.removeById(id);
    }
}
