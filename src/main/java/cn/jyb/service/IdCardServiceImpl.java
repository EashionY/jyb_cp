package cn.jyb.service;

import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import cn.jyb.dao.IdCardMapper;
import cn.jyb.entity.IdCard;
import cn.jyb.exception.IdCardException;
import cn.jyb.util.HttpUtil;
import cn.jyb.util.Upload;
import net.sf.json.JSONObject;
@Service("idCardService")
public class IdCardServiceImpl implements IdCardService {

	@Resource
	private IdCardMapper idCardMapper;
	
	//聚合数据，身份证实名认证appkey
	private static final String KEY = "3556003f77d86adfe5751488341fdb41";
	
	public boolean addIdCard(HttpServletRequest request) throws Exception {
		request.setCharacterEncoding("UTF-8");
		Integer userId = Integer.parseInt(request.getParameter("userId"));
		List<String> paths = Upload.uploadImg(request, "IdCard", ""+userId);
		if(paths == null || paths.size() == 0){
			throw new IdCardException("身份证上传失败");
		}
		String idcardPic = paths.get(0);
		String realname = request.getParameter("realname");
		String idcardNo = request.getParameter("idcardNo");
		boolean success = certIdCard(idcardNo, realname);
		if(!success){
			throw new IdCardException("认证失败");
		}
		IdCard idCard = idCardMapper.findByUserId(userId);
		int i;
		if(idCard != null){
			idCard.setIdcardNo(idcardNo);
			idCard.setIdcardPic(idcardPic);
			idCard.setIdcardRealname(realname);
			idCard.setIdcardSex(request.getParameter("sex"));
			idCard.setIdcardAddress(request.getParameter("address"));
			idCard.setRealnameStatus(1);
			i = idCardMapper.updateByPrimaryKeySelective(idCard);
		}else{
			idCard = new IdCard();
			idCard.setUserId(userId);
			idCard.setIdcardNo(idcardNo);
			idCard.setIdcardPic(idcardPic);
			idCard.setIdcardRealname(realname);
			idCard.setIdcardSex(request.getParameter("sex"));
			idCard.setIdcardAddress(request.getParameter("address"));
			idCard.setRealnameStatus(1);
			i = idCardMapper.insertSelective(idCard);
		}
		return i==1;
	}
	
	/**
	 * 身份证实名认证(调用聚合数据接口)
	 * 
	 * @param idcardNo
	 * @param realname
	 * @return
	 * @throws Exception 
	 */
	private boolean certIdCard(String idcardNo,String realname) throws Exception{
		String utf8realname = URLEncoder.encode(realname, "UTF-8");
		String url = "http://op.juhe.cn/idcard/query?key="+KEY+"&idcard="+idcardNo+"&realname="+utf8realname;
		System.out.println(url);
		JSONObject json = JSONObject.fromObject(HttpUtil.sendGet(url, "UTF-8"));
		System.out.println("返回结果："+json);
		Integer error_code = (Integer) json.get("error_code");
		if(error_code == 0){
			Map<String,Object> result = (Map<String, Object>) json.get("result");
			System.out.println(result);
			Integer res = (Integer) result.get("res");
			return res==1;
		}else {
			throw new IdCardException("服务器异常，请等待管理员处理");
		}
	}

}
