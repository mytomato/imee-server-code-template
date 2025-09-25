package com.imee.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imee.common.result.Result;
import com.imee.user.model.User;
import com.imee.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 创建用户
     * @param user 用户信息
     * @return Result<User>
     */
    @PostMapping
    public Result<User> createUser(@RequestBody User user) {
        // 检查用户名是否已存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", user.getUsername());
        if (userService.count(queryWrapper) > 0) {
            return Result.failed("用户名已存在");
        }
        
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        user.setStatus(1);
        userService.save(user);
        return Result.success("用户创建成功", user);
    }

    /**
     * 根据ID获取用户
     * @param id 用户ID
     * @return Result<User>
     */
    @GetMapping("/{id}")
    public Result<User> getUserById(@PathVariable Long id) {
        User user = userService.getById(id);
        if (user == null) {
            return Result.failed("用户不存在");
        }
        return Result.success(user);
    }

    /**
     * 获取所有用户
     * @return Result<List<User>>
     */
    @GetMapping
    public Result<List<User>> getAllUsers() {
        List<User> users = userService.list();
        return Result.success(users);
    }

    /**
     * 分页获取用户
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @return Result<Page<User>>
     */
    @GetMapping("/page")
    public Result<Page<User>> getUserPage(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        Page<User> page = new Page<>(pageNum, pageSize);
        Page<User> userPage = userService.page(page);
        return Result.success(userPage);
    }

    /**
     * 更新用户信息
     * @param id 用户ID
     * @param user 用户信息
     * @return Result<User>
     */
    @PutMapping("/{id}")
    public Result<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        User existingUser = userService.getById(id);
        if (existingUser == null) {
            return Result.failed("用户不存在");
        }
        
        // 更新用户信息
        user.setId(id);
        user.setUpdateTime(LocalDateTime.now());
        userService.updateById(user);
        return Result.success("用户更新成功", user);
    }

    /**
     * 删除用户（逻辑删除）
     * @param id 用户ID
     * @return Result<String>
     */
    @DeleteMapping("/{id}")
    public Result<String> deleteUser(@PathVariable Long id) {
        User existingUser = userService.getById(id);
        if (existingUser == null) {
            return Result.failed("用户不存在");
        }
        
        // 逻辑删除，设置状态为0
        existingUser.setStatus(0);
        existingUser.setUpdateTime(LocalDateTime.now());
        userService.updateById(existingUser);
        return Result.success("用户删除成功");
    }
}