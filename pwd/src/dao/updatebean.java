package dao;

public class updatebean {	
	private String searchword;
	private String category_pwd;
	private String username;
	private String password;
	private String remarks;
	private String id;
	private String check_searchword;
	private String check_username;
	public updatebean(){}
	public updatebean(String searchword, String category_pwd,
			String username, String password, String remarks,String id, 
			String check_searchword, String check_username) {		
		this.id = id;
		this.searchword = searchword;
		this.category_pwd = category_pwd;
		this.username = username;
		this.password = password;
		this.remarks = remarks;
		this.check_searchword = check_searchword;
		this.check_username = check_username;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getCheck_searchword() {
		return check_searchword;
	}
	public void setCheck_searchword(String check_searchword) {
		this.check_searchword = check_searchword;
	}
	public String getCheck_username() {
		return check_username;
	}
	public void setCheck_username(String check_username) {
		this.check_username = check_username;
	}
}
