package mobile_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mobile_project.bean.Msg;
import mobile_project.bean.User;
import mobile_project.dao.UserMapper;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;

	public User getUser(String username) {
		//select user info
		return userMapper.selectByUsername(username);
	}

	// update user info
	public int updateUser(User user) {
		int result = userMapper.updateByPrimaryKey(user);

		return Msg.UPDATE_SUCCESS;

	}

	// insert user info, check if the user exists
	public int insertUser(User user) {

		if (this.getUser(user.getUsername()) != null) {
			return Msg.INSERT_FAIL;
		} else {
			userMapper.insert(user);
			return Msg.INSERT_SUCCESS;
		}

	}
}
