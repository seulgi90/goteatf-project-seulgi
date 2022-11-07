package com.goteatfproject.appgot.service;

import com.goteatfproject.appgot.vo.AttachedFile;
import com.goteatfproject.appgot.vo.Criteria;
import com.goteatfproject.appgot.vo.Party;
import java.util.List;
import java.util.Map;

public interface PartyService {

  void add(Party party) throws Exception;
//  List<Party> list() throws Exception;
  Party get(int no) throws Exception;

  boolean update(Party party) throws Exception;

  boolean delete(int no) throws Exception;

  AttachedFile getAttachedFile(int fileNo) throws Exception;

  boolean deleteAttachedFile(int fileNo) throws Exception;

  List<Map<String, Object>> selectPartyList(Criteria cri);
}
