package cn.jyb.entity;
/**
 * 微信openid与用户对照表
 * @author Eashion
 *
 */
public class WxOpenid {
    private Integer id;

    private String openid;

    private Integer userId;

    private String isLogin;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getIsLogin() {
        return isLogin;
    }

    public void setIsLogin(String isLogin) {
        this.isLogin = isLogin;
    }

	@Override
	public String toString() {
		return "WxOpenid [id=" + id + ", openid=" + openid + ", userId=" + userId + ", isLogin=" + isLogin + "]";
	}
    
}