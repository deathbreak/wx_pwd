package dao;

import org.apache.ibatis.annotations.Param;

public interface sharelookdaoImpl {
	public void Addsharelook(sharelook sl);
	public void Deletesharelook(String key);
	public int Querylookuser(String key);
	public sharelook Select_sharelook(@Param("key")String key,@Param("openid")String openid);
}
