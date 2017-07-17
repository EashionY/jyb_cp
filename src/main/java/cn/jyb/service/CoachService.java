package cn.jyb.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.jyb.entity.Coach;

public interface CoachService {
	
	/**
	 * ͨ��������������
	 * @param coach_name
	 * @return coach
	 */
	public List<Map<String,Object>> findCoachByName(String coach_name,String coach_area);
	
	/**
	 * ���Ž���
	 * @return list
	 */
	public List<Map<String,Object>> hotCoach(int page,int pageSize,String coach_area);
	
	/**
	 * �����ʸ�����
	 * @param user_id
	 * @param phone
	 * @param coach_name
	 * @param coach_sex
	 * @param coach_brithday
	 * @param school_name
	 * @param school_address
	 * @param train_field
	 * @param field_jingdu
	 * @param field_weidu
	 * @param coach_license
	 * @param request
	 * @return coach
	 * @throws IOException
	 */
	public Coach insertCoach(int user_id,String phone,String coach_name,String coach_sex,
			String coach_birthday,String school_name, String school_address,
			String train_field,String field_jingdu,String field_weidu,
			String coach_license,String coach_car,String coach_area,HttpServletRequest request) throws IOException;
	
	/**
	 * ��ѧ����
	 * @param coach_id
	 * @param coach_paying_two
	 * @param coach_paying_three
	 * @param coach_freeshuttle
	 * @return
	 */
	public boolean teachSet(String coach_id,String coach_paying_two,
			String coach_paying_three,String coach_freeshuttle);
	
	/**
	 * ���½����������
	 * @param coach_id ����id
	 * @return
	 */
	public boolean updateCoachBrowse(String coach_id);

	/**
	 * �г����н���(��̨����ϵͳ)
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public Map<String, Object> listAllCoach(int page,int pageSize);
	
	/**
	 * ����״̬���Ҷ�Ӧ�Ľ���(��̨����ϵͳ)
	 * @param coach_status('0'�����,'1'��ͨ��,'2'δͨ��)
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public Map<String, Object> listCoachByStatus(String coach_status,int page,int pageSize);
	
	/**
	 * ���������ʸ�����(��̨����ϵͳ)
	 * @param coach_id
	 * @param coach_status
	 */
	public String dealCoach(String coach_id,String coach_status);
	
	/**
	 * ���Ҹ�������
	 * @param lon �û�����
	 * @param lat �û�γ��
	 * @param page
	 * @param pageSize
	 * @param coach_area
	 * @param coach_sex
	 * @param range
	 * @return ���������б�
	 */
	public List<Map<String,Object>> coachNearby(String lon,String lat,int page,int pageSize,String coach_area,
			String coach_sex,String range);
	
	/**
	 * �������ֲ��ҽ���
	 * @param lon �û�����
	 * @param lat �û�γ��
	 * @param page
	 * @param pageSize
	 * @param coach_area
	 * @param coach_sex
	 * @param range
	 * @return
	 */
	public List<Map<String,Object>> listCoachByScore(String lon,String lat,int page,int pageSize,String coach_area,String coach_sex,String range);
	
	/**
	 * Ĭ��������ҽ���
	 * @param lon �û�����
	 * @param lat �û�γ��
	 * @param page
	 * @param pageSize
	 * @param coach_area
	 * @param coach_sex
	 * @param range
	 * @return
	 */
	public List<Map<String,Object>> listCoachDefault(String lon,String lat,int page,int pageSize,String coach_area,String coach_sex,String range);
}
