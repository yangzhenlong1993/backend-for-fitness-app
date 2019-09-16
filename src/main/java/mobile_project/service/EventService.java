package mobile_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mobile_project.bean.Event;
import mobile_project.dao.EventMapper;

@Service
public class EventService {
	@Autowired
	private EventMapper eventMapper;

	public Event getEvent(int eventId) {
		
		return eventMapper.selectByPrimaryKey(eventId);
	}

	public int insertEvent(Event event) {

		eventMapper.insert(event);
		return 0;
	}
	
	public int updateEvent(Event event) {
		int result = eventMapper.updateByPrimaryKey(event);
		return 0;
	}
}
