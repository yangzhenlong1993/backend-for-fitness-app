package mobile_project.dao;

import mobile_project.bean.User;

public interface UserMapper {
	int deleteByPrimaryKey(Integer userid);

	int insert(User record);

	int insertSelective(User record);

	User selectByPrimaryKey(Integer userid);

	User selectByUsername(String username);

	int updateByPrimaryKeySelective(User record);

	int updateByPrimaryKey(User record);

	
}