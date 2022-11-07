package com.goteatfproject.appgot.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.goteatfproject.appgot.service.FeedService;
import com.goteatfproject.appgot.service.MemberService;
import com.goteatfproject.appgot.service.PartyService;

@Controller
@RequestMapping("/my")
public class MyController {

  PartyService partyService;
  FeedService feedService;
  MemberService memberService;

  public MyController(PartyService partyService,FeedService feedService, MemberService memberService) {
    this.partyService = partyService;
    this.feedService = feedService;
    this.memberService = memberService;
  }

  // 마이페이지
  @GetMapping("/main")
  public String myPage() throws Exception {
    // model.addAttribute("parties", myPageService.list());
    return "mypage/myPartyList";
  }

  // 마이페이지- 개인 정보 수정
  @GetMapping("/myProfile")
  public String myProfile(Model model) throws Exception {
    model.addAttribute("members", memberService.list());
    return "mypage/myProfile";
  }

  // 마이페이지-파티게시글 관리
  @GetMapping("/myPartyList")
  public String myPartylist(Model model) throws Exception {
    model.addAttribute("parties", partyService.list());
    model.getAttribute("parties");
    System.out.println("parties data:" + model.getAttribute("parties")); // data 값 확인용
    return "mypage/myPartyList";
  }

  // 마이페이지-피드게시글 관리
  @GetMapping("/myFeedList")
  public String myFeedList(Model model) throws Exception {
    model.addAttribute("feeds", feedService.list());
    //    model.getAttribute("feeds");
    //    System.out.println("data:" + model.getAttribute("feeds"));
    return "mypage/myFeedList";
  }

}
