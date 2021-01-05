package com.antherd.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import java.util.concurrent.TimeUnit;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

  public String paymentInfoOk(Integer id) {
    return "线程池：" + Thread.currentThread().getName() + " paymentInfoOk，" + id + " O(∩_∩)O哈哈~";
  }

  @HystrixCommand(fallbackMethod = "paymentInfoTimeoutHandle", commandProperties = {
      @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
  })
  public String paymentInfoTimeout(Integer id) {
    // int age = 10/0;
    int timeNumber = 3;
    try {
      TimeUnit.SECONDS.sleep(timeNumber);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return "线程池：" + Thread.currentThread().getName() + " id：" + id + " O(∩_∩)O哈哈~" + " 耗时" + timeNumber + "秒钟";
  }

  public String paymentInfoTimeoutHandle(Integer id) {
    return "线程池：" + Thread.currentThread().getName() + " id：" + id + " 8001系统繁忙或运行报错，请稍后再试" + " ┭┮﹏┭┮";
  }
}
