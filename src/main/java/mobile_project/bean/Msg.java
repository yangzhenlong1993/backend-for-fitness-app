package mobile_project.bean;

import java.util.List;

/**
 * 通用的返回数据类
 * 
 * @author zhenlong
 *
 */
public class Msg {

	private int code;
	// 提示信息
	private String operation;
	// 需要的操作
	private int fromId;
	// 发信用户
	private int toId;
	// 收信用户
	private User userInfo = null;
	
	private Event event = null;
	
	private List<Event> events = null;

	public static final int INSERT_SUCCESS = 101;
	public static final int UPDATE_SUCCESS = 102;
	public static final int DELETE_SUCCESS = 103;
	public static final int SELECT_SUCCESS = 104;
	public static final int INSERT_FAIL = 201;
	public static final int UPDATE_FAIL = 202;
	public static final int DELETE_FAIL = 203;
	public static final int SELECT_FAIL = 204;


	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public int getFromId() {
		return fromId;
	}

	public void setFromId(int fromId) {
		this.fromId = fromId;
	}

	public int getToId() {
		return toId;
	}

	public void setToId(int toId) {
		this.toId = toId;
	}

	public User getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(User userInfo) {
		this.userInfo = userInfo;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}



}
