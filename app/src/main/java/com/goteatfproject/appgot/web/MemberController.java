package com.goteatfproject.appgot.web;

import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.goteatfproject.appgot.service.MemberService;
import com.goteatfproject.appgot.vo.Member;

@Controller
@RequestMapping("/register")
public class MemberController {

  MemberService memberService;

  public MemberController(MemberService memberService) {
    System.out.println("MemberController() 호출됨!");
    this.memberService = memberService;
  }

  @GetMapping ("/add")
  public String add() throws Exception {
    return "member/form";
  }

  //  @PostMapping("/save")
  //  public String save(User user) throws Exception {
  //    return "memberInfo";
  //  }

  @PostMapping("/add")
  public String add(Member member) throws Exception {
    memberService.add(member);
    return "redirect:list";
  }

  @GetMapping("/list")
  public String list(Model model) throws Exception {
    model.addAttribute("members", memberService.list());
    return "member/list";
  }

  @GetMapping("detail")
  public void detail(int no, Map map) throws Exception {
    Member member = memberService.get(no);

    if (member == null) {
      throw new Exception("해당 번호의 회원이 없습니다.");
    }

    map.put("member", member);
  }

  @PostMapping("update")
  public String update(Member member) throws Exception {
    if (!memberService.update(member)) {
      throw new Exception("회원 변경 오류입니다!");
    }

    return "redirect:list";
  }
}
