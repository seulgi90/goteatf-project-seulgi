package com.goteatfproject.appgot.service;

import java.util.List;
import com.goteatfproject.appgot.vo.Party;

public interface PartyService {

  void add(Party party) throws Exception;

  boolean update(Party party) throws Exception;

  Party get(int no) throws Exception;

  boolean delete(int no) throws Exception;

  List<Party> list() throws Exception;

}
