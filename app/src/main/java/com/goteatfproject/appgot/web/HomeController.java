package com.goteatfproject.appgot.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.goteatfproject.appgot.service.MemberService;
import com.goteatfproject.appgot.vo.Member;

@Controller
public class HomeController {

  @Autowired
  MemberService memberService;

  @GetMapping("/")
  public String home() {
    return "index";
  }

  @GetMapping("/test")
  public String test() {
    return "test";
  }


  @PostMapping("/test1")
  @ResponseBody
  public Object test1() throws Exception {
    Member member = memberService.get(51);
    return member;
  }
}
