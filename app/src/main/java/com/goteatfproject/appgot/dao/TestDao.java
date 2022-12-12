package com.goteatfproject.appgot.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.goteatfproject.appgot.vo.Test;

@Mapper
public interface TestDao {

  int insert(Test test);

  List<Test> findAll();

  // 내가 짠 쿼리의 결과와 일치하는 vo 객체(Test)에 담아서 리턴
  Test detail(int no);

  int update(Test test);

  int remove(int no);
}
