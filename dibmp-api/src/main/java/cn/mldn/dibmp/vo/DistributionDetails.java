package cn.mldn.dibmp.vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class DistributionDetails implements Serializable {
	private Long dsdid;
	private Long gid;
	private String name;
	private Integer num;
	private Double price;
	private Integer status;
	private Long wid;
	private String outmid;
	public Long getDsdid() {
		return dsdid;
	}
	public void setDsdid(Long dsdid) {
		this.dsdid = dsdid;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Long getWid() {
		return wid;
	}
	public void setWid(Long wid) {
		this.wid = wid;
	}
	public String getOutmid() {
		return outmid;
	}
	public void setOutmid(String outmid) {
		this.outmid = outmid;
	}
	@Override
	public String toString() {
		return "Distribution_details [dsdid=" + dsdid + ", gid=" + gid + ", name=" + name + ", num=" + num + ", price="
				+ price + ", status=" + status + ", wid=" + wid + ", outmid=" + outmid + "]";
	}
}
