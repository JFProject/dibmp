package cn.mldn.dibmp.vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Subtype implements Serializable {
	public Long stid;
	public String title;
	public Long wiid;
	public Long getStid() {
		return stid;
	}
	public void setStid(Long stid) {
		this.stid = stid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Long getWiid() {
		return wiid;
	}
	public void setWiid(Long wiid) {
		this.wiid = wiid;
	}
	@Override
	public String toString() {
		return "Subtype [stid=" + stid + ", title=" + title + ", wiid=" + wiid + "]";
	}
}
