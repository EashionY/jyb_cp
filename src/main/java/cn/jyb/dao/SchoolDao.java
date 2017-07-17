package cn.jyb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.jyb.entity.School;

public interface SchoolDao {

	/**
	 * �����У��Ϣ
	 * @param school
	 */
	public void save(School school);
	
	/**
	 * ���¼�У�����
	 * @param school_id
	 */
	public void updateSchoolBrowse(String school_id);
	
	/**
	 * ���¼�У���û�֮��ľ���
	 * @param school_id
	 * @param distance
	 */
	public void updateSchoolDistance(String school_id,String distance);
	
	/**
	 * ��ѯ���м�У�ľ�γ��
	 * @return
	 */
	public List<School> findJWdu();
	
	/**
	 * ���վ���������Ҽ�У
	 * @param offset
	 * @param pageSize
	 * @param school_area ��У��������
	 * @return
	 */
	public List<School> findSchoolByDistance(int offset,int pageSize,String school_area);
	
	/**
	 * ���Ҽ�У��Ĭ������
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	public List<School> findSchool(int offset,int pageSize,String school_area);
	
	/**
	 * ���������������Ҽ�У
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	public List<School> findSchoolByBrowse(int offset,int pageSize,String school_area);
	
	/**
	 * ͨ����У������
	 * @param school_name
	 * @return
	 */
	public School findSchoolByName(String school_name);
	
	/**
	 * �޸ļ�У��Ϣ
	 * @param school
	 * @return
	 */
	public int modifySchoolInfo(School school);

	/**
	 * ͨ����У����ģ����ѯ
	 * @param school_name
	 * @return
	 */
	public List<School> searchSchoolByName(String school_name);
	
	/**
	 * ɾ����У
	 * @param school_id
	 * @return
	 */
	public int deleteSchool(String school_id);
	
	/**
	 * ��ȡĳ��������У����
	 * @param school_area
	 * @return
	 */
	public int getSchoolNum(String school_area);

	/**
	 * �޸ļ�Уlogo
	 * @param school_logo
	 * @param school_name
	 * @return
	 */
	public int modifySchoolLogo(@Param("school_logo")String school_logo,@Param("school_name")String school_name);
}