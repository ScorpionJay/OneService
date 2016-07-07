/**
 * Copyright (c) 2016 Mars
 */

package com.one.service.impl;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.one.app.App;
import com.one.service.ArticleService;
import com.one.vo.ArticleVo;

/**
 * @author Jay
 * @since 2016年7月7日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
public class ArticleServiceImplTest {

	private Logger log = LoggerFactory.getLogger(ArticleServiceImplTest.class);

	@Autowired
	ArticleService articleService;

	@Test
	public void testAdd() {
		 Calendar calendar=Calendar.getInstance();   
		 calendar.setTime(new Date()); 
		 
		for (int i = 0; i < 10; i++) {
			calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH)-i);//让日期加1
			articleService.add(new ArticleVo( "测试标题" + i, "测试内容..." + i, "jay", calendar.getTime()));
		}
	}

	@Test
	public void testGetById() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetByType() {
		 List<ArticleVo> lsit = articleService.getByType("", "2016-7-4 23:00:00" , 5);
		 for (ArticleVo articleVo : lsit) {
			System.out.println(articleVo.toString());
		}
	}

	@Test
	public void testDeleteById() {
		fail("Not yet implemented");
	}

}
