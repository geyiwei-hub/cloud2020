package com.antherd.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.antherd.springcloud.entities.Payment;
import com.antherd.springcloud.entities.CommonResult;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class CircleBreakerController {

  public static final String SERVICE_URL = "http://nacos-payment-provider";

  @Resource
  private RestTemplate restTemplate;

  @RequestMapping("/consumer/fallback/{id}")
  @SentinelResource(value = "fallback") // 没有配置
  // @SentinelResource(value = "fallback", fallback = "handlerFallback") // fackback只负责业务异常
  // @SentinelResource(value = "fallback", blockHandler = "blockHandler") // blockHandler只负责sentinel控制台配置违规
  // @SentinelResource(value = "fallback", fallback = "handlerFallback", blockHandler = "blockHandler")
  public CommonResult<Payment> fallback(@PathVariable Long id) {
    CommonResult<Payment> result = restTemplate.getForObject(SERVICE_URL + "/paymentSQL/" + id, CommonResult.class, id);

    if (id == 4) {
      throw new IllegalArgumentException("IllegalAccessException, 非法参数异常....");
    } else if (result.getData() == null) {
      throw new NullPointerException("NullPointerException, 该ID没有对应记录， 控制针异常");
    }
    return result;
  }
}