package org.webdisk.control;

import java.util.Enumeration;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.webdisk.pojo.User;
import org.webdisk.service.UserService;

@Controller("userControl")
@RequestMapping("/user")
public class UserControl {
	private UserService userService;

	@RequestMapping(value = "register.do", method = RequestMethod.POST)
	public String insert(@RequestBody User user) {

		userService.register(user);
		return "redirect:/jsp/login.jsp";
	}
	
	@RequestMapping(value = "quit.do")
	public String quit(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session!=null) {
			Enumeration<String> attributeNames = session.getAttributeNames();
			while(attributeNames.hasMoreElements()) {	
				session.removeAttribute(attributeNames.nextElement());
			}
		}
		
		return "redirect:/jsp/login.jsp";
	}
	@RequestMapping(value = "login.do", method = RequestMethod.POST)
	public String login(@RequestBody User user,HttpServletRequest request,HttpServletResponse response) {
		User login = userService.login(user);
		HttpSession session = request.getSession();
		if(session!=null) {
			Enumeration<String> attributeNames = session.getAttributeNames();
			while(attributeNames.hasMoreElements()) {	
				session.removeAttribute(attributeNames.nextElement());
			}
		}
		
		session.setAttribute("user",login.getUserName());
		
		return "redirect:/jsp/main/index.jsp";
	}

	public UserService getUserService() {
		return userService;
	}

	@Resource(name = "userServiceImp")
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
