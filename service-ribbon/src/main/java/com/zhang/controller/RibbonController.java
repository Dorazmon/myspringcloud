package com.zhang.controller;

import com.zhang.service.IHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RibbonController {

  @Autowired
  private IHelloService helloService;
  @RequestMapping(value = "/hi")
  public String hi(@RequestParam String name){
    return helloService.hiService(name);
  }
}
