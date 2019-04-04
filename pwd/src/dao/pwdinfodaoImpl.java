package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import action.pwdjsonbean;




public interface pwdinfodaoImpl {
	public void addpwdinfo(pwdinfo pwd_info);
	public List<pwdinfo> select_repeat_pwd(@Param("id")String id,@Param("searchword")String searchword,@Param("username")String username);
	public List<pwdjsonbean> select_all_index(@Param("id")String id,@Param("category_pwd")String category_pwd);
	public void delete_pwd(@Param("id")String id,@Param("searchword")String searchword,@Param("username")String username);
    public void update_info(updatebean u);
    public List<pwdinfo> select_repeat_all_pwd(@Param("id")String id,@Param("searchword")String searchword,@Param("category_pwd")String category_pwd,@Param("username")String username,@Param("password")String password,@Param("remarks")String remarks);
}
