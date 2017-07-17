package cn.jyb.service;

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
}
