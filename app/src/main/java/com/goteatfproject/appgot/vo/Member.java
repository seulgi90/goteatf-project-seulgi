package com.goteatfproject.appgot.vo;

import java.sql.Date;


public class Member {

  private int no;
  private String id;
  private String password;
  private String password2;
  private boolean grade;
  private String name;
  private String nick;
  private Date birth;
  private String tel;
  private String gender;
  private Date createdDate;
  private String post;
  private String address;
  private String subAddr;
  private String interest;
  private boolean outState;
  private Date outDate;
  private String profile;
  private String intro;
  @Override
  public String toString() {
    return "Member [no=" + no + ", id=" + id + ", password=" + password + ", password2=" + password2
        + ", grade=" + grade + ", name=" + name + ", nick=" + nick + ", birth=" + birth + ", tel="
        + tel + ", gender=" + gender + ", createdDate=" + createdDate + ", post=" + post
        + ", address=" + address + ", subAddr=" + subAddr + ", interest=" + interest + ", outState="
        + outState + ", outDate=" + outDate + ", profile=" + profile + ", intro=" + intro + "]";
  }
  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public String getPassword2() {
    return password2;
  }
  public void setPassword2(String password2) {
    this.password2 = password2;
  }
  public boolean isGrade() {
    return grade;
  }
  public void setGrade(boolean grade) {
    this.grade = grade;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getNick() {
    return nick;
  }
  public void setNick(String nick) {
    this.nick = nick;
  }
  public Date getBirth() {
    return birth;
  }
  public void setBirth(Date birth) {
    this.birth = birth;
  }
  public String getTel() {
    return tel;
  }
  public void setTel(String tel) {
    this.tel = tel;
  }
  public String getGender() {
    return gender;
  }
  public void setGender(String gender) {
    this.gender = gender;
  }
  public Date getCreatedDate() {
    return createdDate;
  }
  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }
  public String getPost() {
    return post;
  }
  public void setPost(String post) {
    this.post = post;
  }
  public String getAddress() {
    return address;
  }
  public void setAddress(String address) {
    this.address = address;
  }
  public String getSubAddr() {
    return subAddr;
  }
  public void setSubAddr(String subAddr) {
    this.subAddr = subAddr;
  }
  public String getInterest() {
    return interest;
  }
  public void setInterest(String interest) {
    this.interest = interest;
  }
  public boolean isOutState() {
    return outState;
  }
  public void setOutState(boolean outState) {
    this.outState = outState;
  }
  public Date getOutDate() {
    return outDate;
  }
  public void setOutDate(Date outDate) {
    this.outDate = outDate;
  }
  public String getProfile() {
    return profile;
  }
  public void setProfile(String profile) {
    this.profile = profile;
  }
  public String getIntro() {
    return intro;
  }
  public void setIntro(String intro) {
    this.intro = intro;
  }

}
