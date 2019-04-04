package dao;

public class share {
	private String key;         //密令
	private String username;	//账户	
	private String password;	//密码
	private String beizhu;		//备注信息
	private String openid;		//分享人的openid
	private String sharetime;	//密令注册时间
	public share(){}
	public share(String key, String username, String password, String beizhu,
			String openid, String sharetime) {
		//super();
		this.key = key;
		this.username = username;
		this.password = password;
		this.beizhu = beizhu;
		this.openid = openid;
		this.sharetime = sharetime;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getBeizhu() {
		return beizhu;
	}
	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getSharetime() {
		return sharetime;
	}
	public void setSharetime(String sharetime) {
		this.sharetime = sharetime;
	}
	
}
