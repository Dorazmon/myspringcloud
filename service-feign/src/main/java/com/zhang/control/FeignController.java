package com.zhang.control;

import com.zhang.service.IFeignCall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignController {
  @Autowired
  private IFeignCall FeignCallImpl;
  @GetMapping(value = "/hi")
  @PreAuthorize("hasAuthority('p3')")
  public String sayHi(@RequestParam String name) {
    return FeignCallImpl.sayHiFromClientOne( name )+"---feign调用的";
  }

}
