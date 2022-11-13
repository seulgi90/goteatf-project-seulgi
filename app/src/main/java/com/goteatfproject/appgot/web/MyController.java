package com.goteatfproject.appgot.web;

import com.goteatfproject.appgot.service.FeedService;
import com.goteatfproject.appgot.service.MemberService;
import com.goteatfproject.appgot.service.PartyService;
import com.goteatfproject.appgot.vo.Criteria;
import com.goteatfproject.appgot.vo.Member;
import com.goteatfproject.appgot.vo.PageMaker;
import com.goteatfproject.appgot.vo.Party;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.boot.Banner.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/my")
public class MyController {
  
  PartyService partyService;
  FeedService feedService;
  MemberService memberService;
  
  public MyController(PartyService partyService, FeedService feedService,
      MemberService memberService) {
    this.partyService = partyService;
    this.feedService = feedService;
    this.memberService = memberService;
  }
  
  // 마이페이지
  @GetMapping("/main")
  public String myPage(HttpSession session) throws Exception {
    Member loginMember = (Member) session.getAttribute("loginMember");
    if (loginMember != null) {
      return "mypage/myMain";
    }
    
    return "/auth/login";
  }
  
  
  // 마이페이지-파티게시글 관리
  @GetMapping("/myPartyList")
  public ModelAndView myPartyList(Criteria cri) throws Exception {
    
    // 기존에는 return에서 보냈으면 mv에서는 여기서 보냄
//    ModelAndView mv = new ModelAndView("party/partyList");
    ModelAndView mv = new ModelAndView();
    
    PageMaker pageMaker = new PageMaker();
    pageMaker.setCri(cri);
    pageMaker.setTotalCount(50);
    
    List<Map<String, Object>> myPartyList = partyService.selectPartyList(cri);
    mv.addObject("myPartyList", myPartyList);
    mv.addObject("pageMaker", pageMaker);
    
    mv.setViewName("mypage/myPartyList");
//    model.addAttribute("parties", partyService.list());
//    return "party/partyList";
    return mv;
  }
  
  // 마이페이지-피드게시글 관리
  @GetMapping("/myFeedList")
  public ModelAndView myFeedList(Criteria cri) throws Exception {
    
    ModelAndView mv = new ModelAndView();
    
    PageMaker pageMaker = new PageMaker();
    pageMaker.setCri(cri);
    pageMaker.setTotalCount(50);
    
    List<Map<String, Object>> myFeedList = feedService.selectFeedList(cri);
    mv.addObject("myFeedList", myFeedList);
    mv.addObject("pageMaker", pageMaker);
    
    mv.setViewName("mypage/myFeedList");
    
    return mv;
  }
  
  // 마이페이지- 개인 정보 수정
  @GetMapping("/myProfile")
  public String myProfile(Model model, HttpSession session) throws Exception {
    
    // 로그인 한 회원의 정보 출력
    // System.out.println("session.getAttribute(\"Loginmember\") = " + session.getAttribute("loginMember"));
    
    // 로그인 한 회원의 정보를 loginMember 변수에 담는다
    Member loginMember = (Member) session.getAttribute("loginMember");
    if (loginMember != null) {
      model.addAttribute("member", memberService.get(loginMember.getNo()));
      return "mypage/myProfile";
    }
    return "redirect:/auth/login";
  }
  
  // 마이페이지 개인 정보 수정 현재 패스워드 체크
  @PostMapping("/currentPassword")
  @ResponseBody
  public int currentPassword(HttpSession session, String password) throws Exception {
    Member loginMember = (Member) session.getAttribute("loginMember");
    int result = memberService.getCurrentPasswordCheck(loginMember.getNo(), password);
    return result;
  }
  
  @PostMapping("update")
  public String updateMember(Member member) throws Exception {
    System.out.println("member1111 = " + member);
    memberService.update(member);
    System.out.println("회원정보 수정 완료");
    return "redirect:/my/main";
  }
}
