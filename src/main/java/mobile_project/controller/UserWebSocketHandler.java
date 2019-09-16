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
 * ����socket �Ĵ�������������֮������
 * 
 * @author zhenlong
 *
 */
@Component
public class UserWebSocketHandler implements WebSocketHandler {
	// ��MyWebSocketHandler�౻����ʱ�ͻᴴ����Map���������,���������û���session
	public static final Map<String, WebSocketSession> USERS;

	private static final String ATTRIBUTE_NAME = "WEBSOCKET_USER_ID";

	static {
		USERS = new HashMap<String, WebSocketSession>();
	}

	// ����ʵ�����Ӻ���ô˷���
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("afterConnectionEstablish is called");
		String uid = (String) session.getAttributes().get(ATTRIBUTE_NAME);
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
			System.out.println("�쳣�˳�");
			session.close();
		}
		String uid = (String) session.getAttributes().get(ATTRIBUTE_NAME);
		USERS.remove(uid);
	}

	// �û�����ʱ��Ҫ��ʱ���MAP�ж�Ӧ��session���Է���һ����������ʱ�޷����뵽MAP��
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {

		USERS.remove(session.getAttributes().get(ATTRIBUTE_NAME));
		System.out.println("�û����"+session.getAttributes().get(ATTRIBUTE_NAME) + "�ѵǳ�," + "��ǰ��������:" + USERS.size());

	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}

}
