package com.imee.main.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.imee.user.model.User;
import com.imee.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        // 检查是否已经存在用户数据，避免重复插入
        if (userService.count() > 0) {
            System.out.println("用户数据已存在，跳过初始化");
            return;
        }
        
        // 初始化一些测试用户数据
        User user1 = new User("zhangsan", "zhangsan@example.com", "13800138001");
        user1.setCreateTime(LocalDateTime.now());
        user1.setUpdateTime(LocalDateTime.now());
        user1.setStatus(1);
        userService.save(user1);

        User user2 = new User("lisi", "lisi@example.com", "13800138002");
        user2.setCreateTime(LocalDateTime.now());
        user2.setUpdateTime(LocalDateTime.now());
        user2.setStatus(1);
        userService.save(user2);

        User user3 = new User("wangwu", "wangwu@example.com", "13800138003");
        user3.setCreateTime(LocalDateTime.now());
        user3.setUpdateTime(LocalDateTime.now());
        user3.setStatus(1);
        userService.save(user3);

        System.out.println("初始化了3个测试用户数据");
    }
}