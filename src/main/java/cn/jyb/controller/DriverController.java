package cn.jyb.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jyb.service.DriverService;
import cn.jyb.util.JsonResult;

@Controller
@RequestMapping("/driver")
public class DriverController {

	@Resource
	private DriverService driverService;
	
	@RequestMapping("/addDriver")
	@ResponseBody
	public JsonResult addDriver(HttpServletRequest request) throws IOException{
		boolean tf = driverService.addDriver(request);
		return new JsonResult(tf);
	}
	
	@RequestMapping("/dealDriver")
	@ResponseBody
	public JsonResult dealDriver(HttpServletResponse response,Integer driverId,Integer driverStatus){
		response.setHeader("Access-Control-Allow-Origin", "*");
		Integer status = driverService.dealDriver(driverId, driverStatus);
		return new JsonResult(status);
	}
	
	@RequestMapping("/findAllDriver")
	@ResponseBody
	public JsonResult findAllDriver(HttpServletResponse response,Integer driverStatus,Integer page,Integer pageSize){
		response.setHeader("Access-Control-Allow-Origin", "*");
		List<Map<String,Object>> list = driverService.findAllDriver(driverStatus, page, pageSize);
		return new JsonResult(list);
	}
	
	
	
	
}
