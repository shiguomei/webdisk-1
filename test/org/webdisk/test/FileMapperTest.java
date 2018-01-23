package org.webdisk.test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.webdisk.mapper.FileMapper;
import org.webdisk.mapper.UserMapper;
import org.webdisk.pojo.User;
import org.webdisk.service.UserServiceImp;


class FileMapperTest {

	@Test
	void test() {
		ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(
				"classpath:spring/applicationContext.xml");
		
		DataSource bean = classPathXmlApplicationContext.getBean("comboPooledDataSource", DataSource.class);

		System.out.println(bean);
	}
	@Test
	void testmap() {
		ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(
				"classpath:spring/applicationContext.xml");
		
		FileMapper bean = classPathXmlApplicationContext.getBean("fileMapper", FileMapper.class);
	
	}
	@Test
	void testUSER() {
		ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(
				"classpath:spring/applicationContext.xml");
		
		UserMapper bean = classPathXmlApplicationContext.getBean("userMapper", UserMapper.class);
		User user = new User();
	user.setUserName("abc5432456t");
	user.setUserPassword("arfa");
	bean.register(user);
	bean.create(user);
	
	}
	@Test
	void testUSEsR()  {
		ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(
				"classpath:spring/applicationContext.xml");
		
		UserServiceImp bean = classPathXmlApplicationContext.getBean("userServiceImp", UserServiceImp.class);
		
		
		User user = new User();
		user.setUserName("abewr6t");
		user.setUserPassword("arfa");
		bean.register(user);
	
	}
	@Test
	void yyu() throws UnsupportedEncodingException {
		String decode = URLDecoder.decode("C5E58DEC5FA88550DBF4D6556D304AFA", "GBK");
		System.out.println(decode);
	}
}
