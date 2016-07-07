package com.one.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.one.exception.MyException;

/**
 * @author jay
 * @since 2016年7月1日
 */
@Component
public class ExceptionUtil {

	@Autowired
	Environment environment;

	public void getException(String str) {
		Integer code = Integer.parseInt(environment.getProperty(str + ".code"));
		String msg = environment.getProperty(str + ".msg");
		throw new MyException(code, msg);
	}

}
