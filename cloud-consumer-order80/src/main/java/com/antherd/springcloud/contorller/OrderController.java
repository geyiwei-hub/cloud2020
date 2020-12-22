package com.antherd.springcloud.contorller;

import com.antherd.springcloud.entities.CommonResult;
import com.antherd.springcloud.entities.Payment;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class OrderController {

  //private  static final String PAYMENT_URL = "http://localhost:8001";
  private  static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";

  @Resource
  private RestTemplate restTemplate;

  @PostMapping("/consumer/payment")
  public CommonResult<Payment> create(Payment payment) {
    return restTemplate.postForObject(PAYMENT_URL + "/payment", payment, CommonResult.class);
  }

  @GetMapping("/consumer/payment/{id}")
  public CommonResult<Payment> getPayment(@PathVariable Long id) {
    return restTemplate.getForObject(PAYMENT_URL + "/payment/" + id, CommonResult.class);
  }

  @GetMapping("/consumer/payment2/{id}")
  public CommonResult<Payment> getPayment2(@PathVariable Long id) {
    ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/" + id, CommonResult.class);
    if (entity.getStatusCode().is2xxSuccessful()) {
      return entity.getBody();
    } else {
      return new CommonResult<>(444, "操作失败");
    }
  }
}
