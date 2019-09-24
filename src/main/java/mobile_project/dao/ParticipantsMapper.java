package mobile_project.dao;

import java.util.List;

import mobile_project.bean.Participants;

public interface ParticipantsMapper {
    int insert(Participants record);

    int insertSelective(Participants record);

	List<Participants> selectByEventId(int eventId);

	List<Participants> selectByUserId(int userId);

	int delete(Participants p);

	List<Participants> select3LargeCountEvents();
}