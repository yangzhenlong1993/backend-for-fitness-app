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
 * ����socket �Ĵ�������������֮������
 * 
 * @author zhenlong
 *
 */
@Component
public class UserWebSocketHandler implements WebSocketHandler {
	// ��MyWebSocketHandler�౻����ʱ�ͻᴴ����Map���������,���������û���session
	public static final Map<Integer, WebSocketSession> USERS;
	@Autowired
	private EventService eventService;
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	//json����
	
	static {
		USERS = new HashMap<Integer, WebSocketSession>();
	}

	// ����ʵ�����Ӻ���ô˷���
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("afterConnectionEstablish is called");
		int uid = (int) session.getAttributes().get(WebSocketConfig.UID);
		if (USERS.get(uid) == null) {
			USERS.put(uid, session);
			System.out.println("number of online users:" + USERS.size());
		}

	}

	// ������Ϣǰ�Ĵ���
	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		if (message.getPayloadLength() == 0)
			return;

		// �õ�socketͨ���е����ݲ�ת��Ϊ����
		
		String incomeMsg = message.getPayload().toString();
		Msg msg = OBJECT_MAPPER.readValue(incomeMsg, Msg.class);

		switch (msg.getOperation()) {
		case "create_event":
			Event e = msg.getEvent();
			eventService.insertEvent(e);
			System.out.println("��ǰΪ���������");
			break;
		case "send_msg_to_user":
			sendMessageToUser(0, null);
			break;
		case "search_event":
			System.out.println("��ǰΪ���������");
			List<Event> events = eventService.getEventByTitle(msg.getEvent().getTitle());
			Msg backmsg = new Msg();
			backmsg.setEvents(events);
			sendMessageToUser(msg.getFromId(), backmsg);
			//���͸��Լ�
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
			System.out.println("�쳣�˳�");
			session.close();
		}
		int uid = (int) session.getAttributes().get(WebSocketConfig.UID);
		USERS.remove(uid);
	}

	// �û�����ʱ��Ҫ��ʱ���MAP�ж�Ӧ��session���Է���һ����������ʱ�޷����뵽MAP��
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {

		USERS.remove(session.getAttributes().get(WebSocketConfig.UID));
		System.out
				.println("�û����" + session.getAttributes().get(WebSocketConfig.UID) + "�ѵǳ�," + "��ǰ��������:" + USERS.size());

	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}

}
