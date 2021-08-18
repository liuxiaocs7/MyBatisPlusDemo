package com.liuxiaocs.mybatisplus.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liuxiaocs.mybatisplus.entity.User;
import com.liuxiaocs.mybatisplus.mapper.UserMapper;
import com.liuxiaocs.mybatisplus.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liuxiaocs
 * @date 2021/4/4 16:38
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    // 自定义扩展方法

    @Override
    public List<User> listAllByName(String name) {
        // baseMapper对象指向当前业务的mapper对象
        return baseMapper.selectAllByName(name);
    }
}
