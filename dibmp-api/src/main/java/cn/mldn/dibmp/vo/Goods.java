package cn.mldn.dibmp.vo;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Goods implements Serializable {
	public Long gid;
	public String name;
	public Long wiid;
	public Long stid;
	public Double price;
	public Double weight;
	public String photo;
	public String note;
	public Date lastin;
	public Integer stornum;
	public String recorder;
	public Integer delflag;
	public Long getGid() {
		return gid;
	}
	public void setGid(Long gid) {
		this.gid = gid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getWiid() {
		return wiid;
	}
	public void setWiid(Long wiid) {
		this.wiid = wiid;
	}
	public Long getStid() {
		return stid;
	}
	public void setStid(Long stid) {
		this.stid = stid;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Date getLastin() {
		return lastin;
	}
	public void setLastin(Date lastin) {
		this.lastin = lastin;
	}
	public Integer getStornum() {
		return stornum;
	}
	public void setStornum(Integer stornum) {
		this.stornum = stornum;
	}
	public String getRecorder() {
		return recorder;
	}
	public void setRecorder(String recorder) {
		this.recorder = recorder;
	}
	public Integer getDelflag() {
		return delflag;
	}
	public void setDelflag(Integer delflag) {
		this.delflag = delflag;
	}
	@Override
	public String toString() {
		return "Goods [gid=" + gid + ", name=" + name + ", wiid=" + wiid + ", stid=" + stid + ", price=" + price
				+ ", weight=" + weight + ", photo=" + photo + ", note=" + note + ", lastin=" + lastin + ", stornum="
				+ stornum + ", recorder=" + recorder + ", delflag=" + delflag + "]";
	}
}
