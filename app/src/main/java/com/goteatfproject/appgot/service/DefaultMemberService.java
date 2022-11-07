package com.goteatfproject.appgot.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.goteatfproject.appgot.dao.MemberDao;
import com.goteatfproject.appgot.vo.Member;

@Service
public class DefaultMemberService implements MemberService {

  @Autowired
  MemberDao memberDao;

  @Override
  public void add(Member member) throws Exception {
    memberDao.insert(member);
  }

  @Override
  public boolean update(Member member) throws Exception {
    return memberDao.update(member) > 0;
  }

  @Override
  public Member get(int no) throws Exception {
    return memberDao.findByNo(no);
  }

  @Override
  public Member get(String id, String password) throws Exception {
    return memberDao.findByEmailPassword(id, password);
  }

  @Override
  public List<Member> list() throws Exception {
    return memberDao.findAll();
  }
}
