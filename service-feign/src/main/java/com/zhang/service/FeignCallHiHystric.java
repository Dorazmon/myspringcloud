package com.zhang.service;


import org.springframework.stereotype.Component;

@Component
public class FeignCallHiHystric implements IFeignCall{

  @Override
  public String sayHiFromClientOne(String name) {
    return "sorry"+name+",调用失败";
  }
}
