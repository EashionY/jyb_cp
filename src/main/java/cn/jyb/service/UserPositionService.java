package cn.jyb.service;

public interface UserPositionService {

	/**
	 * �����û���λ����Ϣ
	 * @param userId
	 * @param userLon
	 * @param userLat
	 * @param region
	 * @return
	 */
	public boolean save(Integer userId,String userLon,String userLat,String region);
	
}
