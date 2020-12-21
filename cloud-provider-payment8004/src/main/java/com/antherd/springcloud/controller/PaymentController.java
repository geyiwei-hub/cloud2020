package com.antherd.springcloud.controller;

import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PaymentController {

  @Value("${server.port}")
  private String serverPort;

  @RequestMapping(value = "/payment/zk")
  public String paymentZk() {
    return "Spring Cloud with Zookeeper: " + serverPort + "\t" + UUID.randomUUID().toString();
  }
}
