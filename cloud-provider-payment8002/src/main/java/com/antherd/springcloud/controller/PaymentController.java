package com.antherd.springcloud.controller;

import com.antherd.springcloud.entities.CommonResult;
import com.antherd.springcloud.entities.Payment;
import com.antherd.springcloud.service.PaymentService;
import java.util.List;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PaymentController {
  @Resource
  private PaymentService paymentService;

  @Value("${server.port}")
  private String serverPort;

  @Resource
  private DiscoveryClient discoveryClient;

  @PostMapping(value = "/payment")
  public CommonResult<Payment> create(@RequestBody Payment payment) {
    int result = paymentService.create(payment);
    log.info("*****插入结果： " + result);
    if(result > 0) {
      return new CommonResult(200, "插入数据库成功, serverPort:"  + serverPort, result);
    } else {
      return new CommonResult<>(444, "插入数据库失败",null);
    }
  }

  @GetMapping(value = "/payment/{id}")
  public CommonResult getPaymentById(@PathVariable Long id) {
    Payment payment = paymentService.getPaymentById(id);
    log.info("*****查询结果： " + payment);
    if (payment != null) {
      return new CommonResult(200, "查询成功, serverPort:"  + serverPort, payment);
    } else {
      return new CommonResult(444, "没有对应记录，查询ID: " + id,null);
    }
  }

  @GetMapping(value = "/payment/discovery")
  public Object discovery() {
    List<String> services = discoveryClient.getServices();
    for (String element : services) {
      log.info("*****element: " + element);
    }
    List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
    for (ServiceInstance instance : instances) {
      log.info(instance.getInstanceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());
    }

    return this.discoveryClient;
  }

  @GetMapping(value = "/payment/lb")
  public String getPaymentLB() {
    return serverPort;
  }
}
