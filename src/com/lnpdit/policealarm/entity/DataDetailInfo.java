package com.lnpdit.policealarm.entity;

public class DataDetailInfo {
    private String Id; // id
    private String tagName; // 案件类型
    private String coordinateX; // 经度
    private String coordinateY; // 纬度
    private String videoName; // 视频
    private String audioName; // 音频
    private String adress; // 位置
    private String textContents; // 文本
    private String pic1; // 图片
    private String pic2; // 图片
    private String pic3; // 图片
    private String pic4; // 图片
    private String pic5; // 图片
    private String pic6; // 图片
    private String CTime; // 时间 
    
    public String getId() {
        return Id;
    }
    public String getCoordinateX() {
        return coordinateX;
    }
    public String getAdress() {
        return adress;
    }
    public void setAdress(String adress) {
        this.adress = adress;
    }
    public void setCoordinateX(String coordinateX) {
        this.coordinateX = coordinateX;
    }
    public String getCoordinateY() {
        return coordinateY;
    }
    public void setCoordinateY(String coordinateY) {
        this.coordinateY = coordinateY;
    }
    public String getVideoName() {
        return videoName;
    }
    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }
    public String getAudioName() {
        return audioName;
    }
    public void setAudioName(String audioName) {
        this.audioName = audioName;
    }
    public String getTextContents() {
        return textContents;
    }
    public void setTextContents(String textContents) {
        this.textContents = textContents;
    }
    public String getPic1() {
        return pic1;
    }
    public void setPic1(String pic1) {
        this.pic1 = pic1;
    }
    public String getPic2() {
        return pic2;
    }
    public void setPic2(String pic2) {
        this.pic2 = pic2;
    }
    public String getPic3() {
        return pic3;
    }
    public void setPic3(String pic3) {
        this.pic3 = pic3;
    }
    public String getPic4() {
        return pic4;
    }
    public void setPic4(String pic4) {
        this.pic4 = pic4;
    }
    public String getPic5() {
        return pic5;
    }
    public void setPic5(String pic5) {
        this.pic5 = pic5;
    }
    public String getPic6() {
        return pic6;
    }
    public void setPic6(String pic6) {
        this.pic6 = pic6;
    }
    public void setId(String id) {
        Id = id;
    }
    public String getTagName() {
        return tagName;
    }
    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
    public String getCTime() {
        return CTime;
    }
    public void setCTime(String cTime) {
        CTime = cTime;
    }
    
    
    
}
