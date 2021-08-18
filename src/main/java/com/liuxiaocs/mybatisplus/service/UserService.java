package com.liuxiaocs.mybatisplus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liuxiaocs.mybatisplus.entity.User;

import java.util.List;

/**
 * @author liuxiaocs
 * @date 2021/4/4 16:37
 */
public interface UserService extends IService<User> {

    /**
     * 根据姓名查询
     * @param name
     * @return
     */
    List<User> listAllByName(String name);
}
