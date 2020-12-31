package com.antherd.springcloud.service;

import java.util.concurrent.TimeUnit;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

  public String paymentInfoOk(Integer id) {
    return "线程池：" + Thread.currentThread().getName() + " paymentInfoOk，" + id + " O(∩_∩)O哈哈~";
  }

  public String paymentInfoTimeout(Integer id) {
    int timeNumber = 3;
    try {
      TimeUnit.SECONDS.sleep(timeNumber);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return "线程池：" + Thread.currentThread().getName() + " paymentInfoTimeout，" + id + " ┭┮﹏┭┮" + " 耗时" + timeNumber + "秒钟";
  }
}
