package cn.mldn.dibmp.vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class StorageRecord implements Serializable {
	private Long srid;
	private Long said;
	private Long gid;
	private String name;
	private Integer num;
	private Double price;
	private Double weight;
	private Integer status;
	private String inmid;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getInmid() {
		return inmid;
	}
	public void setInmid(String inmid) {
		this.inmid = inmid;
	}
	@Override
	public String toString() {
		return "Storage_record [srid=" + srid + ", said=" + said + ", gid=" + gid + ", name=" + name + ", num=" + num
				+ ", price=" + price + ", weight=" + weight + ", status=" + status + ", inmid=" + inmid + "]";
	}
}
