package cn.mldn.dibmp.vo;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class StorageRecord implements Serializable {
	private Long srid;
	private Long said;
	private Long gid;
	private Long wid;
	private String name;
	private Integer num;
	private Double price;
	private Double weight;
	private String inmid;
	private Date auditDate;
	public void setWid(Long wid) {
		this.wid = wid;
	}
	public Long getWid() {
		return wid;
	}
	public Long getSrid() {
		return srid;
	}
	public void setSrid(Long srid) {
		this.srid = srid;
	}
	public Long getSaid() {
		return said;
	}
	public void setSaid(Long said) {
		this.said = said;
	}
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
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
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
	public String getInmid() {
		return inmid;
	}
	public void setInmid(String inmid) {
		this.inmid = inmid;
	}
	public Date getAuditDate() {
		return auditDate;
	}
	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}
	
}
