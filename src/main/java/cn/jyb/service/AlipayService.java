package cn.jyb.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

public interface AlipayService {

	/**
	 * ��֧����֧����Ϣ����ǩ��(APP)
	 * @param out_trade_no ������(ѧԱԼ����ʱ��Լ�̼�¼id��Ϊ�����š���������ɲ����˲�)
	 * @param subject ��������
	 * @param body ������ϸ����
	 * @param total_amount �ܼ�
	 * @param payer_id ֧�����û�id
	 * @param receiver_id �տ�û�id(��Ϊ��У�����Ӧ��Ϊ��Уid)
	 * @param address ��ά�붩���ջ���ַ
	 * @param orderType �������ͣ�1-��У������2-����������3-��ά�붩����
	 * @param name ��ά�붩���ջ�������
	 * @param phone ��ά�붩���ջ��˵绰
	 * @return
	 */
	public String sign(String out_trade_no,String subject,String body,String total_amount,String payer_id,
			String receiver_id,String address,String orderType,String name,String phone);
	/**
	 * ��֧����֧����Ϣ����ǩ��(WEB)
	 * @param out_trade_no
	 * @param subject
	 * @param body
	 * @param total_amount
	 * @param payer_id
	 * @param receiver_id
	 * @param address
	 * @param orderType
	 * @param name
	 * @param phone
	 * @return
	 */
	public String webSign(String out_trade_no,String subject,String body,String total_amount,String payer_id,
			String receiver_id,String address,String orderType,String name,String phone);
	/**
	 * ֧���ɹ��󣬻ص�
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	public String notify(HttpServletRequest request) throws IOException;
}
