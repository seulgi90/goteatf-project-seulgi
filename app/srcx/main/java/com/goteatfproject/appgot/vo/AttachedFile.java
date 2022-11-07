package com.goteatfproject.appgot.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter @Setter @ToString
public class AttachedFile {

    private int no;
    private String filepath;
    private int partyNo;
    private int ticketNo;
    private int feedNo;
    private int cs1No;

  public AttachedFile() {}

    public AttachedFile(String filepath) {
      this.filepath = filepath;
    }

  @Override
  public String toString() {
    return "AttachedFile{" +
        "no=" + no +
        ", filepath='" + filepath + '\'' +
        ", partyNo=" + partyNo +
        ", ticketNo=" + ticketNo +
        ", feedNo=" + feedNo +
        ", cs1No=" + cs1No +
        '}';
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public String getFilepath() {
    return filepath;
  }

  public void setFilepath(String filepath) {
    this.filepath = filepath;
  }

  public int getPartyNo() {
    return partyNo;
  }

  public void setPartyNo(int partyNo) {
    this.partyNo = partyNo;
  }

  public int getTicketNo() {
    return ticketNo;
  }

  public void setTicketNo(int ticketNo) {
    this.ticketNo = ticketNo;
  }

  public int getFeedNo() {
    return feedNo;
  }

  public void setFeedNo(int feedNo) {
    this.feedNo = feedNo;
  }

  public int getCs1No() {
    return cs1No;
  }

  public void setCs1No(int cs1No) {
    this.cs1No = cs1No;
  }
}
