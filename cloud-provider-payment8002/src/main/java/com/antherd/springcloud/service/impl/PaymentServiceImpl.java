package com.antherd.springcloud.service.impl;

import com.antherd.springcloud.dao.PaymentDao;
import com.antherd.springcloud.entities.Payment;
import com.antherd.springcloud.service.PaymentService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

  @Resource
  private PaymentDao paymentDao;

  @Override
  public int create(Payment payment) {
    return paymentDao.create(payment);
  }

  @Override
  public Payment getPaymentById(Long id) {
    return paymentDao.getPaymentById(id);
  }
}
