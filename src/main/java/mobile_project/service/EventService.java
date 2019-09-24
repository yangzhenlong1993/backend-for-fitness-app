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

	// 新建活动
	public int insertEvent(Event event) {
		return eventMapper.insert(event);
	}

	// 更新活动内容, 活动结束，或截至日期前更新活动内容, 1代表完成，0代表未完成
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

	// 根据活动名称搜索与名称类似的活动，模糊查找
	public List<Event> getEventByTitle(String title) {

		return eventMapper.selectByTitle(title);
	}

	//取得附近3千米内的所有活动
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

	// 获取热门的三个活动
	public List<Event> getHotEvents() {
		List<Participants> pl = participantsMapper.select3LargeCountEvents();
		List<Event> events = new ArrayList<Event>();

		for (int i = 0; i < pl.size(); i++) {
			events.add(eventMapper.selectByPrimaryKey(pl.get(i).getEventid()));
		}

		return events;

	}

}
