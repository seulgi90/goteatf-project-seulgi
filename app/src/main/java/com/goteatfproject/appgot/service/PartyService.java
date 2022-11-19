package com.goteatfproject.appgot.service;

import java.util.List;
import java.util.Map;
import com.goteatfproject.appgot.vo.AttachedFile;
import com.goteatfproject.appgot.vo.Board;
import com.goteatfproject.appgot.vo.Comment;
import com.goteatfproject.appgot.vo.Criteria;
import com.goteatfproject.appgot.vo.Party;

public interface PartyService {

  void add(Party party) throws Exception;
  List<Party> list() throws Exception;

  // 파티게시판 - 카테고리
  List<Party> list2(String meal, String food) throws Exception;

  //페이징
  List<Map<String, Object>> selectPartyList(Criteria criteria);
  Party get(int no) throws Exception;
  boolean update(Party party) throws Exception;
  boolean delete(int no) throws Exception;
  AttachedFile getAttachedFile(int fileNo) throws Exception;
  boolean deleteAttachedFile(int fileNo) throws Exception;



  // 관리자 페이지 이벤트+피드+파티 게시글 조회
  List<Board> listAll() throws Exception;

  // 관리자 페이지 이벤트+피드+파티 오늘 등록된 게시글 개수
  List<Board> newBoardCount() throws Exception;

  // 댓글 테스트
  public void insertComment(Comment comment) throws Exception;

  public List<Comment> getCommentList(Comment comment) throws Exception;

  boolean updateComment(Comment comment) throws Exception;

  // 마이페이지 파티게시글 본인 작성 글 리스트
  List<Map<String, Object>> selectPartyListByNo(Map<String, Object> map);

  // 마이페이지 파티게시글 본인 작성 글 상세보기
  // 관리자페이지 파티게시글 회원 작성 글 상세보기
  Party getMyPartyListDetail(int no) throws Exception;

  // 관리자페이지 파티게시글 비활성화
  boolean partyBlock(int no) throws Exception;
}
