package cn.jyb.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

public interface AlipayService {

	/**
	 * 对支付宝支付信息进行签名(APP)
	 * @param out_trade_no 订单号(学员约教练时，约教记录id即为订单号。其余情况可不传此参)
	 * @param subject 订单标题
	 * @param body 订单详细内容
	 * @param total_amount 总价
	 * @param payer_id 支付者用户id
	 * @param receiver_id 收款方用户id(若为驾校，则对应的为驾校id)
	 * @param address 二维码订单收货地址
	 * @param orderType 订单类型（1-驾校订单，2-教练订单，3-二维码订单）
	 * @param name 二维码订单收货人姓名
	 * @param phone 二维码订单收货人电话
	 * @return
	 */
	public String sign(String out_trade_no,String subject,String body,String total_amount,String payer_id,
			String receiver_id,String address,String orderType,String name,String phone);
	/**
	 * 对支付宝支付信息进行签名(WEB)
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
	 * 支付成功后，回调
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	public String notify(HttpServletRequest request) throws IOException;
}
