package com.one.controller;

import org.apache.tomcat.util.buf.UEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.one.service.UserService;
import com.one.vo.ResultVo;
import com.one.vo.UserVo;

@RestController
public class TestController {

	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value="test123")
    public String test() {
        return "test";
    }

	@RequestMapping(value="hello")
    public String hello() {
        return "hello";
    }

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "testAdd", method = RequestMethod.GET)
	@ResponseBody
	public ResultVo register( String username,  String password) {
		ResultVo resultVo = new ResultVo();
		userService.addUser(new UserVo(username, password,"admin"));
		return resultVo;
	}
	
	@RequestMapping(value = "user/{username}", method = RequestMethod.GET)
	@ResponseBody
	public ResultVo getByUsername(@PathVariable String username) {
		ResultVo resultVo = new ResultVo();
		UserVo userVo = userService.getByUsername2(username);
		resultVo.setData(userVo);
//		Integer.parseInt("aaa");
		System.out.println(userVo.toString());
		return resultVo;
	}
	
}
