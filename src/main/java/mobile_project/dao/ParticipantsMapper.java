package mobile_project.dao;

import mobile_project.bean.Participants;

public interface ParticipantsMapper {
    int insert(Participants record);

    int insertSelective(Participants record);
}