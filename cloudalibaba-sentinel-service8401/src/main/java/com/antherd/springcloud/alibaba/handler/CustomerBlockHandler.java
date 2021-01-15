package com.antherd.springcloud.alibaba.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.antherd.springcloud.entities.CommonResult;
import com.antherd.springcloud.entities.Payment;

public class CustomerBlockHandler {

  public static CommonResult handlerException(BlockException exception) {

    return new CommonResult(200, "按客户自定义, global handler exception-----1", new Payment(2020L, "serial003"));
  }

  public static CommonResult handlerException2(BlockException exception) {

    return new CommonResult(200, "按客户自定义, global handler exception-----2", new Payment(2020L, "serial003"));
  }
}
