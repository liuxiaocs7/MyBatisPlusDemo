package com.liuxiaocs.mybatisplus.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 最好是在所有数据库表中都会出现的字段使用自动填充
 * 否则在业务层中写默认值即可
 * @author liuxiaocs
 * @date 2021/4/4 19:23
 */
@Component  // 这个类会被Spring自动管理
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {

    /**
     * 每次插入的时候自动执行
     *
     * @param metaObject 要操作数据对象的源数据信息
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("insert 自动填充......");
        // 实现填充业务逻辑
        // 源数据对象，针对哪个字段填充(实体类中的属性)，字段的类型，字段的值
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());

        // 判断当前对象的自动填充属性是否已经进行了赋值
        Object age = this.getFieldValByName("age", metaObject);
        // 业务层没有进行赋值就使用自动填充
        if (age == null) {
            log.info("insert age 属性");
            this.strictInsertFill(metaObject, "age", Integer.class, 3);
        }

        // 判断当前的数据对象的自动填充属性是否具备当前(author)属性
        boolean hasAuthor = metaObject.hasSetter("author");
        if (hasAuthor) {
            log.info("insert author 属性");
            this.strictInsertFill(metaObject, "author", String.class, "Helen");
        }
    }

    /**
     * 每次更新的时候自动执行
     *
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("update 自动填充......");
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
    }
}
