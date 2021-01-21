package com.antherd.springcloud.alibaba.service.impl;

import com.antherd.springcloud.alibaba.dao.StorageDao;
import com.antherd.springcloud.alibaba.service.StorageService;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StorageServiceImpl implements StorageService {

  @Resource
  private StorageDao storageDao;

  @Override
  public void decrease(Long productId, Integer count) {

    log.info("***** storage-service中扣减库存开始");
    storageDao.decrease(productId, count);
    log.info("***** storage-service中扣减库存结束");
  }
}
