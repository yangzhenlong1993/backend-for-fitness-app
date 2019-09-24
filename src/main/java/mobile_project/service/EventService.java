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
import mobile_project.utils.Calculator;

@Service
public class EventService {
	@Autowired
	private EventMapper eventMapper;
	@Autowired
	private ParticipantsMapper participantsMapper;
	@Autowired
	private UserMapper userMapper;

	public Event getEvent(int eventId) {

		return eventMapper.selectByPrimaryKey(eventId);
	}

	// �½��
	public int insertEvent(Event event) {
		return eventMapper.insert(event);
	}

	// ���»����, ����������������ǰ���»����, 1������ɣ�0����δ���
	public int updateEvent(Event event) {
		if (event.getDoneornot() == 1) {

			List<Participants> participantsInOneEvent = participantsMapper.selectByEventId(event.getEventid());
			for (int i = 0; i < participantsInOneEvent.size(); i++) {
				int totalCount = 0;
				int totalAttendence = 0;
				int userId = participantsInOneEvent.get(i).getUserid();
				List<Participants> eventsOneUserTook = participantsMapper.selectByUserId(userId);
				for (int j = 0; j < eventsOneUserTook.size(); j++) {
					totalAttendence = totalAttendence + eventsOneUserTook.get(i).getCount();
					totalCount = totalCount
							+ eventMapper.selectByPrimaryKey(eventsOneUserTook.get(i).getEventid()).getEventcount();
				}

				int newLevel = Calculator.calUserLevel(totalCount, totalAttendence);

				User userWithNewLevel = new User();
				userWithNewLevel.setUserid(userId);
				userWithNewLevel.setLevel(newLevel);

				userMapper.updateByPrimaryKeySelective(userWithNewLevel);

				Event doneEvent = new Event();
				doneEvent.setEventid(event.getEventid());
				doneEvent.setDoneornot(1);
				eventMapper.updateByPrimaryKeySelective(doneEvent);
			}
		}

		return eventMapper.updateByPrimaryKey(event);
	}

	// ���ݻ�����������������ƵĻ��ģ������
	public List<Event> getEventByTitle(String title) {

		return eventMapper.selectByTitle(title);
	}

	//ȡ�ø���3ǧ���ڵ����л
	public List<Event> getNearEvents(double userLongtitude, double userLatitude) {

		List<Event> events = eventMapper.selectAllEvents();
		List<Event> nearEvents = new ArrayList<Event>();
		for (int i = 0; i < events.size(); i++) {
			if (Calculator.getDistance(userLongtitude, userLatitude, events.get(i).getLongtitude(),
					events.get(i).getLatitude()) < 3000) {
				nearEvents.add(events.get(i));
			}

		}
		return nearEvents;

	}

	// ��ȡ���ŵ������
	public List<Event> getHotEvents() {
		List<Participants> pl = participantsMapper.select3LargeCountEvents();
		List<Event> events = new ArrayList<Event>();

		for (int i = 0; i < pl.size(); i++) {
			events.add(eventMapper.selectByPrimaryKey(pl.get(i).getEventid()));
		}

		return events;

	}

}
