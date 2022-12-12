package com.goteatfproject.appgot.vo;


public class Test {

  private String title;
  private String content;
  private String writer;
  private String date;
  private int no;

  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
  }
  public String getWriter() {
    return writer;
  }
  public void setWriter(String writer) {
    this.writer = writer;
  }
  public String getDate() {
    return date;
  }
  public void setDate(String date) {
    this.date = date;
  }
  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }

  @Override
  public String toString() {
    return "Test [title=" + title + ", content=" + content + ", writer=" + writer + ", date=" + date
        + ", no=" + no + "]";
  }

}
