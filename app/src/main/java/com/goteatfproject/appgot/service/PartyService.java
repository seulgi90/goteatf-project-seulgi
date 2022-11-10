package com.goteatfproject.appgot.service;

import java.util.List;
import com.goteatfproject.appgot.vo.AttachedFile;
import com.goteatfproject.appgot.vo.Party;

public interface PartyService {

  void add(Party party) throws Exception;

  List<Party> list() throws Exception;

  List<Party> list2(String meal, String food) throws Exception;

  Party get(int no) throws Exception;

  boolean update(Party party) throws Exception;

  boolean delete(int no) throws Exception;

  AttachedFile getAttachedFile(int fileNo) throws Exception;

  boolean deleteAttachedFile(int fileNo) throws Exception;
}
