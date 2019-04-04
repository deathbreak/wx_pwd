package dao;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;


import action.pwdjsonbean;
import util.MyBatisUtils;


public class pwdinfoDao {
	public  List<pwdinfo> select_repeat_pwd(String i,String s,String u) throws IOException {
		SqlSession session=MyBatisUtils.openSession();
		pwdinfodaoImpl mapper=session.getMapper(pwdinfodaoImpl.class);
		List<pwdinfo> list=mapper.select_repeat_pwd(i, s, u);
		session.close();
		return list;
	}
	public  List<pwdinfo> select_repeat_all_pwd(String i,String s,String c,String u,String p,String r) throws IOException {
		SqlSession session=MyBatisUtils.openSession();
		pwdinfodaoImpl mapper=session.getMapper(pwdinfodaoImpl.class);
		List<pwdinfo> list=mapper.select_repeat_all_pwd(i, s, c,u,p,r);
		session.close();
		return list;
	}
	public  List<pwdjsonbean> select_all_index(String i,String c) throws IOException {
		SqlSession session=MyBatisUtils.openSession();
		pwdinfodaoImpl mapper=session.getMapper(pwdinfodaoImpl.class);
		List<pwdjsonbean> list=mapper.select_all_index(i, c);
		session.close();
		return list;
	}
	public  void addpwdinfo(pwdinfo pwd_info) throws IOException {
		SqlSession session=MyBatisUtils.openSession();
		pwdinfodaoImpl mapper=session.getMapper(pwdinfodaoImpl.class);
		mapper.addpwdinfo(pwd_info);
		session.commit();
		session.close();
	} 
	public  void delete_pwd(String i,String s,String u) throws IOException {
		SqlSession session=MyBatisUtils.openSession();
		pwdinfodaoImpl mapper=session.getMapper(pwdinfodaoImpl.class);
		mapper.delete_pwd(i,s,u);
		session.commit();
		session.close();
	}  
	public  void update_info(updatebean u) throws IOException {
		SqlSession session=MyBatisUtils.openSession();
		pwdinfodaoImpl mapper=session.getMapper(pwdinfodaoImpl.class);
		mapper.update_info(u);
		session.commit();
		session.close();
	} 
}
