package cn.jyb.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.jyb.entity.School;

public interface SchoolService {

	/**
	 * ���¼�У�������
	 * @param school_id
	 * @return
	 */
	public boolean updateSchoolBrowse(String school_id);
	
	/**
	 * ���վ���������Ҽ�У
	 * @param lon1 �û�����
	 * @param lat1 �û�γ��
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<School> findSchoolByDistance(String lon1,String lat1,int page,int pageSize,String school_area);
	
	/**
	 * ���������������Ҽ�У
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<School> findSchoolByBrowse(int page,int pageSize,String school_area);
	
	/**
	 * ��Ӽ�У����̨����ϵͳ��
	 * @param school_name
	 * @param school_address
	 * @param school_slogan
	 * @param school_jingdu
	 * @param school_weidu
	 * @param school_price
	 * @param school_tel
	 * @param school_area
	 * @param request
	 * @throws IOException 
	 */
	public boolean addSchool(String school_name,String school_address,String school_slogan,
			String school_jingdu,String school_weidu,String school_price,String school_tel,
			String school_area,HttpServletRequest request) throws IOException;
	
	/**
	 * �޸ļ�У��Ϣ����̨����ϵͳ��
	 * @param school_id
	 * @param school_name
	 * @param school_address
	 * @param school_slogan
	 * @param school_jingdu
	 * @param school_weidu
	 * @param school_price
	 * @param school_tel
	 * @param school_area
	 * @return
	 * @throws UnsupportedEncodingException 
	 * @throws IOException 
	 */
	public boolean modifySchoolInfo(String school_id,String school_name,String school_address,String school_slogan,
			String school_jingdu,String school_weidu,String school_price,String school_tel,
			String school_area,HttpServletRequest req) throws UnsupportedEncodingException;
	
	/**
	 * �޸ļ�Уlogo(��̨����ϵͳ)
	 * @param request
	 * @param school_name
	 * @return
	 * @throws IOException
	 */
	public boolean modifySchoolLogo(HttpServletRequest request,String school_name) throws IOException;
	
	/**
	 * ͨ����У��������У����̨����ϵͳ��
	 * @param school_name
	 * @return
	 */
	public List<School> searchSchool(String school_name);
	
	/**
	 * ����ĳ�����������м�У����̨����ϵͳ��
	 * @param page
	 * @param pageSize
	 * @param school_area
	 * @return
	 */
	public Map<String, Object> findAllSchool(int page,int pageSize,String school_area);
	
	/**
	 * ɾ����У����̨����ϵͳ��
	 * @param school_id
	 * @return
	 */
	public boolean deleteSchool(String school_id);
	
	/**
	 * ��У����
	 * @param school_id
	 * @return
	 */
	public Map<String,Object> schoolDetail(int school_id,double lon1,double lat1);
	
	/**
	 * ��Ӽ�У��������(��̨����ϵͳ)
	 * @param request
	 * @param school_id
	 * @return
	 * @throws IOException 
	 */
	public boolean addEnvironment(HttpServletRequest request,int school_id) throws IOException;
	
	/**
	 * ��Ӽ�У�����ײ�(��̨����ϵͳ)
	 * @param school_id
	 * @param packageName
	 * @param packageType
	 * @param packageIntro
	 * @param packagePrice
	 * @param packageContent
	 * @return
	 */
	public boolean addPackage(Integer school_id,String packageName,String packageType,String packageIntro,String packagePrice,String packageContent);
	
	/**
	 * ɾ�������ײ�(��̨����ϵͳ)
	 * @param packageId
	 * @return
	 */
	public boolean deletePackage(int packageId);
	
	/**
	 * �޸ı����ײ�
	 * @param packageId
	 * @param school_id
	 * @param packageName
	 * @param packageType
	 * @param packageIntro
	 * @param packagePrice
	 * @param packageContent
	 * @return
	 */
	public boolean modifyPackage(int packageId,int school_id,String packageName,String packageType,String packageIntro,
			String packagePrice,String packageContent);
}
