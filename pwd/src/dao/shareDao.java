package dao;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import util.MyBatisUtils;

public class shareDao {
	public  List<share> Select_share(String key) throws IOException {
		SqlSession session=MyBatisUtils.openSession();
		sharedaoImpl mapper=session.getMapper(sharedaoImpl.class);
		List<share> list=mapper.Select_share(key);
		session.close();
		return list;
	}
	public  List<share> Select_user_share(String openid) throws IOException{
		SqlSession session=MyBatisUtils.openSession();
		sharedaoImpl mapper=session.getMapper(sharedaoImpl.class);
		List<share> list=mapper.Select_user_share(openid);
		session.close();
		return list;		
	}
	public  void Addshare(share s) throws IOException {
		SqlSession session=MyBatisUtils.openSession();
		sharedaoImpl mapper=session.getMapper(sharedaoImpl.class);
		mapper.Addshare(s);
		session.commit();
		session.close();
	} 
	public  void Deleteshare(String key) throws IOException {
		SqlSession session=MyBatisUtils.openSession();
		sharedaoImpl mapper=session.getMapper(sharedaoImpl.class);
		mapper.Deleteshare(key);
		session.commit();
		session.close();
	}
}
