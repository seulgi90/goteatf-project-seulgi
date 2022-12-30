package com.goteatfproject.appgot.web;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
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
import com.goteatfproject.appgot.service.BoardService;
import com.goteatfproject.appgot.service.EventService;
import com.goteatfproject.appgot.service.FeedService;
import com.goteatfproject.appgot.service.FollowerService;
import com.goteatfproject.appgot.service.MemberService;
import com.goteatfproject.appgot.service.PartyService;
import com.goteatfproject.appgot.vo.AttachedFile;
import com.goteatfproject.appgot.vo.Criteria;
import com.goteatfproject.appgot.vo.Feed;
import com.goteatfproject.appgot.vo.FeedAttachedFile;
import com.goteatfproject.appgot.vo.Follower;
import com.goteatfproject.appgot.vo.Member;
import com.goteatfproject.appgot.vo.PageMaker;
import com.goteatfproject.appgot.vo.Party;

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
  FollowerService followerService;
  @Autowired
  BoardService boardService;
  @Autowired
  ServletContext sc;

  // 마이페이지
  @GetMapping("/main")
  public String myPage(HttpSession session, Model model) throws Exception {
    Member loginMember = (Member) session.getAttribute("loginMember");
    if (loginMember != null) {
      model.addAttribute("member", memberService.get(loginMember.getNo()));
      model.addAttribute("boards", boardService.myListAll(loginMember.getNo()));

      return "mypage/myMain";
    }
    return "/auth/login";
  }

  // 마이페이지- 개인 정보 수정 페이지
  @GetMapping("/myProfile")
  public String myProfile(Model model, HttpSession session, String password) throws Exception {

    // 로그인 한 회원의 정보 출력
    // System.out.println("session.getAttribute(\"Loginmember\") = " + session.getAttribute("loginMember"));

    // 로그인 한 회원의 정보를 loginMember 변수에 담는다
    Member loginMember = (Member) session.getAttribute("loginMember");
    if (loginMember != null) {
      model.addAttribute("member", memberService.get(loginMember.getNo()));
      System.out.println("member=" + model.getAttribute("member"));
      return "mypage/myProfile";
    }
    return "redirect:/auth/login";
  }

  //마이페이지 개인정보수정 페이지 패스워드 체크 페이지
  @GetMapping("/myAuthForm")
  public String myAuthForm() throws Exception {
    return "mypage/myAuthForm";
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
    System.out.println(member.getPassword() == "");
    // 새로운 패스워드가 없을때는 udpate2()
    if (member.getPassword() == "") {
      memberService.update2(member);
    } else { // 새로운 패스워드 변경이 있을때 update()
      memberService.update(member);
    }
    System.out.println("회원정보 수정 완료");
    return "redirect:/my/main";
  }

  // 마이페이지 메인 프로필 사진 수정
  // @RequestParam 생략할 수 있는 경우 클라이언트에서 보내는 파라미터 변수이름과 값을 받을 변수의 이름이 같을경우
  // 스프링에서 제공하는 MultiPartFile 타입으로 첨부파일을 받는다
  @PostMapping("/updateProfile")
  public String updateProfile(@RequestParam("files") MultipartFile files, HttpSession session)
      throws Exception {
    Member member = (Member) session.getAttribute("loginMember");

    if (!files.isEmpty()) {

      // sc.getRealPath("/"); : 이름이 지정된 응용 프로그램 /이 있을 때까지 경로를 제공
      // 임시 폴더에 저장된 파일을 옮길 폴더 경로 알아내기
      String dirPath = sc.getRealPath("/member/files");

      // 파일명은 원래의 이름 대신 UUID로 생성한 이름(회원들간 파일명 중복방지)
      String filename = UUID.randomUUID().toString();

      // 현재 어느 폴더에 저장하는건지 알아내기위함
      System.out.println(dirPath + "/" + filename); 

      // transferTo(); 메서드에 의해 지정한 위치에 생성한 이름으로 첨부파일을 저장
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
  public String delete(HttpSession session, @RequestParam("no") int no,
      @RequestParam("outState") boolean outState) throws Exception {

    System.out.println("out =====> " + outState);

    if (outState != false) {
      Member member = (Member) session.getAttribute("loginMember");
      if (member.getNo() == no) {
        memberService.delete(no);
        session.invalidate();
        return "1";
        //        return "회원 탈퇴 완료";
      }
    }
    return "0";
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

  // 마이페이지 파티게시글 강제 삭제
  @GetMapping("/myPartyDelete")
  public String allDelete(int no) throws Exception {
    partyService.allDelete(no);
    return "redirect:myPartyList";
  }

  // 마이페이지 파티게시글 강제삭제 체크박스 선택
  @PostMapping("/partyDeletes")
  @ResponseBody
  public String partyDeletes(@RequestParam("checkedValue[]") int[] checkedValue) throws Exception {
    int valueLength = checkedValue.length;

    for (int i = 0; i < valueLength; i++) {
      System.out.println(checkedValue[i]);
      partyService.allDelete(checkedValue[i]);
    }
    return "삭제 성공";
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

  // 마이페이지 피드 게시물 수정
  @PostMapping("/updateFeed")
  public String updateFeed(Feed feed, HttpSession session) throws Exception {

    checkOwner(feed.getNo(), session);

    feedService.update(feed);

    return "redirect:myFeedList";
  }

  private void checkOwner(int feedNo, HttpSession session) throws Exception {
    Member loginMember = (Member) session.getAttribute("loginMember");

    if (feedService.get(feedNo).getWriter().getNo() != loginMember.getNo()) {
      throw new Exception("게시글 작성자가 아닙니다.");
    }
  }

  // 마이페이지 피드게시글 강제 삭제
  @GetMapping("/myFeedDelete")
  public String allDelete2(int no) throws Exception {
    feedService.allDelete2(no);
    return "redirect:myFeedList";
  }

  // 마이페이지 피드게시글 강제삭제 체크박스 선택
  @PostMapping("/feedDeletes")
  @ResponseBody
  public String feedDeletes(@RequestParam("checkedValue[]") int[] checkedValue) throws Exception {
    int valueLength = checkedValue.length;

    for (int i = 0; i < valueLength; i++) {
      System.out.println(checkedValue[i]);
      feedService.allDelete2(checkedValue[i]);
    }
    return "삭제 성공";
  }

  // 마이페이지 피드게시물 첨부파일 삭제
  @GetMapping("/fileDelete")
  public String fileDelete(int no, HttpSession session) throws Exception {

    // 첨부파일 정보 가져오기
    FeedAttachedFile feedAttachedFile = feedService.getFeedAttachedFile(no);

    System.out.println("feedAttachedFile.getNo() = " + feedAttachedFile.getNo());
    System.out.println("feedAttachedFile.getNo() = " + feedAttachedFile.getFilepath());
    System.out.println("feedAttachedFile.getNo() = " + feedAttachedFile.getFeedNo());

    // 게시글 작성자 일치여부
    Member loginMember = (Member) session.getAttribute("loginMember");
    Feed feed = feedService.get(feedAttachedFile.getFeedNo());
    System.out.println("feed = " + feed);

    // feedVO의 getWriter 통해서 member getNo 접근 != 로그인No 와 일치여부
    if (feed.getWriter().getNo() != loginMember.getNo()) {
      throw new Exception("게시글 작성자가 아닙니다.");
    }
    // 첨부파일 삭제
    if (!feedService.deleteFeedAttachedFile(no)) {
      throw new Exception("게시글 첨부파일을 삭제할 수 없습니다.");
    }
    return "redirect:myFeedListDetail?no=" + feed.getNo();
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
  public String myFeedListDetail(Model model, HttpSession session, int no) throws Exception {

    Member loginMember = (Member) session.getAttribute("loginMember");

    if (loginMember != null) {
      model.addAttribute("feed", feedService.getMyFeedListDetail(no));
      System.out.println("model.getAttribute(\"feed\") = " + model.getAttribute("feed"));
    }
    return "mypage/myFeedListDetail";
  }

  // 마이페이지 팔로우 관리
  @GetMapping("/myFollowList")
  public String myFollowList(Model model, HttpSession session) throws Exception {

    Member loginMember = (Member) session.getAttribute("loginMember");

    if (loginMember != null) {
      List<Follower> followList = followerService.selectFollowList(loginMember.getNo());
      model.addAttribute("follows", followList);
      System.out.println("model.getAttribute(\"follows\") = " + model.getAttribute("follows"));
    }
    return "mypage/myFollowList";
  }

  // 마이페이지 팔로워 강제 삭제
  @GetMapping("/myFollowDelete")
  public String allDelete3(int no) throws Exception {
    followerService.allDelete3(no);
    return "redirect:myFeedList";
  }

  // 마이페이지 팔로워 강제삭제 체크박스 선택
  @PostMapping("/followDeletes")
  @ResponseBody
  public String followDeletes(@RequestParam("checkedValue[]") int[] checkedValue) throws Exception {
    int valueLength = checkedValue.length;

    for (int i = 0; i < valueLength; i++) {
      System.out.println(checkedValue[i]);
      followerService.allDelete3(checkedValue[i]);
    }
    return "삭제 성공";
  }

  // 파티 게시물 수정
  @PostMapping("updateParty")
  public String update(Party party, HttpSession session,
      Part[] files) throws Exception {

    party.setAttachedFiles(saveAttachedFiles(files));

    // detail.html : <input name="no" type="number" value="1" th:value="${party.no}" readonly hidden/>
    // 위에 추가해야 party.getNo() 가져오기 가능 System.out.println("partyNo = " + party.getNo());
    checkOwner2(party.getNo(), session);

    if (!partyService.update(party)) {
      throw new Exception("게시글을 변경할 수 없습니다.");
    }
    return "redirect:myPartyList";
  }

  private void checkOwner2(int partyNo, HttpSession session) throws Exception {
    Member loginMember = (Member) session.getAttribute("loginMember");
    // 개인이해메모
    // getWriter().getNo() != loginMember.getNo() // 로그인 멤버no 꺼내서 party에 있는 Member writer 이용해서 일치여부 확인
    // 방향 ----->
    if (partyService.get(partyNo).getWriter().getNo() != loginMember.getNo()) {
      throw new Exception("파티 게시글 작성자가 아닙니다.");
    }
  }

  private List<AttachedFile> saveAttachedFiles(Part[] files)
      throws IOException, ServletException {
    List<AttachedFile> attachedFiles = new ArrayList<>();
    String dirPath = sc.getRealPath("/party/files");

    for (Part part : files) {
      if (part.getSize() == 0) {
        continue;
      }
      String filename = UUID.randomUUID().toString();
      part.write(dirPath + "/" + filename);
      attachedFiles.add(new AttachedFile(filename));
    }
    return attachedFiles;
  }

  private List<AttachedFile> saveAttachedFiles(MultipartFile[] files)
      throws IOException, ServletException {
    List<AttachedFile> attachedFiles = new ArrayList<>();
    String dirPath = sc.getRealPath("/party/files");

    for (MultipartFile part : files) {
      if (part.isEmpty()) {
        continue;
      }

      System.out.println("filename3 = " + Arrays.toString(files));
      System.out.println("filename4 = " + files);
      System.out.println("dirPath = " + dirPath);

      String filename = UUID.randomUUID().toString();
      part.transferTo(new File(dirPath + "/" + filename));
      attachedFiles.add(new AttachedFile(filename));
    }
    return attachedFiles;
  }

  // 마이페이지 예약 내역 관리
  @GetMapping("/myReservationList")
  public String myReservationList(Model model, HttpSession session) throws Exception {
    Member loginMember = (Member) session.getAttribute("loginMember");
    if (loginMember != null) {
      model.addAttribute("tickets", eventService.getTicketNo(loginMember.getNo()));
      return "mypage/myReservationList";
    }
    return "/auth/login";
  }

}
