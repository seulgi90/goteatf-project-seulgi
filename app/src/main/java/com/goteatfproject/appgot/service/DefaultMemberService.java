package com.goteatfproject.appgot.service;

import com.goteatfproject.appgot.dao.MemberDao;
import com.goteatfproject.appgot.vo.Member;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultMemberService implements MemberService {

  @Autowired
  MemberDao memberDao;

  @Override
  public void add(Member member) throws Exception {
    memberDao.insert(member);
  }

  @Override
  public Member get(int no) throws Exception {
    return memberDao.findByNo(no);
  }

  @Override
  public Member get(String id, String password) throws Exception {
    return memberDao.findByEmailPassword(id, password);
  }

  public List<Member> list() throws Exception {
    return memberDao.findAll();
  }

  // 밑에는 피드의 사용 기능

  public List<Member> randomList() throws Exception {
    return memberDao.userList();
  }

  public Member profileByNo(int no) throws Exception {
    return memberDao.profileByNo(no);
  }

  public Member profileByNick(String nick) throws Exception {
    return memberDao.profileByNick(nick);
  }
  
  // 관리자페이지 전체회원 limit 10;
  @Override
  public List<Member> MemberList() throws Exception {
    return memberDao.findByMember();
  }
  
  // 관리자페이지 신규회원 limit 10;
  @Override
  public List<Member> NewMemberList() throws Exception {
    return memberDao.findByNewMember();
  }
  
  // 관리자페이지 회원 상세정보
  @Override
  public Member getMemberDetail(int no) throws Exception {
    return memberDao.findByMemberDetail(no);
  }
  
  // 관리자페이지 회원정보 검색
  @Override
  public List<Member> getSearchMember(String keyword) {
    return memberDao.searchMember(keyword);
  }
  
  // 마이페이지 현재 패스워드 확인
  @Override
  public int getCurrentPasswordCheck(int no, String password) {
    return memberDao.currentPasswordCheck(no, password);
  }
  
  // 마이페이지 회원정보 수정
  @Override
  public boolean update(Member member) throws Exception {
    return memberDao.update(member) > 0;
  }
}
