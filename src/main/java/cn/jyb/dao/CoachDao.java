package cn.jyb.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.jyb.entity.Coach;

public interface CoachDao {
	
	/**
	 * ͨ���������ֲ���
	 * @param name
	 * @return
	 */
	public List<Map<String,Object>> findCoachByName(String coach_name);
	
	/**
	 * ���Ž���
	 * @return
	 */
	public List<Coach> hotCoach(@Param("offset")int offset,@Param("pageSize")int pageSize,@Param("coach_area")String coach_area);
	
	/**
	 * �����ʸ�����
	 * @param coach
	 */
	public void insertCoach(Coach coach);
	
	/**
	 * ��ѧ����
	 * @param coach_id
	 * @param coach_paying_two
	 * @param coach_paying_three
	 */
	public void teachSet(int coach_id,double coach_paying_two,double coach_paying_three,int coach_freeshuttle);
	
	/**
	 * ���½����������
	 * @param coach_id
	 */
	public void updateCoachBrowse(String coach_id);
	
	/**
	 * �г����н���
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	public List<Map<String,Object>> listAllCoach(int offset,int pageSize);
	
	/**
	 * ���Ҹ���״̬�Ľ���
	 * @param coach_status('0'�����,'1'��ͨ��,'2'δͨ��)
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	public List<Map<String,Object>> listCoachByStatus(String coach_status,int offset,int pageSize);
	
	/**
	 * ���������ʸ�����
	 * @param coach_id
	 * @param coach_status
	 * @return
	 */
	public int dealCoach(int coach_id,String coach_status);
	
	/**
	 * ����������
	 * @return
	 */
	public int coachTotalNum();
	
	/**
	 * ��Ӧ״̬�Ľ�������
	 * @param coach_status
	 * @return
	 */
	public int coachNum(String coach_status);
	
	/**
	 * ���½�����ѧ������ѧԱ�ľ���
	 * @param coach_id
	 * @param distance
	 * @return ���µļ�¼��
	 */
	public int updateDistance(@Param("coach_id")int coach_id,@Param("distance")String distance);

	/**
	 * �鿴�����Ľ���(����)
	 * @param offset
	 * @param pageSize
	 * @param coach_area
	 * @return �����������б�
	 */
	public List<Coach> coachNearby(@Param("offset")int offset,@Param("pageSize")int pageSize,@Param("coach_area")String coach_area);
	
	/**
	 * �鿴�����Ľ���(���룬�Ա�)
	 * @param offset
	 * @param pageSize
	 * @param coach_area
	 * @param coach_sex
	 * @return
	 */
	public List<Coach> coachNearbySex(@Param("offset")int offset,@Param("pageSize")int pageSize,
			@Param("coach_area")String coach_area,@Param("coach_sex")String coach_sex);
	
	/**
	 * �鿴��������(���룬��Χ)
	 * @param offset
	 * @param pageSize
	 * @param coach_area
	 * @param range
	 * @return
	 */
	public List<Coach> coachNearbyRange(@Param("offset")int offset,@Param("pageSize")int pageSize,
			@Param("coach_area")String coach_area,@Param("range")int range);
	
	/**
	 * �鿴��������(���룬�Ա𣬷�Χ)
	 * @param offset
	 * @param pageSize
	 * @param coach_area
	 * @param coach_sex
	 * @param range
	 * @return
	 */
	public List<Coach> coachNearbySexRange(@Param("offset")int offset,@Param("pageSize")int pageSize,
			@Param("coach_area")String coach_area,@Param("coach_sex")String coach_sex,@Param("range")int range);
	
	/**
	 * �鿴��ͨ����˵Ľ���
	 * @return �����б�
	 */
	public List<Coach> listPassedCoach();
	
	/**
	 * ���½����ۺ�����
	 * @param coach_score
	 * @return ���¼�¼����
	 */
	public int updateCoachScore(@Param("coach_score")double coach_score,@Param("coach_id")int coach_id);
	
	/**
	 * ͨ��id���ҽ���
	 * @param coach_id
	 * @return
	 */
	public Coach findById(int coach_id);
	
	/**
	 * �޸Ľ�����Ϣ����Ҫ�����ʸ�����δͨ���������ϴ����ϣ�
	 * @param coach
	 * @return
	 */
	public int modifyCoachinfo(Coach coach);
	
	/**
	 * �������ָߵͲ��ҽ���
	 * @param offset
	 * @param pageSize
	 * @param coach_area
	 * @return
	 */
	public List<Coach> listCoachByScore(@Param("offset")int offset,@Param("pageSize")int pageSize,@Param("coach_area")String coach_area);
	
	/**
	 * �������ֲ��ҽ���(�Ա�)
	 * @param offset
	 * @param pageSize
	 * @param coach_area
	 * @param coach_sex
	 * @return
	 */
	public List<Coach> listCoachByScoreSex(@Param("offset")int offset,@Param("pageSize")int pageSize,
			@Param("coach_area")String coach_area,@Param("coach_sex")String coach_sex);
	
	/**
	 * �������ֲ��ҽ���(��Χ)
	 * @param offset
	 * @param pageSize
	 * @param coach_area
	 * @param range
	 * @return
	 */
	public List<Coach> listCoachByScoreRange(@Param("offset")int offset,@Param("pageSize")int pageSize,
			@Param("coach_area")String coach_area,@Param("range")int range);
	
	/**
	 * �������ֲ��ҽ���(�Ա𣬷�Χ)
	 * @param offset
	 * @param pageSize
	 * @param coach_area
	 * @param coach_sex
	 * @param range
	 * @return
	 */
	public List<Coach> listCoachByScoreSexRange(@Param("offset")int offset,@Param("pageSize")int pageSize,
			@Param("coach_area")String coach_area,@Param("coach_sex")String coach_sex,@Param("range")int range);
	
	/**
	 * Ĭ��������ҽ���
	 * @param offset
	 * @param pageSize
	 * @param coach_area
	 * @return
	 */
	public List<Coach> listCoachDefault(@Param("offset")int offset,@Param("pageSize")int pageSize,@Param("coach_area")String coach_area);
	
	/**
	 * Ĭ��������ҽ���(�Ա�)
	 * @param offset
	 * @param pageSize
	 * @param coach_area
	 * @param coach_sex
	 * @return
	 */
	public List<Coach> listCoachDefaultSex(@Param("offset")int offset,@Param("pageSize")int pageSize,@Param("coach_area")String coach_area,@Param("coach_sex")String coach_sex);
	
	/**
	 * Ĭ��������ҽ���(��Χ)
	 * @param offset
	 * @param pageSize
	 * @param coach_area
	 * @param range
	 * @return
	 */
	public List<Coach> listCoachDefaultRange(@Param("offset")int offset,@Param("pageSize")int pageSize,@Param("coach_area")String coach_area,@Param("range")int range);
	
	/**
	 * Ĭ��������ҽ���(�Ա𣬷�Χ)
	 * @param offset
	 * @param pageSize
	 * @param coach_area
	 * @param coach_sex
	 * @param range
	 * @return
	 */
	public List<Coach> listCoachDefaultSexRange(@Param("offset")int offset,@Param("pageSize")int pageSize,@Param("coach_area")String coach_area,@Param("coach_sex")String coach_sex,@Param("range")int range);
	
	/**
	 * ͨ��������ģ������(��ͨ����˽���)
	 * @param coach_name
	 * @param coach_status
	 * @return
	 */
	public List<Coach> findByName(@Param("coach_name")String coach_name,@Param("coach_area")String coach_area);
	
	/**
	 * �鿴�ü�У���Ƽ�����
	 * @param school_name
	 * @return
	 */
	public List<Map<String,Object>> listRecomdCoach(@Param("school_name")String school_name,@Param("coach_status")String coach_status,
			@Param("offset")Integer offset,@Param("pageSize")Integer pageSize);
	
	/**
	 * ͨ���û�id���ҽ���
	 * @param user_id
	 * @return
	 */
	public Coach findByUserId(Integer user_id);
}
