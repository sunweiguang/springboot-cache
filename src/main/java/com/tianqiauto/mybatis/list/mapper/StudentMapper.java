package com.tianqiauto.mybatis.list.mapper;

import com.tianqiauto.mybatis.list.entity.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author TIS-swg
 */
public interface StudentMapper extends BaseMapper<Student> {

    List<Student> selByCid(int cid);
}
