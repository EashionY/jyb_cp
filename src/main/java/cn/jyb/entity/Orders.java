package cn.jyb.entity;

import java.io.Serializable;
import java.sql.Timestamp;
/**
 * 支付订单
 * @author Eashion
 *
 */
public class Orders implements Serializable {

	private static final long serialVersionUID = -2296521224189308071L;

	//订单id
	private int id;
	//订单号
	private String out_trade_no;
	//标题
	private String subject;
	//订单主体内容
	private String body;
	//总金额
	private String total_amount;
	//收款支付宝用户id
	private String seller_id;
	//创建时间
	private Timestamp creatime;
	//结束时间
	private Timestamp finishtime;
	//交易状态(支付成功TRADE_SUCCESS,交易创建WAIT_BUYER_PAY,交易关闭TRADE_CLOSED,交易完结TRADE_FINISHED)
	private String trade_status;
	//支付方用户id
	private int payer_id;
	//收款人用户id
	private int receiver_id;
	//付款方式
	private String pay_method;
	//订单类型（1-驾校订单，2-教练订单，3-二维码订单，4-钱包充值）
	private String order_type;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(String total_amount) {
		this.total_amount = total_amount;
	}

	public String getSeller_id() {
		return seller_id;
	}

	public void setSeller_id(String seller_id) {
		this.seller_id = seller_id;
	}

	public Timestamp getCreatime() {
		return creatime;
	}

	public void setCreatime(Timestamp creatime) {
		this.creatime = creatime;
	}

	public Timestamp getFinishtime() {
		return finishtime;
	}

	public void setFinishtime(Timestamp finishtime) {
		this.finishtime = finishtime;
	}

	public String getTrade_status() {
		return trade_status;
	}

	public void setTrade_status(String trade_status) {
		this.trade_status = trade_status;
	}

	public int getPayer_id() {
		return payer_id;
	}

	public void setPayer_id(int payer_id) {
		this.payer_id = payer_id;
	}

	public int getReceiver_id() {
		return receiver_id;
	}

	public void setReceiver_id(int receiver_id) {
		this.receiver_id = receiver_id;
	}

	public String getPay_method() {
		return pay_method;
	}

	public void setPay_method(String pay_method) {
		this.pay_method = pay_method;
	}

	public String getOrderType() {
		return order_type;
	}

	public void setOrderType(String order_type) {
		this.order_type = order_type;
	}

	@Override
	public String toString() {
		return "Orders [id=" + id + ", out_trade_no=" + out_trade_no + ", subject=" + subject + ", body=" + body
				+ ", total_amount=" + total_amount + ", seller_id=" + seller_id + ", creatime=" + creatime
				+ ", finishtime=" + finishtime + ", trade_status=" + trade_status + ", payer_id=" + payer_id
				+ ", receiver_id=" + receiver_id + ", pay_method=" + pay_method + ", order_type=" + order_type + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Orders other = (Orders) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}
