package mobile_project.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import mobile_project.bean.Event;
import mobile_project.bean.Msg;
import mobile_project.service.EventService;

/**
 * 这是socket 的处理器，拦截器之后启用
 * 
 * @author zhenlong
 *
 */
@Component
public class UserWebSocketHandler implements WebSocketHandler {
	// 当MyWebSocketHandler类被加载时就会创建该Map，随类而生,保管所有用户的session
	public static final Map<Integer, WebSocketSession> USERS;
	@Autowired
	private EventService eventService;
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	//json解析
	
	static {
		USERS = new HashMap<Integer, WebSocketSession>();
	}

	// 握手实现连接后调用此方法
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("afterConnectionEstablish is called");
		int uid = (int) session.getAttributes().get(WebSocketConfig.UID);
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
		
		String incomeMsg = message.getPayload().toString();
		Msg msg = OBJECT_MAPPER.readValue(incomeMsg, Msg.class);

		switch (msg.getOperation()) {
		case "create_event":
			Event e = msg.getEvent();
			eventService.insertEvent(e);
			System.out.println("当前为创建活动操作");
			break;
		case "send_msg_to_user":
			sendMessageToUser(0, null);
			break;
		case "search_event":
			System.out.println("当前为搜索活动操作");
			List<Event> events = eventService.getEventByTitle(msg.getEvent().getTitle());
			Msg backmsg = new Msg();
			backmsg.setEvents(events);
			sendMessageToUser(msg.getFromId(), backmsg);
			//发送给自己
			break;
		}
	}

	public void sendMessageToUser(int uid, Msg msg) throws IOException {
		WebSocketSession session = USERS.get(uid);
		String backMsg = OBJECT_MAPPER.writeValueAsString(msg);
		System.out.println(backMsg);
		if(session !=null && session.isOpen()) {
			
			session.sendMessage(new TextMessage(backMsg));
		}
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		if (session.isOpen()) {
			System.out.println("异常退出");
			session.close();
		}
		int uid = (int) session.getAttributes().get(WebSocketConfig.UID);
		USERS.remove(uid);
	}

	// 用户断线时需要及时清除MAP中对应的session，以防下一次重新连接时无法加入到MAP中
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {

		USERS.remove(session.getAttributes().get(WebSocketConfig.UID));
		System.out
				.println("用户编号" + session.getAttributes().get(WebSocketConfig.UID) + "已登出," + "当前在线人数:" + USERS.size());

	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}

}
