package com.zhang.control;

import com.zhang.service.IFeignCall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignController {
  @Autowired
  IFeignCall FeignCallImpl;
  @GetMapping(value = "/hi")
  public String sayHi(@RequestParam String name) {
    return FeignCallImpl.sayHiFromClientOne( name );
  }

}
