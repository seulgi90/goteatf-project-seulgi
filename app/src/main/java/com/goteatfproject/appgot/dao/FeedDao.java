package com.goteatfproject.appgot.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import com.goteatfproject.appgot.vo.Criteria;
import com.goteatfproject.appgot.vo.Feed;
import com.goteatfproject.appgot.vo.FeedAttachedFile;

@Mapper

public interface FeedDao {

  List<Feed> findAll();

  int insert(Feed feed);

  List<Map<String, Object>> selectFeedList(Criteria cri);

  // 추가
  List<Feed> selectListByNick(String nick);
  List<Feed> randomfindAll();

  List<Feed> followFindAll(int no);
  List<Feed> simpleProfile(int no);

  Feed findByNo(int no);

  int update(Feed feed);

  int delete(int no);

  int insertFiles(Feed feed);

  FeedAttachedFile findFileByNo(int fileNo);

  List<FeedAttachedFile> findFilesByParty(int feedNo);

  int deleteFile(int fileNo);

  int deleteFiles(int feedNo);

  int deleteFilesByMemberFeeds(int memberNo);

  // 마이페이지-피드게시글 관리
  List<Map<String, Object>> selectFeedListByNo(Map<String, Object> map);
}