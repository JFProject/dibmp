package cn.mldn.dibmp.vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Witem implements Serializable {
	private Long wiid;
	private String title;
	public Long getWiid() {
		return wiid;
	}
	public void setWiid(Long wiid) {
		this.wiid = wiid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "Witem [wiid=" + wiid + ", title=" + title + "]";
	}
}
