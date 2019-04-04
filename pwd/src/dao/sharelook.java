package dao;

public class sharelook {
	private String key;         //密令
	private String openid;		//查看人的openid
	public sharelook(){}
	public sharelook(String key, String openid) {
		this.key = key;
		this.openid = openid;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
}
