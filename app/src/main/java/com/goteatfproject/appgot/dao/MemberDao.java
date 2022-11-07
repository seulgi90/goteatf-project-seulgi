package com.goteatfproject.appgot.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.goteatfproject.appgot.vo.Member;

@Mapper
public interface MemberDao {

  int insert(Member member);

  int update(Member member);

  Member findByNo(int no);

  List<Member> findAll();

  Member findByEmailPassword(@Param("id") String id, @Param("password") String password);

}
