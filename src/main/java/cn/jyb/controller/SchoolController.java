package cn.jyb.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jyb.entity.School;
import cn.jyb.service.SchoolService;
import cn.jyb.service.TeachRecordService;
import cn.jyb.util.JsonResult;
@Controller
@RequestMapping("/school")
public class SchoolController extends ExceptionController {

	@Resource
	private SchoolService schoolService;
	
	@Resource
	private TeachRecordService teachRecordService;
	
	/**
	 * ���¼�У�������
	 * @param school_id
	 * @return boolean
	 */
	@RequestMapping("/updateSchoolBrowse")
	@ResponseBody
	public JsonResult updateSchoolBrowse(String school_id){
		boolean tf = schoolService.updateSchoolBrowse(school_id);
		return new JsonResult(tf);
	}
	
	/**
	 * ���վ���������Ҽ�У
	 * @param lon1 �û�����
	 * @param lat1 �û�γ��
	 * @param page ҳ��
	 * @param pageSize ÿҳ��Ŀ��
	 * @return list
	 */
	@RequestMapping("/findSchoolByDistance")
	@ResponseBody
	public JsonResult findSchoolByDistance(String lon1, String lat1,String page,String pageSize,String school_area){
		List<School> list = schoolService.findSchoolByDistance(lon1, lat1, Integer.parseInt(page), 
				Integer.parseInt(pageSize), school_area);
		return new JsonResult(list);
	}
	
	/**
	 * ���������������Ҽ�У
	 * @param page
	 * @param pageSize
	 * @return list
	 */
	@RequestMapping("/findSchoolByBrowse")
	@ResponseBody
	public JsonResult findSchoolByBrowse(String page,String pageSize,String school_area){
		List<School> list = schoolService.findSchoolByBrowse(Integer.parseInt(page), Integer.parseInt(pageSize), school_area);
		return new JsonResult(list);
	}
	
	/**
	 * ������У(��̨����ϵͳ)
	 * @param school_name
	 * @param school_address
	 * @param school_slogan
	 * @param school_jingdu
	 * @param school_weidu
	 * @param school_price
	 * @param school_tel
	 * @param school_area
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/addSchool",produces="application/json;charset=utf-8")
	@ResponseBody
	public JsonResult addSchool(String school_name, String school_address, String school_slogan, String school_jingdu,
			String school_weidu, String school_price, String school_tel, String school_area, HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		response.setHeader("Access-Control-Allow-Origin", "*");
		boolean tf = schoolService.addSchool(school_name, school_address, school_slogan, school_jingdu,
				school_weidu, school_price, school_tel,school_area, request);
		return new JsonResult(tf);
	}
	
	@RequestMapping(value="/findAllSchool")
	@ResponseBody
	public JsonResult findAllSchool(String page,String pageSize,String school_area,HttpServletRequest req,HttpServletResponse res){
		res.addHeader("Access-Control-Allow-Origin", "*");
		Map<String,Object> result = schoolService.findAllSchool(Integer.parseInt(page), Integer.parseInt(pageSize), school_area);
		return new JsonResult(result);
	}
	
	/**
	 * ͨ�����ֲ��Ҽ�У(ǰ�˺�̨����)
	 * @param school_name
	 * @param req
	 * @param res
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/searchSchool")
	@ResponseBody
	public JsonResult searchSchool(String school_name,HttpServletRequest req,HttpServletResponse res) throws UnsupportedEncodingException{
		req.setCharacterEncoding("utf-8");
		res.addHeader("Access-Control-Allow-Origin", "*");
		List<School> schools = schoolService.searchSchool(school_name);
		return new JsonResult(schools);
	}
	
	@RequestMapping(value="/modifySchoolInfo",produces="application/json;charset=utf-8")
	@ResponseBody
	public JsonResult modifySchoolInfo(String school_id, String school_name, String school_address, String school_slogan,
			String school_jingdu, String school_weidu, String school_price, String school_tel, String school_area,
			HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
		response.addHeader("Access-Control-Allow-Origin", "*");
		boolean tf = schoolService.modifySchoolInfo(school_id, school_name, school_address, school_slogan,
				school_jingdu, school_weidu, school_price, school_tel, school_area, request);
		return new JsonResult(tf);
	}
	
	@RequestMapping(value="/deleteSchool")
	@ResponseBody
	public JsonResult deleteSchool(String school_id,HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin", "*");
		boolean tf = schoolService.deleteSchool(school_id);
		return new JsonResult(tf);
	}
	
	@RequestMapping(value="/modifySchoolLogo")
	@ResponseBody
	public JsonResult modifySchoolLogo(HttpServletRequest request,HttpServletResponse response,String school_name) throws IOException{
		response.setHeader("Access-Control-Allow-Origin", "*");
		boolean tf = schoolService.modifySchoolLogo(request, school_name);
		return new JsonResult(tf);
	}
	
	/**
	 * ��У���飨ǰ���ͨ�ã�
	 * @param response
	 * @param school_id
	 * @param lon1
	 * @param lat1
	 * @return
	 */
	@RequestMapping("/schoolDetail")
	@ResponseBody
	public JsonResult schoolDetail(HttpServletResponse response,int school_id,Double lon1,Double lat1){
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String,Object> result = schoolService.schoolDetail(school_id, lon1, lat1);
		return new JsonResult(result);
	}
	
	/**
	 * ��̨����ϵͳ����ӽ�ѧ����
	 * @param request
	 * @param response
	 * @param school_id
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/addEnvironment")
	@ResponseBody
	public JsonResult addEnvironment(HttpServletRequest request,HttpServletResponse response,int school_id) throws IOException{
		response.setHeader("Access-Control-Allow-Origin", "*");
		boolean tf = schoolService.addEnvironment(request, school_id);
		return new JsonResult(tf);
	}
	
	@RequestMapping("/listSchoolEval")
	@ResponseBody
	public JsonResult listSchoolEval(String school_name, int evaltype, int page, int pageSize){
		Map<String, Object> result = teachRecordService.listSchoolEval(school_name, evaltype, page, pageSize);
		return new JsonResult(result);
	}
	
	@RequestMapping("/addPackage")
	@ResponseBody
	public JsonResult addPackage(HttpServletResponse response,Integer school_id, String packageName, String packageType, String packageIntro,
			String packagePrice, String packageContent){
		response.setHeader("Access-Control-Allow-Origin", "*");
		boolean tf = schoolService.addPackage(school_id, packageName, packageType, packageIntro, packagePrice, packageContent);
		return new JsonResult(tf);
	}
	
	@RequestMapping("/deletePackage")
	@ResponseBody
	public JsonResult deletePackage(HttpServletResponse response,int packageId){
		response.setHeader("Access-Control-Allow-Origin", "*");
		boolean tf = schoolService.deletePackage(packageId);
		return new JsonResult(tf);
	}
	
	@RequestMapping("/modifyPackage")
	@ResponseBody
	public JsonResult modifyPackage(HttpServletResponse response,int packageId,int school_id, String packageName, String packageType, String packageIntro,
			String packagePrice, String packageContent){
		response.setHeader("Access-Control-Allow-Origin", "*");
		boolean tf = schoolService.modifyPackage(packageId, school_id, packageName, packageType, packageIntro, packagePrice, packageContent);
		return new JsonResult(tf);
	}
	
	
}
