package com.liuxiaocs.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author liuxiaocs
 * @date 2021/4/4 16:02
 */
@Data
@TableName(value = "t_user")
public class User {

    /**
     * 实体类属性是id，数据库字段为uid，id策略是雪花算法
     */
//    @TableId(value = "uid", type = IdType.ASSIGN_ID)

    /**
     * 实体类属性是id，数据库字段为uid，id策略是自增，将当前的最大值作为起点 +1作为当前结果
     */
    @TableId(value = "uid", type = IdType.AUTO)
    private Long id;

    /**
     * 数据库中列名和实体类属性名不一样的时候必须添加 @TableField(value = "数据库列名")注解
     */
    @TableField(value = "username")
    private String name;

    @TableField(fill = FieldFill.INSERT)
    private Integer age;

    private String email;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableLogic
    @TableField(value = "is_deleted")
    private Boolean deleted;  // 0 false 未删除  1 true 已删除
}
