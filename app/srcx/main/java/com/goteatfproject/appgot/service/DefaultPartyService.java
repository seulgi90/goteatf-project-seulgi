package com.goteatfproject.appgot.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.goteatfproject.appgot.dao.PartyDao;
import com.goteatfproject.appgot.vo.Party;

@Service
public class DefaultPartyService implements PartyService {

  @Autowired
  PartyDao partyDao;

  @Transactional
  @Override
  public void add(Party party) throws Exception {
    // 1) 게시글 등록
    if (partyDao.insert(party) == 0) {
      throw new Exception("게시글 등록 실패!");
    }

    // 2) 첨부파일 등록
    //    if (party.getAttachedFiles().size() > 0) {
    //      partyDao.insertFiles(party);
    //    }
  }

  @Transactional
  @Override
  public boolean update(Party party) throws Exception {
    // 1) 게시글 변경
    if (partyDao.update(party) == 0) {
      return false;
    }

    //    // 2) 첨부파일 추가
    //    if (party.getAttachedFiles().size() > 0) {
    //      partyDao.insertFiles(party);
    //    }

    return true;
  }

  @Override
  public Party get(int no) throws Exception {
    return partyDao.findByNo(no); // 첨부파일 데이터까지 조인하여 select를 한 번만 실행한다.
  }

  @Transactional
  @Override
  public boolean delete(int no) throws Exception {
    // 1) 첨부파일 삭제
    partyDao.deleteFiles(no);

    // 2) 게시글 삭제
    return partyDao.delete(no) > 0;
  }

  @Override
  public List<Party> list() throws Exception {
    return partyDao.findAll();
  }
}
