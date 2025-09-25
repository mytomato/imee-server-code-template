package com.imee.main.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.imee.user")
@MapperScan("com.imee.user.mapper")
public class UserModuleConfig {
}