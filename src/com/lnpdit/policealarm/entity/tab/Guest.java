package com.lnpdit.policealarm.entity.tab;

import java.io.Serializable;

public class Guest implements Serializable {
	private String id;
	private String analysis;
	private String headpic;
	private String webid;
	private String level;
	private String paidmark;
	private String realname;
	private String stockstyle;
	private String resume;

	public Guest() {

	}

	public Guest(String analysis, String headpic, String webid, String level,
			String paidmark, String realname, String stockstyle, String resume) {
		this.analysis = analysis;
		this.headpic = headpic;
		this.webid = webid;
		this.level = level;
		this.paidmark = paidmark;
		this.realname = realname;
		this.stockstyle = stockstyle;
		this.resume = resume;
	}

	public String getid() {
		return id;
	}

	public void setid(String id) {
		this.id = id;
	}

	public String getanalysis() {
		return analysis;
	}

	public void setanalysis(String analysis) {
		this.analysis = analysis;
	}

	public String getheadpic() {
		return headpic;
	}

	public void setheadpic(String headpic) {
		this.headpic = headpic;
	}

	public String getwebid() {
		return webid;
	}

	public void setwebid(String webid) {
		this.webid = webid;
	}

	public String getlevel() {
		return level;
	}

	public void setlevel(String level) {
		this.level = level;
	}

	public String getpaidmark() {
		return paidmark;
	}

	public void setpaidmark(String paidmark) {
		this.paidmark = paidmark;
	}

	public String getrealname() {
		return realname;
	}

	public void setrealname(String realname) {
		this.realname = realname;
	}

	public String getstockstyle() {
		return stockstyle;
	}

	public void setstockstyle(String stockstyle) {
		this.stockstyle = stockstyle;
	}

	public String getresume() {
		return resume;
	}

	public void setresume(String resume) {
		this.resume = resume;
	}
}
