package com.antherd.springcloud.alibaba.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlowLimitController {

  @GetMapping("/testA")
  public String TestA() {
    return "****** testA";
  }

  @GetMapping("/testB")
  public String TestB() {
    return "****** testB";
  }
}
