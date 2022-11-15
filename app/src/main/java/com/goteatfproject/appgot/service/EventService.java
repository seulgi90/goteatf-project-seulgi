package com.goteatfproject.appgot.service;

import com.goteatfproject.appgot.vo.AttachedFile;
import com.goteatfproject.appgot.vo.Criteria;
import com.goteatfproject.appgot.vo.Event;
import com.goteatfproject.appgot.vo.Party;

import java.util.List;
import java.util.Map;

public interface EventService {

  void add(Event event) throws Exception;
  List<Event> list() throws Exception;

  //페이징
  List<Map<String, Object>> selectEventList(Criteria criteria);
  Event get(int no) throws Exception;
  boolean update(Event event) throws Exception;
  boolean delete(int no) throws Exception;
  AttachedFile getAttachedFile(int fileNo) throws Exception;
  boolean deleteAttachedFile(int fileNo) throws Exception;



}
