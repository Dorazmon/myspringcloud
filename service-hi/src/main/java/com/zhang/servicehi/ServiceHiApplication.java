package com.zhang.servicehi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RestController
@Slf4j
public class ServiceHiApplication {
  public static void main(String[] args) {
    SpringApplication.run( ServiceHiApplication.class, args );
  }

  @Value("${server.port}")
  String port;

  @RequestMapping("/hi")
  public String home(@RequestParam(value = "name") String name) {
    log.debug("{debug日志}");
    log.info("{info日志}");
    log.warn("{warn日志}");
    log.error("{error日志}");
    return "名字"+name+" ,端口号:" + port;
  }


}
