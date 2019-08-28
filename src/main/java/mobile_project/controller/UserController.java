package mobile_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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
	private UserService userService;
	private static final String LOG_IN = "login";
	private static final String SIGN_UP = "signup";

	/**
	 * 查询用户数据
	 * 
	 * @return
	 */
	@RequestMapping(value = LOG_IN, method = RequestMethod.GET)
	@ResponseBody
	public Msg logInCheck(@RequestParam(value = "username") String username) {
		User result = userService.getUser(username);
		return Msg.prepare(100, LOG_IN).add("user_info",result);

	}

	@RequestMapping(value = SIGN_UP, method = RequestMethod.GET)
	@ResponseBody
	public Msg signUpCheck(@RequestBody User user) {
		int result = userService.insertUser(user);

		return Msg.prepare(result, SIGN_UP);

	}
}
