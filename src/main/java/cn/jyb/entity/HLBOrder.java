package cn.jyb.entity;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 货拉宝订单
 * @author Eashion
 *
 */
public class HLBOrder {
	
    private String hlbOrderNo;//订单号

    private String carType;//车型

    private String departure;//出发地

    private String destination;//目的地

    private BigDecimal fare;//费用

    private String payStatus;//支付状态

    private Integer orderType;//订单类型（1--即时/2--预约）

    private Date departTime;//出发时间

    private String contact;//联系人

    private String contactPhone;//联系电话

    private String remark;//备注

    private Integer publishId;//下单人id

    private Integer receiptId;//接单人id
    /*
     * (0--订单已提交，1--司机已接单，2--司机开始行程，3--乘客确认上传，4--司机确认到达，5--乘客确认到达，6--行程结束，
     *  -1--乘客取消，-2--司机取消)
     */
    private Integer orderStatus;//订单状态

    private Date publishTime;//下单时间

    private Date receiptTime;//接单时间

    private String carry;//额外要求：搬运

    private String backhaul;//额外要求：回程

    private String invoice;//额外要求：电子回单

    private Date finishTime;//订单完成时间

    private Integer driverEval;//司机评价
    
    private Integer passengerEval;//乘客评价

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