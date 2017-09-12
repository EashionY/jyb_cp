package cn.jyb.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.jyb.entity.Coach;

public interface CoachDao {
	
	/**
	 * 通过教练名字查找
	 * @param name
	 * @return
	 */
	public List<Map<String,Object>> findCoachByName(String coach_name);
	
	/**
	 * 热门教练
	 * @return
	 */
	public List<Coach> hotCoach(@Param("offset")int offset,@Param("pageSize")int pageSize,@Param("coach_area")String coach_area);
	
	/**
	 * 教练资格申请
	 * @param coach
	 */
	public void insertCoach(Coach coach);
	
	/**
	 * 教学设置
	 * @param coach_id
	 * @param coach_paying_two
	 * @param coach_paying_three
	 */
	public void teachSet(int coach_id,double coach_paying_two,double coach_paying_three,int coach_freeshuttle);
	
	/**
	 * 更新教练被浏览数
	 * @param coach_id
	 */
	public void updateCoachBrowse(String coach_id);
	
	/**
	 * 列出所有教练
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	public List<Map<String,Object>> listAllCoach(int offset,int pageSize);
	
	/**
	 * 查找各个状态的教练
	 * @param coach_status('0'审核中,'1'已通过,'2'未通过)
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	public List<Map<String,Object>> listCoachByStatus(String coach_status,int offset,int pageSize);
	
	/**
	 * 操作教练资格申请
	 * @param coach_id
	 * @param coach_status
	 * @return
	 */
	public int dealCoach(int coach_id,String coach_status);
	
	/**
	 * 教练的总数
	 * @return
	 */
	public int coachTotalNum();
	
	/**
	 * 对应状态的教练数量
	 * @param coach_status
	 * @return
	 */
	public int coachNum(String coach_status);
	
	/**
	 * 更新教练教学场地与学员的距离
	 * @param coach_id
	 * @param distance
	 * @return 更新的记录数
	 */
	public int updateDistance(@Param("coach_id")int coach_id,@Param("distance")String distance);

	/**
	 * 查看附近的教练(距离)
	 * @param offset
	 * @param pageSize
	 * @param coach_area
	 * @return 附近教练的列表
	 */
	public List<Coach> coachNearby(@Param("offset")int offset,@Param("pageSize")int pageSize,@Param("coach_area")String coach_area);
	
	/**
	 * 查看附近的教练(距离，性别)
	 * @param offset
	 * @param pageSize
	 * @param coach_area
	 * @param coach_sex
	 * @return
	 */
	public List<Coach> coachNearbySex(@Param("offset")int offset,@Param("pageSize")int pageSize,
			@Param("coach_area")String coach_area,@Param("coach_sex")String coach_sex);
	
	/**
	 * 查看附近教练(距离，范围)
	 * @param offset
	 * @param pageSize
	 * @param coach_area
	 * @param range
	 * @return
	 */
	public List<Coach> coachNearbyRange(@Param("offset")int offset,@Param("pageSize")int pageSize,
			@Param("coach_area")String coach_area,@Param("range")int range);
	
	/**
	 * 查看附近教练(距离，性别，范围)
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
	 * 查看已通过审核的教练
	 * @return 教练列表
	 */
	public List<Coach> listPassedCoach();
	
	/**
	 * 更新教练综合评分
	 * @param coach_score
	 * @return 更新记录条数
	 */
	public int updateCoachScore(@Param("coach_score")double coach_score,@Param("coach_id")int coach_id);
	
	/**
	 * 通过id查找教练
	 * @param coach_id
	 * @return
	 */
	public Coach findById(int coach_id);
	
	/**
	 * 修改教练信息（主要用于资格申请未通过，二次上传资料）
	 * @param coach
	 * @return
	 */
	public int modifyCoachinfo(Coach coach);
	
	/**
	 * 根据评分高低查找教练
	 * @param offset
	 * @param pageSize
	 * @param coach_area
	 * @return
	 */
	public List<Coach> listCoachByScore(@Param("offset")int offset,@Param("pageSize")int pageSize,@Param("coach_area")String coach_area);
	
	/**
	 * 根据评分查找教练(性别)
	 * @param offset
	 * @param pageSize
	 * @param coach_area
	 * @param coach_sex
	 * @return
	 */
	public List<Coach> listCoachByScoreSex(@Param("offset")int offset,@Param("pageSize")int pageSize,
			@Param("coach_area")String coach_area,@Param("coach_sex")String coach_sex);
	
	/**
	 * 根据评分查找教练(范围)
	 * @param offset
	 * @param pageSize
	 * @param coach_area
	 * @param range
	 * @return
	 */
	public List<Coach> listCoachByScoreRange(@Param("offset")int offset,@Param("pageSize")int pageSize,
			@Param("coach_area")String coach_area,@Param("range")int range);
	
	/**
	 * 根据评分查找教练(性别，范围)
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
	 * 默认排序查找教练
	 * @param offset
	 * @param pageSize
	 * @param coach_area
	 * @return
	 */
	public List<Coach> listCoachDefault(@Param("offset")int offset,@Param("pageSize")int pageSize,@Param("coach_area")String coach_area);
	
	/**
	 * 默认排序查找教练(性别)
	 * @param offset
	 * @param pageSize
	 * @param coach_area
	 * @param coach_sex
	 * @return
	 */
	public List<Coach> listCoachDefaultSex(@Param("offset")int offset,@Param("pageSize")int pageSize,@Param("coach_area")String coach_area,@Param("coach_sex")String coach_sex);
	
	/**
	 * 默认排序查找教练(范围)
	 * @param offset
	 * @param pageSize
	 * @param coach_area
	 * @param range
	 * @return
	 */
	public List<Coach> listCoachDefaultRange(@Param("offset")int offset,@Param("pageSize")int pageSize,@Param("coach_area")String coach_area,@Param("range")int range);
	
	/**
	 * 默认排序查找教练(性别，范围)
	 * @param offset
	 * @param pageSize
	 * @param coach_area
	 * @param coach_sex
	 * @param range
	 * @return
	 */
	public List<Coach> listCoachDefaultSexRange(@Param("offset")int offset,@Param("pageSize")int pageSize,@Param("coach_area")String coach_area,@Param("coach_sex")String coach_sex,@Param("range")int range);
	
	/**
	 * 通过教练名模糊查找(已通过审核教练)
	 * @param coach_name
	 * @param coach_status
	 * @return
	 */
	public List<Coach> findByName(@Param("coach_name")String coach_name,@Param("coach_area")String coach_area);
	
	/**
	 * 查看该驾校的推荐教练
	 * @param school_name
	 * @return
	 */
	public List<Map<String,Object>> listRecomdCoach(@Param("school_name")String school_name,@Param("coach_status")String coach_status,
			@Param("offset")Integer offset,@Param("pageSize")Integer pageSize);
	
	/**
	 * 通过用户id查找教练
	 * @param user_id
	 * @return
	 */
	public Coach findByUserId(Integer user_id);
}
