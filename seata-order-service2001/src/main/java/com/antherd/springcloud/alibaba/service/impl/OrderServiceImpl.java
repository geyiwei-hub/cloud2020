package com.antherd.springcloud.alibaba.service.impl;

import com.antherd.springcloud.alibaba.dao.OrderDao;
import com.antherd.springcloud.alibaba.domain.Order;
import com.antherd.springcloud.alibaba.service.AccountService;
import com.antherd.springcloud.alibaba.service.OrderService;
import com.antherd.springcloud.alibaba.service.StorageService;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

  @Resource
  private OrderDao orderDao;

  @Resource
  private StorageService storageService;

  @Resource
  private AccountService accountService;

  /**
   * 下订单->减库存->减余额->改状态
   * @param order
   */
  @Override
  public void create(Order order) {
    log.info("***** 开始新建订单");
    orderDao.create(order);

    log.info("***** 订单微服务开始调用库存，做扣减Count");
    storageService.decrease(order.getProductId(), order.getCount());
    log.info("***** 订单微服务开始调用库存，做扣减end");

    log.info("***** 订单微服务开始调用账户，做扣减money");
    accountService.decrease(order.getUserId(), order.getMoney());
    log.info("***** 订单微服务开始调用账户，做扣减end");

    log.info("***** 修改订单状态开始");
    orderDao.update(order.getUserId(), 0);
    log.info("***** 修改订单状态结束");

    log.info("***** 下订单结束了，O(∩_∩)O哈哈~");
  }
}
