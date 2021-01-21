package com.antherd.springcloud.alibaba.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.antherd.springcloud.alibaba.dao"})
public class MybatisConfig {

}
