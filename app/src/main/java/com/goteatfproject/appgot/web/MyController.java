package com.goteatfproject.appgot.web;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.goteatfproject.appgot.service.FeedService;
import com.goteatfproject.appgot.service.MemberService;
import com.goteatfproject.appgot.service.PartyService;
import com.goteatfproject.appgot.vo.Member;

@Controller
@RequestMapping("/my")
public class MyController {

  PartyService partyService;
  FeedService feedService;
  MemberService memberService;

  public MyController(PartyService partyService, FeedService feedService, MemberService memberService) {
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
  public String myProfile(Model model, HttpSession session) throws Exception {

    // 로그인 한 회원의 정보 출력
    // System.out.println("session.getAttribute(\"Loginmember\") = " + session.getAttribute("loginMember"));

    // 로그인 한 회원의 정보를 멤버 변수에 담는다
    Member member = (Member) session.getAttribute("loginMember");

    // 로그인 한 회원의 회원 번호 출력
    // System.out.println("member.getNo() = " + member.getNo());

    // 로그인 한 회원의 회원번호 가지고 ->  memberService ->
    // DefaultMemberService의 get(int no) 호출 -> return memberDao.findByNo(no); 호출 
    // -> MemberDao.xml 의 findByNo 호출
    model.addAttribute("member", memberService.get(member.getNo())); 

    // System.out.println("model.getAttribute(\"member\") = " + model.getAttribute("member")); // 로그인 한 회원정보
    return "mypage/myProfile";
  }

  // 마이페이지-파티게시글 관리
  @GetMapping("/myPartyList")
  public String myPartylist(Model model) throws Exception {
    model.addAttribute("parties", partyService.list());
    //    model.getAttribute("parties");
    //    System.out.println("parties data:" + model.getAttribute("parties")); // data 값 확인용
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
