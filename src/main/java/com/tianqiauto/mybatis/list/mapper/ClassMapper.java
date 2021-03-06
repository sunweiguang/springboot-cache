package com.tianqiauto.mybatis.list.mapper;

import com.tianqiauto.mybatis.list.entity.Class;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @author TIS-swg
 */
public interface ClassMapper extends BaseMapper<Class> {
    List<Class> selAll();
}
