package com.antherd.springcloud.alibaba.domain;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

  private Long id;

  private Long userId;

  private BigDecimal total;

  private BigDecimal used;

  private BigDecimal residue;
}
