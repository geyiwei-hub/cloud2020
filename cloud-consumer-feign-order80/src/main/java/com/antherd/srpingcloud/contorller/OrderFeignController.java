package com.antherd.srpingcloud.contorller;

import com.antherd.springcloud.entities.CommonResult;
import com.antherd.springcloud.entities.Payment;
import com.antherd.srpingcloud.service.PaymentFeignService;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OrderFeignController {

  @Resource
  private PaymentFeignService paymentFeignService;

  @GetMapping("/consumer/payment/{id}")
  public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {
    return paymentFeignService.getPaymentById(id);
  }

  @GetMapping(value = "/consumer/payment/feign/timeout")
  String paymentFeignTimeout() {
    // openfeign-ribbon客户端默认等待一秒钟
    return paymentFeignService.paymentFeignTimeout();
  }
}
