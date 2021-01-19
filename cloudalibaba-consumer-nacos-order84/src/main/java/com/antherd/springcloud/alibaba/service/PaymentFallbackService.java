package com.antherd.springcloud.alibaba.service;

import com.antherd.springcloud.entities.CommonResult;
import com.antherd.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentService {

  @Override
  public CommonResult<Payment> paymentSQL(Long id) {

    return new CommonResult<>(444444, "服务降级返回， *****PaymentFallbackService", new Payment(id, "errorSerial"));
  }
}
