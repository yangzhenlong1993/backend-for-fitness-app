package mobile_project.test;

import java.util.Date;
import java.util.Enumeration;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.fasterxml.jackson.databind.node.ObjectNode;

import mobile_project.bean.User;

/**
 * ʹ��spring����ģ���ṩ�Ĳ��������ܣ�����CRUD�������ȷ��
 * 
 * @author zhenlong
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:applicationContext.xml",
		"file:src/main/webapp/WEB-INF/dispatcherServlet-servlet.xml" })
public class MVCTest {
	// ����springMVC��ioc
	@Autowired
	WebApplicationContext context;
	// ����mvc���󣬻�ȡ��������
	MockMvc mockMvc;

	@Before
	public void initMockMvc() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void testGetUser() throws Exception {
		
		User user = new User();
		user.setUsername("dfsg");
		user.setPassword("123");
		user.setGender(1);
		user.setBirthday(new Date());
		user.setHeight(123);
		user.setWeight(123);
		ObjectMapper mapper = new ObjectMapper();
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String requestJson = ow.writeValueAsString(user);
		mapper.writeValueAsString(user);
		
		// ģ�������õ�����ֵ
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/login").param("username", user.getUsername())).andReturn();
		// ����ɹ���
		//MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/signup").contentType(MediaType.APPLICATION_JSON).content(requestJson)).andReturn();
		System.out.println(result.getResponse().getContentAsString());
		

	}
}
