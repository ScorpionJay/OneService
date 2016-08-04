package com.one.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.one.main.restsec.AuthenticationService;
import com.one.service.FileService;
import com.one.service.PostService;
import com.one.service.UserService;
import com.one.util.ExceptionUtil;
import com.one.util.Post;
import com.one.vo.PostAllVo;
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
	
	@Autowired
	FileService fileService;
	
	@Autowired
	ExceptionUtil exceptionUtil;
	
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
	
	
	@RequestMapping(value = "post", method = RequestMethod.POST)
	@ResponseBody
	public ResultVo post(@RequestParam(value = "file", required = false) MultipartFile file,HttpServletRequest request) {
		ResultVo resultVo = new ResultVo();
		String content = request.getParameter("content");
		
		log.info("content{}",content);
		PostVo vo = new PostVo();
		List<String> sourceList = new ArrayList<String>(); 
		// file
		if ( file != null ){
			try {
				log.info("content type{}",file.getContentType());
				String id = fileService.save(file.getBytes(), file.getOriginalFilename(),file.getContentType());
				sourceList.add(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			vo.setType(Post.PICTURE.toString());
		}else{
			log.info("Unable to upload. File is empty.");

			vo.setType(Post.WORD.toString());
		}
		
		UserDetails currentUser = authenticationService.currentUser();
		UserVo userVo = userService.getByUsername2(currentUser.getUsername());
		
		
		vo.setUserId(userVo.getId());
		vo.setSource(sourceList);
		vo.setContent(content);
		// type
		
		postService.add(vo);
		resultVo.setMsg("发表成功");
		
		return resultVo;
	}
	
	@RequestMapping(value = "/lists", method = RequestMethod.GET)
	@ResponseBody
	public ResultVo gets(String date) {
		ResultVo resultVo = new ResultVo();
		
		List<PostAllVo> list = postService.getAll( date);
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
	
	

}
