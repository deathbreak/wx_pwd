package dao;

import java.io.IOException;


import java.util.List;

import org.apache.ibatis.session.SqlSession;




import util.MyBatisUtils;

public class loginDao {
	public  List<login> SelectAll_login() throws IOException{
		SqlSession session=MyBatisUtils.openSession();
		logindaoImpl mapper=session.getMapper(logindaoImpl.class);
		List<login> list=mapper.SelectAll_login();
		session.close();
		return list;		
	}
	public  void Addlogin(login login) throws IOException {
		SqlSession session=MyBatisUtils.openSession();
		logindaoImpl mapper=session.getMapper(logindaoImpl.class);
		mapper.Addlogin(login);
		session.commit();
		session.close();
	} 
	public  login Select_info(String usertimeid) throws IOException {
		SqlSession session=MyBatisUtils.openSession();
		logindaoImpl mapper=session.getMapper(logindaoImpl.class);
		login login=mapper.Select_info(usertimeid);
		session.close();
		return login;
	}
	public  void Deletelogin(String id) throws IOException {
		SqlSession session=MyBatisUtils.openSession();
		logindaoImpl mapper=session.getMapper(logindaoImpl.class);
		mapper.Deletelogin(id);
		session.commit();
		session.close();
	}
	public  int Queryonlineuser() throws IOException{
		SqlSession session=MyBatisUtils.openSession();
		logindaoImpl mapper=session.getMapper(logindaoImpl.class);
		int value=mapper.Queryonlineuser();
		session.close();
		return value;		
	}
}
