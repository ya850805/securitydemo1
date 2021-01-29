package com.practice.securitydemo1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
  @GetMapping("/hello")
  public String hello() {
    return "Hello Security";
  }

  @GetMapping("/index")
  public String index () {
    return "This is index page.";
  }
}
