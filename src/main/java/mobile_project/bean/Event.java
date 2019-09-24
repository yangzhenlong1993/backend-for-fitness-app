package mobile_project.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Event {
	private Integer eventid;

	private String title;

	private String category;

	private String locationname;

	private Double longtitude;

	private Double latitude;
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "GMT+10")
	private Date starttime;

	private Integer eventinterval;

	private Integer eventcount;

	//1 代表完成，0代表未完成，任何新建的event状态都应为0
	private Integer doneornot = 0;

	public Integer getEventid() {
		return eventid;
	}

	public void setEventid(Integer eventid) {
		this.eventid = eventid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category == null ? null : category.trim();
	}

	public String getLocationname() {
		return locationname;
	}

	public void setLocationname(String locationname) {
		this.locationname = locationname == null ? null : locationname.trim();
	}

	public Double getLongtitude() {
		return longtitude;
	}

	public void setLongtitude(Double longtitude) {
		this.longtitude = longtitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public Integer getEventinterval() {
		return eventinterval;
	}

	public void setEventinterval(Integer eventinterval) {
		this.eventinterval = eventinterval;
	}

	public Integer getEventcount() {
		return eventcount;
	}

	public void setEventcount(Integer eventcount) {
		this.eventcount = eventcount;
	}

	public Integer getDoneornot() {
		return doneornot;
	}

	public void setDoneornot(Integer doneornot) {
		this.doneornot = doneornot;
	}
}