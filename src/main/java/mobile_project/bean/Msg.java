package mobile_project.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * ͨ�õķ���������
 * 
 * @author zhenlong
 *
 */
public class Msg {
	// ״̬�� �ɹ�-100 ʧ��-200
	private int code;
	// ��ʾ��Ϣ
	private String msg;
	// Ҫ���ظ������������
	private Map<String, Object> extend = new HashMap<String, Object>();

	public static final int INSERT_SUCCESS = 101;
	public static final int UPDATE_SUCCESS = 102;
	public static final int DELETE_SUCCESS = 103;
	public static final int SELECT_SUCCESS = 104;
	public static final int INSERT_FAIL = 201;
	public static final int UPDATE_FAIL = 202;
	public static final int DELETE_FAIL = 203;
	public static final int SELECT_FAIL = 204;

	public static Msg prepare(int code, String msg) {
		Msg result = new Msg();
		result.setCode(code);
		result.setMsg(msg);
		return result;
	}

	public Msg add(String key, Object value) {
		this.getExtend().put(key, value);
		return this;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Map<String, Object> getExtend() {
		return extend;
	}

	public void setExtend(Map<String, Object> extend) {
		this.extend = extend;
	}

}
