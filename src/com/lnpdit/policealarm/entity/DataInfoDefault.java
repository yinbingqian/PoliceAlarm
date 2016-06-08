package com.lnpdit.policealarm.entity;

public class DataInfoDefault {
    private String Id; // id
    private String tagName; // 案件类型
    private String state; // 处理状态
    private String CTime; // 时间
    public String getId() {
        return Id;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
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
