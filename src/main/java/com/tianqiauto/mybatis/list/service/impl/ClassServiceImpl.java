package com.tianqiauto.mybatis.list.service.impl;

import com.tianqiauto.mybatis.list.entity.Class;
import com.tianqiauto.mybatis.list.mapper.ClassMapper;
import com.tianqiauto.mybatis.list.service.IClassService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author TIS-swg
 */
@Service
public class ClassServiceImpl extends ServiceImpl<ClassMapper, Class> implements IClassService {

    @Override
    public List<Class> queryAll() {
        return this.baseMapper.selAll();
    }
}
