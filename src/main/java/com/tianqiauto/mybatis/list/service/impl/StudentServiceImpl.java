package com.tianqiauto.mybatis.list.service.impl;

import com.tianqiauto.mybatis.list.entity.Student;
import com.tianqiauto.mybatis.list.mapper.StudentMapper;
import com.tianqiauto.mybatis.list.service.IStudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author TIS-swg
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {

}
