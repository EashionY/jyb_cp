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
	 * ����/�����û�λ��
	 * @param userId �û�id
	 * @param userLon ����
	 * @param userLat γ��
	 * @param region ����
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public JsonResult save(Integer userId, String userLon, String userLat, String region){
		return new JsonResult(userPositionService.save(userId, userLon, userLat, region));
	}
	
	
	
}
