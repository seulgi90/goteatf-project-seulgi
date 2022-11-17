package com.goteatfproject.appgot.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.goteatfproject.appgot.dao.BoardDao;
import com.goteatfproject.appgot.dao.PartyDao;
import com.goteatfproject.appgot.vo.AttachedFile;
import com.goteatfproject.appgot.vo.Board;
import com.goteatfproject.appgot.vo.Comment;
import com.goteatfproject.appgot.vo.Criteria;
import com.goteatfproject.appgot.vo.Party;

@Service
public class DefaultPartyService implements PartyService {

  @Autowired
  PartyDao partyDao;

  @Autowired
  BoardDao boardDao;

  @Override
  @Transactional
  public void add(Party party) throws Exception {

    if (partyDao.insert(party) == 0) {
      throw new Exception("게시글 등록 실패!");
    }

    if (party.getAttachedFiles().size() > 0) {
      partyDao.insertFiles(party);
    }
  }

  @Override
  public List<Party> list() throws Exception {
    return partyDao.findAll();
  }

  // 파티게시판 - 카테고리
  @Override
  public List<Party> list2(String meal, String food) throws Exception {
    return partyDao.findAll2(meal, food);
  }

  //페이징
  @Override
  public List<Map<String, Object>> selectPartyList(Criteria cri) {
    return partyDao.selectPartyList(cri);
  }

  @Override
  public Party get(int no) throws Exception {
    return partyDao.findByNo(no);
  }

  @Transactional
  @Override
  public boolean update(Party party) throws Exception {

    if (partyDao.update(party) == 0) {
      return false;
    }

    if (party.getAttachedFiles().size() > 0) {
      partyDao.insertFiles(party);
    }
    return true;
  }

  @Transactional
  @Override
  public boolean delete(int no) throws Exception {
    partyDao.deleteFiles(no);
    return partyDao.delete(no) > 0;
  }

  @Override
  public AttachedFile getAttachedFile(int fileNo) throws Exception {
    return partyDao.findFileByNo(fileNo);
  }

  @Override
  public boolean deleteAttachedFile(int fileNo) throws Exception {
    return partyDao.deleteFile(fileNo) > 0;
  }

  // 관리자페이지 이벤트+피드+파티 게시글 조회
  @Override
  public List<Board> listAll() throws Exception {
    System.out.println("newBoardDao = " + boardDao.findAll());
    return boardDao.findAll();
  }

  // 관리자 페이지 이벤트+피드+파티 오늘 등록된 게시글 개수
  @Override
  public List<Board> newBoardCount() throws Exception {
    return boardDao.newBoardCount();
  }

  // 댓글 테스트
  @Override
  public void insertComment(Comment comment) throws Exception {
    partyDao.insertComment(comment);
  }

  @Override
  public List<Comment> getCommentList(Comment comment) throws Exception {
    return partyDao.selectCommentList(comment);
  }

  @Override
  public boolean updateComment(Comment comment) throws Exception {

    return partyDao.updateComment(comment) != 0; // 넘어오는 값이 0이 아니면 true, 0이면 false
  }

  // 마이페이지 파티게시글 본인 글 리스트
  @Override
  public List<Map<String, Object>> selectPartyListByNo(Map<String, Object> map) {
    return partyDao.selectPartyListByNo(map);
  }

  // 마이페이지 파티게시글 본인 작성 글 상세보기
  @Override
  public Party getMyPartyListDetail(int no) throws Exception {
    return partyDao.findByMyPartyListDetail(no);
  }

  // 관리자페이지 파티게시글 비활성화
  @Override
  public boolean partyBlock(int no) throws Exception {
    return partyDao.partyBlock(no) > 0;
  }
}
