package dao;

import java.util.List;


public interface logindaoImpl {
	public List<login> SelectAll_login();
	public void Addlogin(login login);
	public void Deletelogin(String id);
	public login Select_info(String usertimeid);
	public int Queryonlineuser();
}
