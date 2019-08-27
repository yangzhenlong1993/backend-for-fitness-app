package mobile_project.test;

import java.util.Enumeration;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import mobile_project.bean.User;

/**
 * 使用spring测试模块提供的测试请求功能，测试CRUD请求的正确性
 * 
 * @author zhenlong
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:applicationContext.xml",
		"file:src/main/webapp/WEB-INF/dispatcherServlet-servlet.xml" })
public class MVCTest {
	// 传入springMVC的ioc
	@Autowired
	WebApplicationContext context;
	// 虚拟mvc请求，获取到处理结果
	MockMvc mockMvc;

	@Before
	public void initMockMvc() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void testGetUser() throws Exception {
		// 模拟请求拿到返回值
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/user").param("userId", "2").param("password", "123456")).andReturn();
		// 请求成功后
		System.out.println(result.getResponse().getContentAsString());

	}
}
