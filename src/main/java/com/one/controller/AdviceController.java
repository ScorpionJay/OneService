package com.one.controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.one.exception.MyException;
import com.one.vo.ResultVo;

@ControllerAdvice
public class AdviceController {
	
	private static Logger logger=LoggerFactory.getLogger(AdviceController.class);
	
//	@Resource(name="messageSource")
//	private MessageSource messages;

	@Autowired
	Environment environment;
	
	// 异常处理
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public ResultVo handleException(Exception exception,HttpServletResponse response) {
		logger.error("****异常 >>{}***", exception.getMessage());
		ResultVo resultVo = new ResultVo();
		// exception 判断
		if(exception instanceof MyException){
			MyException myException = (MyException)exception;
			//response.setStatus(((MyException)exception).getCode());
			resultVo.setCode( myException.getCode() );
			resultVo.setMsg( myException.getMessage() );
		}else{
			logger.info("服务器错误");
			Integer code = Integer.parseInt(environment.getProperty("service.error.code"));
			String msg = environment.getProperty("service.error.msg");
			resultVo.setCode( code );
			resultVo.setMsg( msg );
		}
		
		return resultVo;
	}

	
}
