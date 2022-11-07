package com.goteatfproject.appgot.vo;

import java.util.Date;


public class Feed {

  private int no;
  private String title;
  private String content;
  private Date date;
  private String image;

  private Member writer;

  @Override
  public String toString() {
    return "Feed [no=" + no + ", title=" + title + ", content=" + content + ", date=" + date
        + ", image=" + image + ", writer=" + writer + "]";
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

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

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public Member getWriter() {
    return writer;
  }

  public void setWriter(Member writer) {
    this.writer = writer;
  }

}
