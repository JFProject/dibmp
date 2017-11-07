package cn.mldn.dibmp.vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Csource implements Serializable {
	private  Long csid;
	private String title;
	public Long getCsid() {
		return csid;
	}
	public void setCsid(Long csid) {
		this.csid = csid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "Csource [csid=" + csid + ", title=" + title + "]";
	}
}
