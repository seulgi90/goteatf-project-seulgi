package com.goteatfproject.appgot.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.goteatfproject.appgot.vo.Follower;

@Mapper

public interface FollowerDao {

  void follow(Follower follower);

  void unfollow(Follower follower);

  int isFollow(Follower follower);

  //  List<Follower> userList(int follow);

  List<Follower> selectFollowList(int follow);

  List<Follower> selectFollowingList(int following);

  //마이페이지 팔로워 강제 삭제
  int allDelete3(int no);


}