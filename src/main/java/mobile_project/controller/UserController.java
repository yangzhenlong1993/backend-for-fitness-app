package mobile_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import mobile_project.bean.Msg;
import mobile_project.bean.User;
import mobile_project.service.UserService;

/**
 * 处理用户请求
 * 
 * @author zhenlong
 *
 */
@Controller
public class UserController {

	@Autowired
	UserService userService;

	/**
	 * 查询用户数据 官方推荐方法
	 * 
	 * @return
	 */
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	@ResponseBody
	public Msg getUser(@RequestParam(value = "userId") Integer userId,
			@RequestParam(value = "password") String password) {

		User user = userService.getUser(userId);

		return Msg.success().add("userInfo", user);

	}
}
