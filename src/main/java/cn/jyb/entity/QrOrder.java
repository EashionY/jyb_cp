package cn.jyb.entity;
/**
 * 二维码寄送订单
 * @author Eashion
 *
 */
public class QrOrder {
	
    private String qrOrderNo;//二维码订单号

    private String name;//收件人姓名

    private String phone;//收件人电话

    private String qrAddress;//收件地址

    private Integer qrPayStatus;//支付状态

    public String getQrOrderNo() {
        return qrOrderNo;
    }

    public void setQrOrderNo(String qrOrderNo) {
        this.qrOrderNo = qrOrderNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQrAddress() {
        return qrAddress;
    }

    public void setQrAddress(String qrAddress) {
        this.qrAddress = qrAddress;
    }

    public Integer getQrPayStatus() {
        return qrPayStatus;
    }

    public void setQrPayStatus(Integer qrPayStatus) {
        this.qrPayStatus = qrPayStatus;
    }

	@Override
	public String toString() {
		return "QrOrder [qrOrderNo=" + qrOrderNo + ", name=" + name + ", phone=" + phone + ", qrAddress=" + qrAddress
				+ ", qrPayStatus=" + qrPayStatus + "]";
	}
    
}