package cn.jyb.service;

public interface MsgService {
	
	/**
	 * ����ע����֤��
	 * @param phone
	 * @return
	 */
	public String sendRegCode(String phone);
	
	/**
	 * ���͸�����֤��
	 * @param phone
	 * @return
	 */
	public String sendPwdCode(String phone);

	/**
	 * ����֧����֤��
	 * @param phone
	 * @return
	 */
	public String sendPayCode(String phone);
}
