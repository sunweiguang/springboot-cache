package com.tianqiauto.mybatis.list.service;

import com.tianqiauto.mybatis.list.entity.Class;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author TIS-swg
 */
public interface IClassService extends IService<Class> {

    List<Class> queryAll();
}
