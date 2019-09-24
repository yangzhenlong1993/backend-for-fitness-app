package mobile_project.dao;

import java.util.List;

import mobile_project.bean.Event;

public interface EventMapper {
	int deleteByPrimaryKey(Integer eventid);

	int insert(Event record);

	int insertSelective(Event record);

	Event selectByPrimaryKey(Integer eventid);

	List<Event> selectByTitle(String title);
	
	List<Event> selectAllEvents();

	int updateByPrimaryKeySelective(Event record);

	int updateByPrimaryKey(Event record);

}