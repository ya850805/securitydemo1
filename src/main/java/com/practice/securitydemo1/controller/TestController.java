package com.practice.securitydemo1.controller;

import com.practice.securitydemo1.domain.Users;
import java.util.LinkedList;
import java.util.List;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
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

//  @Secured({"ROLE_manager", "ROLE_sale"})
//  @PreAuthorize("hasAnyAuthority('admins')")
  @PostAuthorize("hasAnyAuthority('admin')")
  @GetMapping("/welcome")
  public String welcome() {
    System.out.println("Welcome..................");
    return "This is welcome page.";
  }

  @PostFilter("filterObject.username.equals('username1')")
  @GetMapping("/filter")
  public List<Users> filter () {
    List<Users> list = new LinkedList<>();
    list.add(new Users(1, "username1", "123"));
    list.add(new Users(1, "username2", "123"));
    return list;
  }
}
