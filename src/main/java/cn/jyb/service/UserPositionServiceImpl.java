package cn.jyb.service;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.jyb.dao.UserPositionMapper;
import cn.jyb.entity.UserPosition;
import cn.jyb.exception.DataBaseException;
@Service("userPositionService")
public class UserPositionServiceImpl implements UserPositionService {

	@Resource
	private UserPositionMapper userPosMapper;
	
	@Override
	public boolean save(Integer userId, String userLon, String userLat, String region) {
		UserPosition userPos = userPosMapper.findByUserId(userId);
		int i;
		if(userPos == null){
			userPos = new UserPosition();
			userPos.setUserId(userId);
			userPos.setUserLon(new BigDecimal(userLon).setScale(6));
			userPos.setUserLat(new BigDecimal(userLat).setScale(6));
			userPos.setRegion(region);
			try {
				i = userPosMapper.insertSelective(userPos);
			} catch (Exception e) {
				e.printStackTrace();
				throw new DataBaseException("数据库异常");
			}
		}else{
			userPos.setUserLon(new BigDecimal(userLon).setScale(6));
			userPos.setUserLat(new BigDecimal(userLat).setScale(6));
			userPos.setRegion(region);
			try {
				i = userPosMapper.updateByPrimaryKeySelective(userPos);
			} catch (Exception e) {
				e.printStackTrace();
				throw new DataBaseException("数据库异常");
			}
		}
		return i == 1;
	}

}
