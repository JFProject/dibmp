package cn.mldn.dibmp.vo;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Customer_record implements Serializable {
	public Long crid;
	public String cmid;
	public Date cdate;
	public Long criid;
	public String note;
	public Long getCrid() {
		return crid;
	}
	public void setCrid(Long crid) {
		this.crid = crid;
	}
	public String getCmid() {
		return cmid;
	}
	public void setCmid(String cmid) {
		this.cmid = cmid;
	}
	public Date getCdate() {
		return cdate;
	}
	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}
	public Long getCriid() {
		return criid;
	}
	public void setCriid(Long criid) {
		this.criid = criid;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	@Override
	public String toString() {
		return "Customer_record [crid=" + crid + ", cmid=" + cmid + ", cdate=" + cdate + ", criid=" + criid + ", note="
				+ note + "]";
	}
}
