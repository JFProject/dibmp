package cn.mldn.dibmp.vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class DistributionDetails implements Serializable {
	private Long dsdid;
	private Long dsid;
	private Long gid;
	private String name;
	private Integer num;
	private Double price;
	private Long wid;
	public Long getDsdid() {
		return dsdid;
	}
	public void setDsdid(Long dsdid) {
		this.dsdid = dsdid;
	}
	public Long getDsid() {
		return dsid;
	}
	public void setDsid(Long dsid) {
		this.dsid = dsid;
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
	public Long getWid() {
		return wid;
	}
	public void setWid(Long wid) {
		this.wid = wid;
	}
	
}
