package action;

public class pwdjsonbean {	
	private String searchword;
	private String category_pwd;
	private String username;
	private String password;
	private String remarks;
	public pwdjsonbean(){}
	public pwdjsonbean(String s,String c,String u,String p,String r){		
		this.searchword = s;
		this.category_pwd = c;
		this.username = u;
		this.password = p;
		this.remarks = r;
	}	
	public String getSearchword() {
		return searchword;
	}
	public void setSearchword(String searchword) {
		this.searchword = searchword;
	}
	public String getCategory_pwd() {
		return category_pwd;
	}
	public void setCategory_pwd(String category_pwd) {
		this.category_pwd = category_pwd;
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
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
