package com.goteatfproject.appgot.vo;

import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

@Getter @Setter @ToString
public class Party {

  private int no; 
  private String meal;
  private String food;
  private String title;
  private String content;
//  private String nick;
  private String gender;
  private int max;

  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
//  @JsonProperty("time")
//  @JsonFormat(shape= Shape.STRING, pattern="yyyy-MM-dd", timezone="Asia/Seoul")
  private Date time;
  private int age;
  private int limit;
  private String location;
  private String post;
  private String address;
  private int viewCnt;
  private boolean pub;
  private Date createDate;
  private String thumbnail;

  private Member writer;

  private List<AttachedFile> attachedFiles;

  private Comment commentList;

}
