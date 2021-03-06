package com.tianqiauto.mybatis.list.controller;


import com.tianqiauto.mybatis.list.entity.Class;
import com.tianqiauto.mybatis.list.service.IClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author TIS-swg
 */
@RestController
@RequestMapping("/list/class")
public class ClassController {
    @Autowired
    private IClassService classService;

    @RequestMapping("queryAll")
    public List<Class> queryAll(){
        return classService.queryAll();
    }
}
