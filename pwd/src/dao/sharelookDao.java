package dao;

import java.io.IOException;


import org.apache.ibatis.session.SqlSession;

import util.MyBatisUtils;

public class sharelookDao {
	public  void Addsharelook(sharelook sl) throws IOException {
		SqlSession session=MyBatisUtils.openSession();
		sharelookdaoImpl mapper=session.getMapper(sharelookdaoImpl.class);
		mapper.Addsharelook(sl);
		session.commit();
		session.close();
	} 
	public  void Deletesharelook(String key) throws IOException {
		SqlSession session=MyBatisUtils.openSession();
		sharelookdaoImpl mapper=session.getMapper(sharelookdaoImpl.class);
		mapper.Deletesharelook(key);
		session.commit();
		session.close();
	}
	public  int Querylookuser(String key) throws IOException{
		SqlSession session=MyBatisUtils.openSession();
		sharelookdaoImpl mapper=session.getMapper(sharelookdaoImpl.class);
		int value=mapper.Querylookuser(key);
		session.close();
		return value;		
	}
	public  sharelook Select_sharelook(String k,String o) throws IOException {
		SqlSession session=MyBatisUtils.openSession();
		sharelookdaoImpl mapper=session.getMapper(sharelookdaoImpl.class);
		sharelook sss=mapper.Select_sharelook(k,o);
		session.close();
		return sss;
	}
}
