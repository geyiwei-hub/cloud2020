package com.antherd.springcloud.alibaba.service.impl;

import com.antherd.springcloud.alibaba.dao.AccountDao;
import com.antherd.springcloud.alibaba.service.AccountService;
import java.math.BigDecimal;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

  @Resource
  private AccountDao accountDao;

  /**
   * 扣减账户余额
   */
  @Override
  public void decrease(Long userId, BigDecimal money) {

    log.info("***** account-service中扣减账户余额开始");
    accountDao.decrease(userId, money);
    log.info("***** storage-service中扣减账户余额结束");
  }
}
