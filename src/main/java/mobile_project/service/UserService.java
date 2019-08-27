package mobile_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mobile_project.bean.User;
import mobile_project.dao.UserMapper;

@Service
public class UserService {

	@Autowired
	UserMapper userMapper;

	public User getUser(int userId) {
		
		return userMapper.selectByPrimaryKey(userId);
	}

}
