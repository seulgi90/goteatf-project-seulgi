package com.goteatfproject.appgot.service;

import java.util.List;
import com.goteatfproject.appgot.vo.Member;

public interface MemberService {

  void add(Member member) throws Exception;

  Member get(int no) throws Exception;

  Member get(String id, String password) throws Exception;

  List<Member> list() throws Exception;


  // 밑에는 피드의 사용 기능

  // 팔로우 박스 랜덤 리스트 출력
  List<Member> randomList() throws Exception;

  // 유저 번호로 유저 프로필 정보 조회
  Member profileByNo(int no) throws Exception;

  Member profileByNick(String nick) throws Exception;

  // 마이페이지 현재 패스워드 확인
  int getCurrentPasswordCheck(int no, String password);

  // 마이페이지 회원정보 수정
  boolean update(Member member) throws Exception;

  // 마이페이지 회원 삭제
  boolean delete(int no) throws Exception; 

  // 마이페이지 회원 정보 프로필 사진 수정
  boolean updateProfile(Member member) throws Exception;

  // 마이페이지 회원 정보 자기소개 수정
  boolean updateIntro(Member member) throws Exception;

  // 관리자페이지 전체회원 limit 10;
  List<Member> MemberList() throws Exception;

  // 관리자페이지 신규회원 limit 10;
  List<Member> NewMemberList() throws Exception;

  // 관리자페이지 회원 상세정보
  Member getMemberDetail(int no) throws Exception;

  // 관리자페이지 회원리스트 정보검색
  List<Member> getSearchMember(String keyword);

  boolean memberBlock(int no);

  boolean memberActive(int no);


}
