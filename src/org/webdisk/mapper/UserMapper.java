package org.webdisk.mapper;

import org.springframework.stereotype.Repository;
import org.webdisk.pojo.User;
@Repository(value="userMapper")
public interface UserMapper {
	void register(User user);
	
	void create(User user);
	
	User login(User user); 
}
