package com.one.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.one.service.UserService;
import com.one.vo.ResultVo;
import com.one.vo.UserVo;

/**
 * This controller is not part of RESTful API, but rather goes to normal JSP
 * views.
 */
@Controller
public class MainController {

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private UserService userService;

	@PostConstruct
	public void init() {
		System.out.println(" *** MainController.init with: " + applicationContext);
	}

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "v1/register", method = RequestMethod.POST)
	@ResponseBody
	public ResultVo register( String username,  String password) {
		ResultVo resultVo = new ResultVo();
		userService.addUser(new UserVo(username, password,"admin"));
		return resultVo;
	}
}
