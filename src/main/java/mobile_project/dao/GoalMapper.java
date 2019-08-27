package mobile_project.dao;

import java.util.List;
import mobile_project.bean.Goal;
import mobile_project.bean.GoalExample;
import org.apache.ibatis.annotations.Param;

public interface GoalMapper {
    long countByExample(GoalExample example);

    int deleteByExample(GoalExample example);

    int insert(Goal record);

    int insertSelective(Goal record);

    List<Goal> selectByExample(GoalExample example);

    int updateByExampleSelective(@Param("record") Goal record, @Param("example") GoalExample example);

    int updateByExample(@Param("record") Goal record, @Param("example") GoalExample example);
}