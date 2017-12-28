package cn.jyb.service;

public interface UserPositionService {

	/**
	 * 保存用户的位置信息
	 * @param userId
	 * @param userLon
	 * @param userLat
	 * @param region
	 * @return
	 */
	public boolean save(Integer userId,String userLon,String userLat,String region);
	
}
