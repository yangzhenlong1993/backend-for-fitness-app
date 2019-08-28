package mobile_project.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import mobile_project.bean.User;
import mobile_project.dao.UserMapper;
import mobile_project.utils.MsgCode;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;

	public User getUser(String username) {

		return userMapper.selectByPrimaryKey(username);
	}

	public int updateUser(User user) {
		userMapper.updateByPrimaryKey(user);

		return MsgCode.UPDATE_SUCCESS;

	}

	public int insertUser(User user) {

		if (this.getUser(user.getUsername()) != null) {
			return MsgCode.INSERT_FAIL;
		} else {
			userMapper.insert(user);
			return MsgCode.INSERT_SUCCESS;
		}

	}
}
