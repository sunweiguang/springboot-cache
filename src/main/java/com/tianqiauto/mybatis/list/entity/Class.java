package com.tianqiauto.mybatis.list.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 *
 *
 * @author TIS-swg
 */
@Data
@TableName("Class")
public class Class implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private List<Student> studentsList;
}
