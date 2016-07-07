package com.one.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.one.main.restsec.AuthenticationService;
import com.one.service.ArticleService;
import com.one.service.UserService;
import com.one.vo.ArticleVo;
import com.one.vo.ResultVo;
import com.one.vo.UserVo;

/**
 * @author jay
 * @since 2016年6月30日
 */
@Controller
@RequestMapping("v1/article")
public class ArticleController {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ArticleService articleService;
	
	
	/**
	 * get user info by username
	 * 
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public ResultVo getByUsername(String type,String date,Integer pageSize) {
		ResultVo resultVo = new ResultVo();
		List<ArticleVo> lsit = articleService.getByType(type, date , pageSize);
		resultVo.setData(lsit);
		log.info(resultVo.toString());
		return resultVo;
	}
	
	
}
