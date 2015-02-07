package com.dj.dy.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dj.dy.entity.JsonEntity;
import com.dj.dy.entity.LoginLog;
import com.dj.dy.entity.User;
import com.dj.dy.exception.UserExistException;
import com.dj.dy.service.LoginService;
import com.dj.dy.service.RoleService;
import com.dj.dy.service.UserService;

@Controller
@RequestMapping("/account")
public class AccountController extends BaseController {
	private static final Logger logger = Logger.getLogger(AccountController.class);
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserService userService;
	@Autowired
	private LoginService loginService;
	@RequestMapping(value = "/prelogin", method = RequestMethod.GET)
	public String preLogin(HttpServletRequest request) {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody JsonEntity login(HttpServletRequest request, HttpServletResponse response, User user) throws IOException {
		logger.debug("User : " + user + " login start...");

		LoginLog loginLog = initLoginLog(request);
		User dbUser = loginService.userLogin(user, loginLog);
		if (dbUser == null) {
			return new JsonEntity(-1, "用户名不存在或密码不正确");

		} else {
			logger.info("User : " + user.getUserName() + " login end..." + dbUser != null ? "SUCCESS" : "FAILURE");
			setSessionUser(request, dbUser);
			return new JsonEntity(1, "登录成功");
		}
	}
	
	@RequestMapping(value = "/preregister", method = RequestMethod.GET)
	public ModelAndView preRegister(HttpServletRequest request, User user) {
		
		ModelAndView view = new ModelAndView();
		view.setViewName("register");
		return view;
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public @ResponseBody JsonEntity register(HttpServletRequest request, HttpServletResponse response, User user) {
		logger.debug("User Register : register start...");
		try {
			loginService.save(user);
			setSessionUser(request, user);
			logger.debug("User Register : register end...");
			return new JsonEntity(1, "注册成功");

		} catch (UserExistException e) {
			logger.debug("User Register : register end...");
			return new JsonEntity(-1, "用户名已经存在，请选择其它的名字。");
		}
		
	}
	
	@RequestMapping(value = "/index")
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		User loginUser = getSessionUser(request);
		mv.addObject("user", loginUser);
		mv.setViewName("index");
		return mv;
	}
	
	@RequestMapping(value = "/logout")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView();
		removeSessionUser(request);
		mv.setViewName("forward:/login.jsp");
		return mv;
	}
}
