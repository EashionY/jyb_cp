package cn.jyb.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jyb.util.JsonResult;

public  abstract class ExceptionController {
	//日志文件
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public JsonResult exp(Exception e){
		logger.error(e.getMessage(), e);
		return new JsonResult(e);
	}
}




