package com.goteatfproject.appgot.service;

import java.util.List;
import com.goteatfproject.appgot.vo.Feed;

public interface FeedService {

  Feed get(int no) throws Exception;

  List<Feed> list() throws Exception;

}
