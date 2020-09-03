package com.tianqiauto.cachetest.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

/**
 *
 *
 * @author TIS-swg
 */
@Data
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id()
    private Integer id;


    @TableField("lastName")
    private String lastName;

    @TableField("email")
    private String email;

    @TableField("gender")
    private Integer gender;

    @TableField("d_Id")
    private Integer dId;


}
