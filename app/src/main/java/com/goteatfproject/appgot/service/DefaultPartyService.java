package com.goteatfproject.appgot.service;

import com.goteatfproject.appgot.dao.BoardDao;
import com.goteatfproject.appgot.dao.PartyDao;
import com.goteatfproject.appgot.vo.*;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DefaultPartyService implements PartyService {

  @Autowired
  PartyDao partyDao;
  
  @Autowired
  BoardDao boardDao;

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

  public AttachedFile getAttachedFile(int fileNo) throws Exception {
    return partyDao.findFileByNo(fileNo);
  }

  public boolean deleteAttachedFile(int fileNo) throws Exception {
    return partyDao.deleteFile(fileNo) > 0;
  }
  
  // 관리자페이지 이벤트+피드+파티 게시글 조회
  @Override
  public List<Board> listAll() throws Exception {
    System.out.println("newBoardDao = " + boardDao.findAll());
    return boardDao.findAll();
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
}
