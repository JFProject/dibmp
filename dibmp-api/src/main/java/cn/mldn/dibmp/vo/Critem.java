package cn.mldn.dibmp.vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Critem implements Serializable {
	private Long criid;
	private String title;
	public Long getCriid() {
		return criid;
	}
	public void setCriid(Long criid) {
		this.criid = criid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "Critem [criid=" + criid + ", title=" + title + "]";
	}
}
