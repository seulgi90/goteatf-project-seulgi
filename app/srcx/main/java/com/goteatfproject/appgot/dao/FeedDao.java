package com.goteatfproject.appgot.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.goteatfproject.appgot.vo.Feed;

@Mapper

public interface FeedDao {

  Feed findByNo(int no);

  List<Feed> findAll();

}