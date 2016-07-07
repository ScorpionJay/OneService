package com.one.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.one.main.restsec.AuthenticationService;
import com.one.service.UserService;
import com.one.vo.ResultVo;
import com.one.vo.UserVo;

/**
 * @author jay
 * @since 2016年6月30日
 */
@Controller
@RequestMapping("v1/account")
public class AccountController {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationService authenticationService;

	/**
	 * get user info by username
	 * 
	 * @return
	 */
	@RequestMapping(value = "{username}", method = RequestMethod.GET)
	@ResponseBody
	public ResultVo getByUsername(@PathVariable String username) {
		ResultVo resultVo = new ResultVo();
		UserVo userVo = userService.getByUsername2(username);
		resultVo.setData(userVo);
//		Integer.parseInt("aaa");
		log.info(resultVo.toString());
		return resultVo;
	}
	
	
	@RequestMapping(value = "sign", method = RequestMethod.PUT)
	@ResponseBody
	public ResultVo sign(String sign) {
		ResultVo resultVo = new ResultVo();
		UserDetails currentUser = authenticationService.currentUser();
		userService.sign(currentUser.getUsername(),sign);
		
		resultVo.setMsg("修改成功");
		log.info(resultVo.toString());
		return resultVo;
	}
	
}
