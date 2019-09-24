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

	//某用户加入某活动
	public int joinEvent(Participants p) {
		return participantsMapper.insert(p);
	}

	//某用户退出某项活动
	public int quitEvent(Participants p) {
		
		return participantsMapper.delete(p);
	}
	
	//获取当前活动下所有的用户及其信息
	public List<User> getEventUsers(int eventId) {
		List<Participants> pl = participantsMapper.selectByEventId(eventId);
		List<User> usersInEvent = new ArrayList<User>();

		for (int i = 0; i < pl.size(); i++) {
			usersInEvent.add(userMapper.selectByPrimaryKey(pl.get(i).getUserid()));
		}

		return usersInEvent;
	}

	//获取当前用户参加的所有活动及其信息
	public List<Event> getUserEvents(int userId) {
		List<Participants> pl = participantsMapper.selectByUserId(userId);
		List<Event> userEvents = new ArrayList<Event>();

		for (int i = 0; i < pl.size(); i++) {
			userEvents.add(eventMapper.selectByPrimaryKey(pl.get(i).getEventid()));
		}

		return userEvents;

	}
}
