package cn.jyb.entity;
/**
 * ��ά����Ͷ���
 * @author Eashion
 *
 */
public class QrOrder {
	
    private String qrOrderNo;//��ά�붩����

    private String name;//�ռ�������

    private String phone;//�ռ��˵绰

    private String qrAddress;//�ռ���ַ

    private Integer qrPayStatus;//֧��״̬

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