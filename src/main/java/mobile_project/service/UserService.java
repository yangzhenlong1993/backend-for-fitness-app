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
		// select user info
		return userMapper.selectByUsername(username);
	}

	// update user info
	public int updateUser(User user) {
		int result = userMapper.updateByPrimaryKey(user);
		System.out.println(result);
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

	// 校验用户登陆信息
	public Msg checkUser(String username, String password) {
		Msg msg = new Msg();
		msg.setOperation("login");
		User user = this.getUser(username);
		if (user != null) {
			if (user.getPassword().equals(password)) {
				msg.setCode(Msg.SELECT_SUCCESS);

				msg.setUserInfo(getUser(username));
				return msg;
			} else {
				msg.setCode(Msg.SELECT_FAIL);

				return msg;
			}
		} else {
			msg.setCode(Msg.SELECT_FAIL);

			return msg;
		}

	}
}
