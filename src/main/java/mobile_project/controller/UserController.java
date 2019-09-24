package mobile_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	@RequestMapping(value = LOG_IN, method = RequestMethod.POST)
	@ResponseBody
	public Msg logInCheck(@RequestBody Msg msg) {
		User user = msg.getUserInfo();
		String username = user.getUsername();
		String password = user.getPassword();
		if(username ==null ||password==null) {
			Msg back = new Msg();
			back.setCode(Msg.SELECT_FAIL);
			return back;
		}
		System.out.println(username+" "+password);
		Msg result = userService.checkUser(username, password);
		result.setOperation(LOG_IN);
		return result;
	}

	/**
	 * 插入用户数据
	 * 
	 * @return
	 */
	@RequestMapping(value = SIGN_UP, method = RequestMethod.POST)
	@ResponseBody
	public Msg signUpCheck(@RequestBody Msg msg) {
		int result = userService.insertUser( msg.getUserInfo());
		Msg back = new Msg();
		back.setCode(result);
		back.setOperation("signup");
	
		return back;

	}
}
