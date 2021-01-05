package com.antherd.springcloud.controller;

import com.antherd.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "paymentGlobalFallback") // 添加DefaultProperties
public class OrderHystrixController {

  @Resource
  private PaymentHystrixService paymentHystrixService;

  @Value("${server.port}")
  private String serverPort;

  @GetMapping("/consumer/payment/hystrix/ok/{id}")
  public String paymentInfoOk(@PathVariable("id") Integer id) {
    String result = paymentHystrixService.paymentInfoOk(id);
    log.info("*****result***** : " + result);
    return result;
  }

  @GetMapping("/consumer/payment/hystrix/timeout/{id}")
//  @HystrixCommand(fallbackMethod = "paymentInfoTimeoutHandle", commandProperties = {
//      @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
//  })
  @HystrixCommand // 需要服务降级的方法上仅添加@HystrixCommand注解
  public String paymentInfoTimeout(@PathVariable("id") Integer id) {
    int age = 10/0; // 添加异常代码，测试全局服务降级
    return paymentHystrixService.paymentInfoTimeout(id);
  }

  public String paymentInfoTimeoutHandle(@PathVariable("id") Integer id) {
    return "我是消费者80，对方支付系统繁忙请10秒后再试或者自己运行出错请检查自己，┭┮﹏┭┮";
  }

  public String paymentGlobalFallback() {
    return "Global异常处理信息，请稍后重试，o(╥﹏╥)o";
  }
}
