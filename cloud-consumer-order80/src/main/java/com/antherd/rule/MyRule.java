package com.antherd.rule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyRule {

  @Bean
  public IRule myRibbonRule() {
    return new RandomRule(); // 定义为随机
  }
}
