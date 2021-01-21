package com.antherd.springcloud.alibaba.service.impl;

import com.antherd.springcloud.alibaba.dao.AccountDao;
import com.antherd.springcloud.alibaba.service.AccountService;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;
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
    // 模拟超时异常，全局事务回滚
    try {
      TimeUnit.SECONDS.sleep(20);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    accountDao.decrease(userId, money);
    log.info("***** storage-service中扣减账户余额结束");
  }
}
