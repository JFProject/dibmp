package cn.mldn.dibmp.vo;

public class Warehouse {
	private Long wid;
	private String name ;
	private Long pid;
	private Long cid;  //gggg
	private Long wiid;
	private String address;
	private Double area;
	private Integer maximum;
	private Integer currnum;
	private String photo;
	private String note;
	private String recorder;
	private String admin;
	@Override
	public String toString() {
		return "Warehouse [wid=" + wid + ", name=" + name + ", pid=" + pid + ", cid=" + cid + ", wiid=" + wiid
				+ ", address=" + address + ", area=" + area + ", maximum=" + maximum + ", currnum=" + currnum
				+ ", photo=" + photo + ", note=" + note + ", recorder=" + recorder + ", admin=" + admin + "]";
	}
	public Long getWid() {
		return wid;
	}
	public void setWid(Long wid) {
		this.wid = wid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public Long getWiid() {
		return wiid;
	}
	public void setWiid(Long wiid) {
		this.wiid = wiid;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Double getArea() {
		return area;
	}
	public void setArea(Double area) {
		this.area = area;
	}
	public Integer getMaximum() {
		return maximum;
	}
	public void setMaximum(Integer maximum) {
		this.maximum = maximum;
	}
	public Integer getCurrnum() {
		return currnum;
	}
	public void setCurrnum(Integer currnum) {
		this.currnum = currnum;
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
	public String getRecorder() {
		return recorder;
	}
	public void setRecorder(String recorder) {
		this.recorder = recorder;
	}
	public String getAdmin() {
		return admin;
	}
	public void setAdmin(String admin) {
		this.admin = admin;
	}
}
