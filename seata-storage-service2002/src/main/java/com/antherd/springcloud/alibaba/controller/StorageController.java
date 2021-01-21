package com.antherd.springcloud.alibaba.controller;

import com.antherd.springcloud.alibaba.service.StorageService;
import com.antherd.springcloud.entities.CommonResult;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StorageController {

  @Resource
  private StorageService storageService;

  @PostMapping(value = "/storage/decrease")
  public CommonResult decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count) {
    storageService.decrease(productId, count);
    return new CommonResult(200, "扣减库存成功!");
  }
}
