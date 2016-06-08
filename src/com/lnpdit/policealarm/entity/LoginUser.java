package com.lnpdit.policealarm.entity;

public class LoginUser {
	private String Id; // 用户id
	private String serialNum;// 警员编号
	private String passWd;// 用户密码
	private String sex;// 用户性别
	private String zone;// 地域
	private String crTime;// 时间
    public String getId() {
        return Id;
    }
    public void setId(String id) {
        Id = id;
    }
    public String getSerialNum() {
        return serialNum;
    }
    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }
    public String getPassWd() {
        return passWd;
    }
    public void setPassWd(String passWd) {
        this.passWd = passWd;
    }
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getZone() {
        return zone;
    }
    public void setZone(String zone) {
        this.zone = zone;
    }
    public String getCrTime() {
        return crTime;
    }
    public void setCrTime(String crTime) {
        this.crTime = crTime;
    }
	
	
}
