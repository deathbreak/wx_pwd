package dao;

import java.io.IOException;

import org.apache.ibatis.session.SqlSession;


import util.MyBatisUtils;

public class userDao {
	public  void Adduser(user user) throws IOException {
		SqlSession session=MyBatisUtils.openSession();
		userdaoImpl mapper=session.getMapper(userdaoImpl.class);
		mapper.Adduser(user);
		session.commit();
		session.close();
	} 
	public  user Select_id(String id) throws IOException {
		SqlSession session=MyBatisUtils.openSession();
		userdaoImpl mapper=session.getMapper(userdaoImpl.class);
		user user=mapper.Select_id(id);
		session.close();
		return user;
	}
}
