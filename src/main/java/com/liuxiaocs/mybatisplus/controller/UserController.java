package com.liuxiaocs.mybatisplus.controller;

import com.liuxiaocs.mybatisplus.entity.User;
import com.liuxiaocs.mybatisplus.service.UserService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liuxiaocs
 * @date 2021/4/5 10:34
 */
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("list")
    public List<User> list() {
        return userService.list();
    }
}
