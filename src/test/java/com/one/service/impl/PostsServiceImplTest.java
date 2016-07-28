package com.one.service.impl;

import static org.junit.Assert.fail;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.one.app.App;
import com.one.service.PostService;
import com.one.vo.PostVo;

/**
* @author	jay
* @since	2016年7月13日
*/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
public class PostsServiceImplTest {

	private Logger log = LoggerFactory.getLogger(PostsServiceImplTest.class);

	@Autowired
	PostService postService;
	
	@Test
	public void testAdd() {
//		PostVo vo = new PostVo("577c5e968cda6df87a6cb0ab", "test post 222...", "1", null,"shanghai" , new Date());
//		postService.add(vo);
//		log.info("ADD POST SUCCESS");
	}

	@Test
	public void testGetById() {
		PostVo vo = postService.getById("5785f4ee8cda6df8505ec4db");
		log.info(vo.toString());
	}

	@Test
	public void testGet() {
		List<PostVo> list = postService.get("5785f4ee8cda6df8505ec4db", "2016-7-13 16:42:10");
		ObjectMapper mapper = new ObjectMapper();  
		 try {
			String json = mapper.writeValueAsString(list);
			log.info(json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}  
	}

	@Test
	public void testDeleteById() {
		postService.deleteById("5785fefe8cda58f6bc8dff49");
	}

}
