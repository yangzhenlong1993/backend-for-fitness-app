package mobile_project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mobile_project.bean.Event;
import mobile_project.bean.Participants;
import mobile_project.bean.User;
import mobile_project.dao.EventMapper;
import mobile_project.dao.ParticipantsMapper;
import mobile_project.dao.UserMapper;

@Service
class ParticipantsService {

	@Autowired
	private ParticipantsMapper participantsMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private EventMapper eventMapper;

	//ĳ�û�����ĳ�
	public int joinEvent(Participants p) {
		return participantsMapper.insert(p);
	}

	//ĳ�û��˳�ĳ��
	public int quitEvent(Participants p) {
		
		return participantsMapper.delete(p);
	}
	
	//��ȡ��ǰ������е��û�������Ϣ
	public List<User> getEventUsers(int eventId) {
		List<Participants> pl = participantsMapper.selectByEventId(eventId);
		List<User> usersInEvent = new ArrayList<User>();

		for (int i = 0; i < pl.size(); i++) {
			usersInEvent.add(userMapper.selectByPrimaryKey(pl.get(i).getUserid()));
		}

		return usersInEvent;
	}

	//��ȡ��ǰ�û��μӵ����л������Ϣ
	public List<Event> getUserEvents(int userId) {
		List<Participants> pl = participantsMapper.selectByUserId(userId);
		List<Event> userEvents = new ArrayList<Event>();

		for (int i = 0; i < pl.size(); i++) {
			userEvents.add(eventMapper.selectByPrimaryKey(pl.get(i).getEventid()));
		}

		return userEvents;

	}
}
