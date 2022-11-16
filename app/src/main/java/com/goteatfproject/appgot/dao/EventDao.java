package com.goteatfproject.appgot.dao;

import com.goteatfproject.appgot.vo.AttachedFile;
import com.goteatfproject.appgot.vo.Criteria;
import com.goteatfproject.appgot.vo.Event;
import com.goteatfproject.appgot.vo.Party;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface EventDao {

  int insert(Event event);

  List<Event> findAll();

  List<Map<String, Object>> selectEventList(Criteria cri);

  Event findByNo(int no);

  int update(Event event);
  int delete(int no);
  int insertFiles(Event event);

  AttachedFile findFileByNo(int fileNo);

  List<AttachedFile> findFilesByEvent(int eventNo);

  int deleteFile(int fileNo);

  int deleteFiles(int eventNo);

  int deleteFilesByMemberEvents(int memberNo);


}