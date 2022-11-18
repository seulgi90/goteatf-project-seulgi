package com.goteatfproject.appgot.web;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.goteatfproject.appgot.service.EventService;
import com.goteatfproject.appgot.service.FeedService;
import com.goteatfproject.appgot.service.MemberService;
import com.goteatfproject.appgot.service.PartyService;
import com.goteatfproject.appgot.vo.Criteria;
import com.goteatfproject.appgot.vo.Member;
import com.goteatfproject.appgot.vo.PageMaker;

@Controller
@RequestMapping("/my")
public class MyController {

  @Autowired
  PartyService partyService;

  @Autowired
  FeedService feedService;

  @Autowired
  MemberService memberService;

  @Autowired
  EventService eventService;

  @Autowired
  ServletContext sc;


  // 마이페이지
  @GetMapping("/main")
  public String myPage(HttpSession session, Model model) throws Exception {
    Member loginMember = (Member) session.getAttribute("loginMember");
    if (loginMember != null) {
      model.addAttribute("member", memberService.get(loginMember.getNo()));
      return "mypage/myMain";
    }

    return "/auth/login";
  }

  // 마이페이지- 개인 정보 수정 페이지
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

  // 마이페이지 개인 정보 수정
  @PostMapping("/update")
  public String updateMember(Member member) throws Exception {
    System.out.println("member = " + member);
    memberService.update(member);
    System.out.println("회원정보 수정 완료");
    return "redirect:/my/main";
  }

  // 마이페이지 메인 프로필 사진 수정
  @PostMapping("/updateProfile")
  public String updateProfile(@RequestParam("files") MultipartFile files, HttpSession session) throws Exception {
    Member member = (Member) session.getAttribute("loginMember");
    if (!files.isEmpty()) {
      String dirPath = sc.getRealPath("/member/files");
      String filename = UUID.randomUUID().toString();
      files.transferTo(new File(dirPath + "/" + filename));

      member.setThumbnail(filename);
      memberService.updateProfile(member);
      return "redirect:/my/main";
    } else {
      return "redirect:/my/main";
    }
  }

  // 마이페이지 메인 자기소개 수정
  @PostMapping("/updateIntro")
  public String updateIntro(String intro, HttpSession session) throws Exception {
    Member member = (Member) session.getAttribute("loginMember");

    member.setIntro(intro);

    memberService.updateIntro(member);
    System.out.println("member = " + member);
    System.out.println("회원정보 수정 완료");
    return "redirect:/my/main";
  }

  // 마이페이지 회원 비활성화
  @PostMapping("/delete")
  @ResponseBody
  public String delete(HttpSession session, int no) throws Exception {
    Member member = (Member) session.getAttribute("loginMember");
    if (member.getNo() == no) {
      memberService.delete(no);
      return "회원 탈퇴 완료";
    }
    return "회원 탈퇴 실패";
  }

  // 마이페이지-파티게시글 관리
  @GetMapping("/myPartyList")
  public ModelAndView myPartyList(Criteria cri, HttpSession session) throws Exception {

    Member loginMember = (Member) session.getAttribute("loginMember");

    ModelAndView mv = new ModelAndView();

    PageMaker pageMaker = new PageMaker();
    pageMaker.setCri(cri);
    pageMaker.setTotalCount(10);

    Map<String, Object> map = new HashMap<>();
    map.put("cri", cri);
    map.put("memberNo", loginMember.getNo());


    List<Map<String, Object>> myPartyList = partyService.selectPartyListByNo(map);

    mv.addObject("myPartyList", myPartyList);
    mv.addObject("pageMaker", pageMaker);

    mv.setViewName("mypage/myPartyList");
    //    model.addAttribute("parties", partyService.list());
    //    return "party/partyList";
    return mv;
  }

  // 마이페이지-피드게시글 관리
  @GetMapping("/myFeedList")
  public ModelAndView myFeedList(Criteria cri, HttpSession session) throws Exception {

    Member loginMember = (Member) session.getAttribute("loginMember");

    ModelAndView mv = new ModelAndView();

    PageMaker pageMaker = new PageMaker();
    pageMaker.setCri(cri);
    pageMaker.setTotalCount(10);

    Map<String, Object> map = new HashMap<>();
    map.put("cri", cri);
    map.put("memberNo", loginMember.getNo());

    List<Map<String, Object>> myFeedList = feedService.selectFeedListByNo(map);
    mv.addObject("myFeedList", myFeedList);
    mv.addObject("pageMaker", pageMaker);

    mv.setViewName("mypage/myFeedList");

    return mv;
  }

  // 마이페이지- 이벤트게시글 관리
  @GetMapping("/myEventList")
  public ModelAndView myEventList(Criteria cri, HttpSession session) throws Exception {

    Member loginMember = (Member) session.getAttribute("loginMember");

    ModelAndView mv = new ModelAndView();

    PageMaker pageMaker = new PageMaker();
    pageMaker.setCri(cri);
    pageMaker.setTotalCount(10);

    Map<String, Object> map = new HashMap<>();
    map.put("cri", cri);
    map.put("memberNo", loginMember.getNo());

    List<Map<String, Object>> myEventList = eventService.selectEventList2(map);
    mv.addObject("myEventList", myEventList);
    mv.addObject("pageMaker", pageMaker);

    mv.setViewName("mypage/myEventList");

    return mv;
  }

  // 마이페이지 파티게시글 본인 작성 글 상세보기
  @GetMapping("/myPartyListDetail")
  public String myPartyListDetail(Model model, HttpSession session, int no) throws Exception {

    Member loginMember = (Member) session.getAttribute("loginMember");


    if (loginMember != null) {
      model.addAttribute("party", partyService.getMyPartyListDetail(no));
      System.out.println("model.getAttribute(\"party\") = " + model.getAttribute("party"));
    }
    return "mypage/myPartyListDetail";
  }

  //마이페이지 피드게시글 본인 작성 글 상세보기
  @GetMapping("/myFeedListDetail")
  public String mFeedListDetail(Model model, HttpSession session, int no) throws Exception {

    Member loginMember = (Member) session.getAttribute("loginMember");


    if (loginMember != null) {
      model.addAttribute("feed", feedService.getMyFeedListDetail(no));
      System.out.println("model.getAttribute(\"feed\") = " + model.getAttribute("feed"));
    }
    return "mypage/myFeedListDetail";
  }
}
