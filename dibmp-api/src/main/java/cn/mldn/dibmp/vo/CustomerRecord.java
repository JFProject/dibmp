package cn.mldn.dibmp.vo;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class CustomerRecord implements Serializable {
	private Long crid;
	private String recordername;
	private Long cmid;
	private Date cdate;
	private Long criid;
	private String note;
	@Override
	public String toString() {
		return "CustomerRecord [crid=" + crid + ", recordername=" + recordername + ", cmid=" + cmid + ", cdate=" + cdate
				+ ", criid=" + criid + ", note=" + note + "]";
	}
	public Long getCrid() {
		return crid;
	}
	public void setCrid(Long crid) {
		this.crid = crid;
	}
	public String getRecordername() {
		return recordername;
	}
	public void setRecordername(String recordername) {
		this.recordername = recordername;
	}
	public Long getCmid() {
		return cmid;
	}
	public void setCmid(Long cmid) {
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

}
