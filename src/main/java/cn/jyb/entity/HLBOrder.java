package cn.jyb.entity;

import java.math.BigDecimal;
import java.util.Date;
/**
 * ����������
 * @author Eashion
 *
 */
public class HLBOrder {
	
    private String hlbOrderNo;//������

    private String carType;//����

    private String departure;//������

    private String destination;//Ŀ�ĵ�

    private BigDecimal fare;//����

    private String payStatus;//֧��״̬

    private Integer orderType;//�������ͣ�1--��ʱ/2--ԤԼ��

    private Date departTime;//����ʱ��

    private String contact;//��ϵ��

    private String contactPhone;//��ϵ�绰

    private String remark;//��ע

    private Integer publishId;//�µ���id

    private Integer receiptId;//�ӵ���id
    /*
     * (0--�������ύ��1--˾���ѽӵ���2--˾����ʼ�г̣�3--�˿�ȷ���ϴ���4--˾��ȷ�ϵ��5--�˿�ȷ�ϵ��6--�г̽�����
     *  -1--�˿�ȡ����-2--˾��ȡ��)
     */
    private Integer orderStatus;//����״̬

    private Date publishTime;//�µ�ʱ��

    private Date receiptTime;//�ӵ�ʱ��

    private String carry;//����Ҫ�󣺰���

    private String backhaul;//����Ҫ�󣺻س�

    private String invoice;//����Ҫ�󣺵��ӻص�

    private Date finishTime;//�������ʱ��

    private Integer driverEval;//˾������
    
    private Integer passengerEval;//�˿�����

    public String getHlbOrderNo() {
        return hlbOrderNo;
    }

    public void setHlbOrderNo(String hlbOrderNo) {
        this.hlbOrderNo = hlbOrderNo;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public BigDecimal getFare() {
        return fare;
    }

    public void setFare(BigDecimal fare) {
        this.fare = fare;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Date getDepartTime() {
        return departTime;
    }

    public void setDepartTime(Date departTime) {
        this.departTime = departTime;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getPublishId() {
        return publishId;
    }

    public void setPublishId(Integer publishId) {
        this.publishId = publishId;
    }

    public Integer getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(Integer receiptId) {
        this.receiptId = receiptId;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public Date getReceiptTime() {
        return receiptTime;
    }

    public void setReceiptTime(Date receiptTime) {
        this.receiptTime = receiptTime;
    }

    public String getCarry() {
        return carry;
    }

    public void setCarry(String carry) {
        this.carry = carry;
    }

    public String getBackhaul() {
        return backhaul;
    }

    public void setBackhaul(String backhaul) {
        this.backhaul = backhaul;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

	public Integer getDriverEval() {
		return driverEval;
	}

	public void setDriverEval(Integer driverEval) {
		this.driverEval = driverEval;
	}

	public Integer getPassengerEval() {
		return passengerEval;
	}

	public void setPassengerEval(Integer passengerEval) {
		this.passengerEval = passengerEval;
	}

	@Override
	public String toString() {
		return "HLBOrder [hlbOrderNo=" + hlbOrderNo + ", carType=" + carType + ", departure=" + departure
				+ ", destination=" + destination + ", fare=" + fare + ", payStatus=" + payStatus + ", orderType="
				+ orderType + ", departTime=" + departTime + ", contact=" + contact + ", contactPhone=" + contactPhone
				+ ", remark=" + remark + ", publishId=" + publishId + ", receiptId=" + receiptId + ", orderStatus="
				+ orderStatus + ", publishTime=" + publishTime + ", receiptTime=" + receiptTime + ", carry=" + carry
				+ ", backhaul=" + backhaul + ", invoice=" + invoice + ", finishTime=" + finishTime + ", driverEval="
				+ driverEval + ", passengerEval=" + passengerEval + "]";
	}
    
}