package com.antherd.springcloud.alibaba.controller;

import com.antherd.springcloud.alibaba.service.AccountService;
import com.antherd.springcloud.entities.CommonResult;
import java.math.BigDecimal;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

  @Resource
  private AccountService accountService;

  /**
   * 扣减账户余额
   */
  @PostMapping(value = "/account/decrease")
  public CommonResult decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money) {

    accountService.decrease(userId, money);
    return new CommonResult(200, "扣减账户余额成功!");
  }
}
