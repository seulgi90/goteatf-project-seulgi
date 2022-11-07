package com.goteatfproject.appgot.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.goteatfproject.appgot.service.FeedService;

@Controller
@RequestMapping("/feed")
public class FeedController {

  FeedService feedService;

  public FeedController(FeedService feedService) {
    System.out.println("FeedController() 호출됨!");
    this.feedService = feedService;
  }

  //  @GetMapping ("/add")
  //  public String add() throws Exception {
  //    return "member/form";
  //  }

  //  @PostMapping("/save")
  //  public String save(User user) throws Exception {
  //    return "memberInfo";
  //  }
  //
  //  @PostMapping("/add")
  //  public String add(User user) throws Exception {
  //    memberService.add(user);
  //    return "redirect:list";
  //  }

  @GetMapping("/list")
  public String list(Model model) throws Exception {
    model.addAttribute("feeds", feedService.list());
    return "feed/Feed";

  }


}
