package com.goteatfproject.appgot.web;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.goteatfproject.appgot.service.PartyService;
import com.goteatfproject.appgot.vo.Member;
import com.goteatfproject.appgot.vo.Party;

@Controller
@RequestMapping("/party")
public class PartyController {

  ServletContext sc;
  PartyService partyService;

  public PartyController(PartyService partyService, ServletContext sc) {
    System.out.println("PartyController() 호출됨!!");
    this.partyService = partyService;
    this.sc = sc;
  }

  @GetMapping("/form")
  public void form() throws Exception {
  }

  @PostMapping("/add")
  public String add(
      Party party,
      //          MultipartFile[] files,
      HttpSession session) throws Exception {

    //    party.setAttachedFiles(saveAttachedFiles(files));
    party.setWriter((Member) session.getAttribute("loginMember"));

    partyService.add(party);
    return "redirect:list";
  }

  @GetMapping("/list")
  public void list(Model model) throws Exception {
    model.addAttribute("parties", partyService.list());
  }

  @GetMapping("/detail")
  public Map detail(int no) throws Exception {
    Party party = partyService.get(no);
    if (party == null) {
      throw new Exception("해당 번호의 게시글이 없습니다!");
    }

    Map map = new HashMap();
    map.put("party", party);
    return map;
  }

  @PostMapping("/update")
  public String update(
      Party party,
      Part[] files,
      HttpSession session)
          throws Exception {

    //    party.setAttachedFiles(saveAttachedFiles(files));

    checkOwner(party.getNo(), session);

    if (!partyService.update(party)) {
      throw new Exception("게시글을 변경할 수 없습니다!");
    }

    return "redirect:list";
  }

  private void checkOwner(int partyNo, HttpSession session) throws Exception {
    Member loginMember = (Member) session.getAttribute("loginMember");
    if (partyService.get(partyNo).getWriter().getNo() != loginMember.getNo()) {
      throw new Exception("게시글 작성자가 아닙니다.");
    }
  }


  @GetMapping("/delete")
  public String delete(
      int no,
      HttpSession session)
          throws Exception {

    checkOwner(no, session);
    if (!partyService.delete(no)) {
      throw new Exception("게시글을 삭제할 수 없습니다.");
    }

    return "redirect:list";
  }

  // 파티 리스트 게시물 등록

  //  @GetMapping("/partyAdd")
  //  public String add(Party party, HttpSession session) throws Exception {
  //
  //    party.setWriter((User) session.getAttribute("loginMember"));
  //
  //    partyService.
  //
  //    return "board/partyAdd";
  //  }

}
