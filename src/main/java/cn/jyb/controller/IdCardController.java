package cn.jyb.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jyb.service.IdCardService;
import cn.jyb.util.JsonResult;

@Controller
@RequestMapping("/idCard")
public class IdCardController extends ExceptionController {

	@Resource
	private IdCardService idCardService;

	/**
	 * 添加身份证（身份证实名认证）.
	 *
	 * @param request the request
	 * @return the json result
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws Exception the exception
	 */
	@RequestMapping(value = "/addIdCard", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult addIdCard(HttpServletRequest request) throws IOException, Exception {
		return new JsonResult(idCardService.addIdCard(request));
	}
	
	

}
