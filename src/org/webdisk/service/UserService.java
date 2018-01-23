package org.webdisk.service;


import org.webdisk.pojo.User;

public interface UserService {
	 void register(User user);
	 User login(User user);
}