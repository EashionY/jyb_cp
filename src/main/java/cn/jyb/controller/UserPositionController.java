package cn.jyb.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jyb.service.UserPositionService;
import cn.jyb.util.JsonResult;

@Controller
@RequestMapping("/position")
public class UserPositionController extends ExceptionController {

	@Resource
	private UserPositionService userPositionService;

	/**
	 * 保存/更新用户位置
	 * @param userId 用户id
	 * @param userLon 经度
	 * @param userLat 纬度
	 * @param region 地区
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public JsonResult save(Integer userId, String userLon, String userLat, String region){
		return new JsonResult(userPositionService.save(userId, userLon, userLat, region));
	}
	
	
	
}
