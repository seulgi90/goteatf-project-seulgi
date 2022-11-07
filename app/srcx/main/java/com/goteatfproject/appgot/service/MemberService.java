package com.goteatfproject.appgot.service;

import java.util.List;
import com.goteatfproject.appgot.vo.Member;

public interface MemberService {

  void add(Member member) throws Exception;

  boolean update(Member member) throws Exception;

  Member get(int no) throws Exception;

  Member get(String id, String password) throws Exception;

  boolean delete(int no) throws Exception;

  List<Member> list() throws Exception;

}
