package com.goteatfproject.appgot.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.goteatfproject.appgot.dao.FeedDao;
import com.goteatfproject.appgot.vo.Feed;

@Service
public class DefaultFeedService implements FeedService {

  @Autowired
  FeedDao feedDao;

  @Override
  public Feed get(int no) throws Exception {
    return feedDao.findByNo(no);
  }

  @Override
  public List<Feed> list() throws Exception {
    return feedDao.findAll();
  }

}
