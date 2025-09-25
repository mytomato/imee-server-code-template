package com.imee.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.imee.user.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}