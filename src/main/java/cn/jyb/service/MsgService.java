package cn.jyb.service;
/**
 * 短信验证码
 * @author Eashion
 *
 */
public interface MsgService {
	
	/**
	 * 发送注册验证码
	 * @param phone
	 * @return
	 */
	public String sendRegCode(String phone);
	
	/**
	 * 发送改密验证码
	 * @param phone
	 * @return
	 */
	public String sendPwdCode(String phone);

	/**
	 * 发送支付验证码
	 * @param phone
	 * @return
	 */
	public String sendPayCode(String phone);
	
	/**
	 * 发送变更手机号验证码
	 * @param phone
	 * @return
	 */
	public String sendPhoneCode(String phone);
	
	/**
	 * 发送一键挪车短信
	 * @param userId 要求挪车的用户id
	 * @param moveUserId 挪车人的用户id
	 * @param vehicleNo 车牌号
	 * @return
	 */
	public boolean sendMoveCarMsg(Integer userId,Integer moveUserId,String vehicleNo);
	
	
}
