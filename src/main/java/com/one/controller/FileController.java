package com.one.controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.one.main.restsec.AuthenticationService;
import com.one.main.restsec.TokenManager;
import com.one.service.FileService;
import com.one.service.UserService;
import com.one.vo.ResultVo;


/**
 * File controller
 * 
 * @author Jay
 * @time 2015年6月3日
 */
@RestController
@RequestMapping("v1/file")
public class FileController {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	FileService fileService;
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = "save", method = RequestMethod.POST)
	@ResponseBody
	public ResultVo saveFile(@RequestParam(value = "file") MultipartFile file) {
		
		log.info("上传头像");
		ResultVo resultVo = new ResultVo();
		try {
			// 文件类型 大小的判断
			String id = fileService.save(file.getBytes(), file.getOriginalFilename(),file.getContentType());

			UserDetails currentUser = authenticationService.currentUser();
			
			userService.uploadImage(currentUser.getUsername(),id);
			
			resultVo.setData(id);
			resultVo.setMsg("上传成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultVo;
	}
	
	
	@RequestMapping(value = "get", method = RequestMethod.GET)
	public void getFile(String fileName, HttpServletResponse response){
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			byte[] result = fileService.getByName(fileName);
			if(null != result){
				os.write(result); 
			}
		} catch (IOException e) {
			
		}
		
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public void getFileById( @PathVariable String id, HttpServletResponse response){
		fileService.getById(id,response);
	}
}
