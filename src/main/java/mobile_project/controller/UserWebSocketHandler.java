package mobile_project.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import mobile_project.bean.Msg;

/**
 * 这是socket 的处理器，拦截器之后启用
 * 
 * @author zhenlong
 *
 */
@Component
public class UserWebSocketHandler implements WebSocketHandler {
	// 当MyWebSocketHandler类被加载时就会创建该Map，随类而生,保管所有用户的session
	public static final Map<String, WebSocketSession> USERS;

	private static final String ATTRIBUTE_NAME = "WEBSOCKET_USER_ID";

	static {
		USERS = new HashMap<String, WebSocketSession>();
	}

	// 握手实现连接后调用此方法
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("afterConnectionEstablish is called");
		String uid = (String) session.getAttributes().get(ATTRIBUTE_NAME);
		if (USERS.get(uid) == null) {
			USERS.put(uid, session);
			System.out.println("number of online users:" + USERS.size());
		}

	}

	// 发送信息前的处理
	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		if (message.getPayloadLength() == 0)
			return;

		// 得到socket通道中的数据并转化为对象
		message.getPayload().toString();

		sendMessageToUser(0, null);

		int service = 0;
		switch (service) {
		case 1:
			break;
		case 2:
			break;
		}
	}

	private void sendMessageToUser(int uid, Msg msg) throws IOException {

	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		if (session.isOpen()) {
			System.out.println("异常退出");
			session.close();
		}
		String uid = (String) session.getAttributes().get(ATTRIBUTE_NAME);
		USERS.remove(uid);
	}

	// 用户断线时需要及时清除MAP中对应的session，以防下一次重新连接时无法加入到MAP中
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {

		USERS.remove(session.getAttributes().get(ATTRIBUTE_NAME));
		System.out.println("用户编号"+session.getAttributes().get(ATTRIBUTE_NAME) + "已登出," + "当前在线人数:" + USERS.size());

	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}

}
