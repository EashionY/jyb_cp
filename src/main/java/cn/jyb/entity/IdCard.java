package cn.jyb.entity;

import java.util.Date;
/**
 * ���֤ʵ����֤
 * @author Eashion
 *
 */
public class IdCard {
	
	//���֤id
    private Integer idcardId;
    //�û�id
    private Integer userId;
    //���֤��
    private String idcardNo;
    //���֤��Ƭ·��
    private String idcardPic;
    //��ʵ����
    private String idcardRealname;
    //�Ա�
    private String idcardSex;
    //סַ
    private String idcardAddress;
    //ʵ����֤״̬
    private Integer realnameStatus;
    //ʵ����֤ʱ��
    private Date certTime;

    public Integer getIdcardId() {
        return idcardId;
    }

    public void setIdcardId(Integer idcardId) {
        this.idcardId = idcardId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getIdcardNo() {
        return idcardNo;
    }

    public void setIdcardNo(String idcardNo) {
        this.idcardNo = idcardNo;
    }

    public String getIdcardPic() {
        return idcardPic;
    }

    public void setIdcardPic(String idcardPic) {
        this.idcardPic = idcardPic;
    }

    public String getIdcardRealname() {
        return idcardRealname;
    }

    public void setIdcardRealname(String idcardRealname) {
        this.idcardRealname = idcardRealname;
    }

    public String getIdcardSex() {
        return idcardSex;
    }

    public void setIdcardSex(String idcardSex) {
        this.idcardSex = idcardSex;
    }

    public String getIdcardAddress() {
        return idcardAddress;
    }

    public void setIdcardAddress(String idcardAddress) {
        this.idcardAddress = idcardAddress;
    }

    public Integer getRealnameStatus() {
        return realnameStatus;
    }

    public void setRealnameStatus(Integer realnameStatus) {
        this.realnameStatus = realnameStatus;
    }

    public Date getCertTime() {
        return certTime;
    }

    public void setCertTime(Date certTime) {
        this.certTime = certTime;
    }
}