package cn.jyb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jyb.util.JsonResult;
import cn.jyb.util.Version;

@Controller()
@RequestMapping("/version")
public class VersionController {

	@RequestMapping("/getVersion")
	@ResponseBody
	public JsonResult getVersion(){
		return new JsonResult(Version.getVersion());
	}
	
}
