package cn.jyb.entity;

import java.io.Serializable;
import java.sql.Timestamp;
/**
 * ֧������
 * @author Eashion
 *
 */
public class Orders implements Serializable {

	private static final long serialVersionUID = -2296521224189308071L;

	//����id
	private int id;
	//������
	private String out_trade_no;
	//����
	private String subject;
	//������������
	private String body;
	//�ܽ��
	private String total_amount;
	//�տ�֧�����û�id
	private String seller_id;
	//����ʱ��
	private Timestamp creatime;
	//����ʱ��
	private Timestamp finishtime;
	//����״̬
	private String trade_status;
	//֧�����û�id
	private int payer_id;
	//�տ����û�id
	private int receiver_id;
	//���ʽ
	private String pay_method;
	
	public Orders(){}
	
	public Orders(String out_trade_no, String subject, String body, String total_amount) {
		super();
		this.out_trade_no = out_trade_no;
		this.subject = subject;
		this.body = body;
		this.total_amount = total_amount;
	}

	public Orders(String out_trade_no, String subject, String body, String total_amount, Timestamp creatime,
			Timestamp finishtime, String trade_status) {
		super();
		this.out_trade_no = out_trade_no;
		this.subject = subject;
		this.body = body;
		this.total_amount = total_amount;
		this.creatime = creatime;
		this.finishtime = finishtime;
		this.trade_status = trade_status;
	}

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

	@Override
	public String toString() {
		return "Orders [id=" + id + ", out_trade_no=" + out_trade_no + ", subject=" + subject + ", body=" + body
				+ ", total_amount=" + total_amount + ", seller_id=" + seller_id + ", creatime=" + creatime
				+ ", finishtime=" + finishtime + ", trade_status=" + trade_status + ", payer_id=" + payer_id
				+ ", receiver_id=" + receiver_id + ", pay_method=" + pay_method + "]";
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