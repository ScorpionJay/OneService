package com.one.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.one.main.restsec.AuthenticationService;
import com.one.service.PostService;
import com.one.service.UserService;
import com.one.vo.PostVo;
import com.one.vo.ResultVo;
import com.one.vo.UserVo;

/**
 * @author jay	
 * time:2016-7-13
 */
@Controller
@RequestMapping("v1/post")
public class PostController {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PostService postService;

	@Autowired
	private AuthenticationService authenticationService;
	
	@Autowired
	private UserService userService;
	
	/**
	 * TODO upload file
	 * @param vo
	 * @return
	 */
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public ResultVo add(@RequestBody PostVo vo) {
		ResultVo resultVo = new ResultVo();
		
		UserDetails currentUser = authenticationService.currentUser();
		UserVo userVo = userService.getByUsername2(currentUser.getUsername());
		vo.setUserId(userVo.getId());
		
		postService.add(vo);
		return resultVo;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResultVo getById(@PathVariable String id) {
		ResultVo resultVo = new ResultVo();
		PostVo vo = postService.getById(id);
		log.info(vo.toString());
		resultVo.setData(vo);
		return resultVo;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public ResultVo get(String date) {
		ResultVo resultVo = new ResultVo();
		
		UserDetails currentUser = authenticationService.currentUser();
		UserVo userVo = userService.getByUsername2(currentUser.getUsername());
		
		List<PostVo> list = postService.get(userVo.getId(), date);
		ObjectMapper mapper = new ObjectMapper();
		try {
			String json = mapper.writeValueAsString(list);
			log.info(json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		resultVo.setData(list);
		return resultVo;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResultVo deleteById(@PathVariable String id) {
		ResultVo resultVo = new ResultVo();
		postService.deleteById(id);
		return resultVo;
	}

}
