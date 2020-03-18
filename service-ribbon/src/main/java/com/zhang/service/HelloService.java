package com.zhang.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class HelloService implements IHelloService{
  @Autowired
  private RestTemplate restTemplate;

  @Override
  public String hiService(String name) {
    return restTemplate.getForObject("http://SERVICE-HI/hi?name="+name,String.class);
  }
}
