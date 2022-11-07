package com.goteatfproject.appgot.vo;


public class AttachedFile {

  private int no;
  private String fileName;
  private String filepath;
  private String saveName;
  private int partyNo;

  public AttachedFile() {}

  // PartyController - attachedFiles.add(new AttachedFile(filename));
  public AttachedFile(String filepath) {
    this.filepath = filepath;
  }

  @Override
  public String toString() {
    return "AttachedFile [no=" + no + ", fileName=" + fileName + ", filepath=" + filepath
        + ", saveName=" + saveName + ", partyNo=" + partyNo + "]";
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public String getFilepath() {
    return filepath;
  }

  public void setFilepath(String filepath) {
    this.filepath = filepath;
  }

  public String getSaveName() {
    return saveName;
  }

  public void setSaveName(String saveName) {
    this.saveName = saveName;
  }

  public int getPartyNo() {
    return partyNo;
  }

  public void setPartyNo(int partyNo) {
    this.partyNo = partyNo;
  }

}
