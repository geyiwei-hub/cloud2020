package com.antherd.springcloud.controller;

import com.antherd.springcloud.service.IMessageProvider;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendMessageController {

  @Resource
  private IMessageProvider messageProvider;

  @GetMapping(value = "/sendMessage")
  public String sendMessage() {
    return messageProvider.send();
  }
}
