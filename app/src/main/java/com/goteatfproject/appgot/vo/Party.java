package com.goteatfproject.appgot.vo;

import java.util.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Party {

  private int no; 
  private String meal;
  private String food;
  private String title;
  private String content;
  //  private String nick;
  private String gender;
  private int max;

  //  @DateTimeFormat(iso = ISO.DATE, pattern = "yyyy-MM-dd'T'HH:mm:ss")
  //  @JsonProperty("time")
  //  @JsonFormat(shape= Shape.STRING, pattern="yyyy-MM-dd", timezone="Asia/Seoul")
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
  private String image;
  private String pub;
  private Date createDate;

  private Member writer;
  private String thumbnail;


  private List<AttachedFile> attachedFiles;

}
