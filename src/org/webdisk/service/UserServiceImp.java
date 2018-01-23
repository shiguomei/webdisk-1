package org.webdisk.service;

import java.io.File;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.webdisk.mapper.UserMapper;
import org.webdisk.pojo.User;

@Service(value = "userServiceImp")
public class UserServiceImp implements UserService {
	private UserMapper userMapper;
public void register(User user)  {
	userMapper.register(user);
	userMapper.create(user);
	File file = new File("F:/WebDisk/upload/"+user.getUserName());
	file.mkdirs();
}
	public UserMapper getUserMapper() {
		return userMapper;
	}
@Resource(name="userMapper")
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
@Override
public User login(User user) {
	return userMapper.login(user);
	
}

	


}
