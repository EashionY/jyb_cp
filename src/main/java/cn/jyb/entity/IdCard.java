package cn.jyb.entity;

import java.util.Date;
/**
 * 身份证实名认证
 * @author Eashion
 *
 */
public class IdCard {
	
	//身份证id
    private Integer idcardId;
    //用户id
    private Integer userId;
    //身份证号
    private String idcardNo;
    //身份证照片路径
    private String idcardPic;
    //真实姓名
    private String idcardRealname;
    //性别
    private String idcardSex;
    //住址
    private String idcardAddress;
    //实名认证状态
    private Integer realnameStatus;
    //实名认证时间
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