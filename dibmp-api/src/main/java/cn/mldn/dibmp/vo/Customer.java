package cn.mldn.dibmp.vo;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Customer implements Serializable {
	public Long cuid;
	public String name;
	public String photo;
	public Long pid;
	public Long cid;
	public String address;
	public Date indate;
	public Integer connum;
	public Long ciid;
	public Long csid;
	public String note;
	public String recorder;
	public Long getCuid() {
		return cuid;
	}
	public void setCuid(Long cuid) {
		this.cuid = cuid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public Long getPid() {
		return pid;
	}
	public void setPid(Long pid) {
		this.pid = pid;
	}
	public Long getCid() {
		return cid;
	}
	public void setCid(Long cid) {
		this.cid = cid;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getIndate() {
		return indate;
	}
	public void setIndate(Date indate) {
		this.indate = indate;
	}
	public Integer getConnum() {
		return connum;
	}
	public void setConnum(Integer connum) {
		this.connum = connum;
	}
	public Long getCiid() {
		return ciid;
	}
	public void setCiid(Long ciid) {
		this.ciid = ciid;
	}
	public Long getCsid() {
		return csid;
	}
	public void setCsid(Long csid) {
		this.csid = csid;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getRecorder() {
		return recorder;
	}
	public void setRecorder(String recorder) {
		this.recorder = recorder;
	}
	@Override
	public String toString() {
		return "Customer [cuid=" + cuid + ", name=" + name + ", photo=" + photo + ", pid=" + pid + ", cid=" + cid
				+ ", address=" + address + ", indate=" + indate + ", connum=" + connum + ", ciid=" + ciid + ", csid="
				+ csid + ", note=" + note + ", recorder=" + recorder + "]";
	}
}
