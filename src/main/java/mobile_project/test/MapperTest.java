package mobile_project.test;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import mobile_project.bean.User;
import mobile_project.dao.UserMapper;

/**
 * ����dao�㹤�� ʹ��spring�ĵ�Ԫ���ԣ��Զ�ע�����
 * 
 * @author zhenlong
 * @ContextConfigurationָ��spring�����ļ���λ�� ֱ��autowiredҪʹ�õ����
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class MapperTest {
	@Autowired
	UserMapper userMapper;

	@Autowired
	SqlSession sqlSession;

	/**
	 * ����UserMapper
	 */
	@Test
	public void testCRUD() {
		System.out.println(userMapper);

		// ����һ���û�
//		userMapper.insertSelective(new User(null, "zhenlong", "123456"));
//		userMapper.insertSelective(new User(null, "yuhong", "12asd456"));

		// ����ִ��
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		for (int i = 0; i < 100; i++) {
			//mapper.insertSelective(new User(null, "my name is " + i, "password" + i));
		}
	}

}
