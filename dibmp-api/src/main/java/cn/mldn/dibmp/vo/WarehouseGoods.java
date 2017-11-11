package cn.mldn.dibmp.vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class WarehouseGoods implements Serializable {
	private Long wgid ;
	private Long wid ;
	private Long gid ;
	private Integer num ;
	public Long getWgid() {
		return wgid;
	}
	public void setWgid(Long wgid) {
		this.wgid = wgid;
	}
	public Long getWid() {
		return wid;
	}
	public void setWid(Long wid) {
		this.wid = wid;
	}
	public Long getGid() {
		return gid;
	}
	public void setGid(Long gid) {
		this.gid = gid;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
}
