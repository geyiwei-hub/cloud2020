package com.antherd.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

  @GetMapping("/testHotKey")
  @SentinelResource(value = "testHotKey", blockHandler = "dealTestHotKey") // value：sentinel热点key资源名，blockHandler不会处理RuntimeException异常
  public String testHotKey(@RequestParam(value = "p1", required = false) String p1,
      @RequestParam(value = "p2", required = false) String p2) {
    return "***** testHotKey";
  }

  public String dealTestHotKey(String p1, String p2, BlockException exception) {
    return "***** dealTestHotKey o(╥﹏╥)o"; // sentinel 系统默认的提示：Blocked by Sentinel (flow limiting)
  }
}
