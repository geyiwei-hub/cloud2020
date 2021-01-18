package com.antherd.springcloud.alibaba.controller;

import com.antherd.springcloud.entities.Payment;
import com.antherd.springcloud.entities.CommonResult;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

  @Value("${server.port}")
  public String serverPort;

  public static HashMap<Long, Payment> hashMap = new HashMap<>();

  static {
    hashMap.put(1L, new Payment(1L, "28a8c1e3bc2742d8848569891fb42181"));
    hashMap.put(2L, new Payment(2L, "28a8c1e3bc2742d8848569891ac32181"));
    hashMap.put(3L, new Payment(3L, "28a8c1e3bc2742d8848569891xt92181"));
  }

  @GetMapping(value = "/paymentSQL/{id}")
  public CommonResult<Payment> paymentSQL(@PathVariable("id") Long id) {
    Payment payment = hashMap.get(id);
    CommonResult<Payment> result = new CommonResult(200, "from mysql, serverPort: " + serverPort, payment);
    return result;
  }
}
