package cn.mldn.dibmp.vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Citem implements Serializable {
	private Long ciid;
	private String title;
	public Long getCiid() {
		return ciid;
	}
	public void setCiid(Long ciid) {
		this.ciid = ciid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "Citem [ciid=" + ciid + ", title=" + title + "]";
	}
}
