package action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLConnection;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

import java.util.List;
import java.util.Map;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


















//import util.Aes;
import util.Encode_decode;
import util.MD5_utils;
import util.Time_utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import dao.*;




public class Getinfo extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Getinfo() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
		String getPathInfo = request.getPathInfo();
		Encode_decode de = new Encode_decode();
		String methodName = de.decode_data((getPathInfo.substring(1)));
		//String methodName = getPathInfo.substring(1);

		try{
			Method method = getClass().getDeclaredMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
			method.invoke(this, request, response);
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	
	
	
	@SuppressWarnings("unused")
	private void getshare(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ParseException {
		//System.out.println("getmsg!!!");
		response.setContentType("text/html;charset=utf-8");  
        response.setCharacterEncoding("UTF-8");  
        request.setCharacterEncoding("UTF-8");  
        
        Encode_decode decode = new Encode_decode();
        
        String key=request.getParameter("key");
        String usertimeid=request.getParameter("usertimeid");
        
		/////////////////
        
        loginDao select_login = new loginDao();
    	login login_flag = select_login.Select_info(usertimeid);
    	if(login_flag==null){
    		msg mm_2 = new msg();
        	mm_2.setMsg("error_no_usertimeid");
        	ObjectMapper mapper = new ObjectMapper();
			String re_error2_str = mapper.writeValueAsString(mm_2);
        	PrintWriter out_2 = response.getWriter();                                      
	        out_2.write(re_error2_str);  
	        out_2.flush();  
	        out_2.close();
    	}else{
    		//System.out.println("112233");
    		loginDao select_time = new loginDao();
    		login login_checktime = select_time.Select_info(usertimeid);
    		String latetime = login_checktime.getBegintime();
    		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String nowdate=sdf.format(new Date());
            int time_flag = Time_utils.timesBetween(latetime, nowdate);
            if(time_flag>=1800){
            	msg mm_3 = new msg();
            	mm_3.setMsg("error_over_time");
            	ObjectMapper mapper = new ObjectMapper();
    			String re_error3_str = mapper.writeValueAsString(mm_3);
            	PrintWriter out_3 = response.getWriter();                                      
    	        out_3.write(re_error3_str);  
    	        out_3.flush();  
    	        out_3.close();
            }else{            	
            
            	boolean fff = check_key.check_key_null(key);
            	
            	if(fff){            	         
                	msg mm_5 = new msg();
                	mm_5.setMsg("error_it_no_key");
                	ObjectMapper mapper = new ObjectMapper();
        			String re_error5_str = mapper.writeValueAsString(mm_5);
                	PrintWriter out_5 = response.getWriter();                                      
        	        out_5.write(re_error5_str);  
        	        out_5.flush();  
        	        out_5.close();
            	}else{    
            		shareDao share_check = new shareDao();
            	    share s = share_check.Select_share(key).get(0);	//可能会发生异常，待处理
            	    query_info re_q = new query_info();
            	    re_q.setLife(s.getUsername());
            	    re_q.setWork(s.getPassword());
                	re_q.setGame(s.getBeizhu());
                	ObjectMapper mapper = new ObjectMapper();
        			String re_error4_str = mapper.writeValueAsString(re_q);
                	PrintWriter out_4 = response.getWriter();                                      
        	        out_4.write(re_error4_str);  
        	        out_4.flush();  
        	        out_4.close();
            		}
            		
            		
            		
            	}
            }
    	}
        
        /////////////////
         
	
	
	
	@SuppressWarnings("unused")
	private synchronized void share_info(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ParseException {
		//System.out.println("getmsg!!!");
		response.setContentType("text/html;charset=utf-8");  
        response.setCharacterEncoding("UTF-8");  
        request.setCharacterEncoding("UTF-8");  
        
        Encode_decode decode = new Encode_decode(); 
        
        String user=request.getParameter("user");
        String pwd=request.getParameter("pwd");
        String key=request.getParameter("key");
        String beizhu=request.getParameter("beizhu");
        String usertimeid=request.getParameter("usertimeid");
        //System.out.println(user+" "+pwd+" "+key+" "+beizhu+" "+usertimeid);
        ///////////////////////////////////////////////////////////////////////////////
        
        loginDao select_login = new loginDao();
    	login login_flag = select_login.Select_info(usertimeid);
    	if(login_flag==null){
    		msg mm_2 = new msg();
        	mm_2.setMsg("error_no_usertimeid");
        	ObjectMapper mapper = new ObjectMapper();
			String re_error2_str = mapper.writeValueAsString(mm_2);
        	PrintWriter out_2 = response.getWriter();                                      
	        out_2.write(re_error2_str);  
	        out_2.flush();  
	        out_2.close();
    	}else{
    		//System.out.println("112233");
    		loginDao select_time = new loginDao();
    		login login_checktime = select_time.Select_info(usertimeid);
    		String latetime = login_checktime.getBegintime();
    		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String nowdate=sdf.format(new Date());
            int time_flag = Time_utils.timesBetween(latetime, nowdate);
            if(time_flag>=1800){
            	msg mm_3 = new msg();
            	mm_3.setMsg("error_over_time");
            	ObjectMapper mapper = new ObjectMapper();
    			String re_error3_str = mapper.writeValueAsString(mm_3);
            	PrintWriter out_3 = response.getWriter();                                      
    	        out_3.write(re_error3_str);  
    	        out_3.flush();  
    	        out_3.close();
            }else{
            	loginDao select_openid = new loginDao();
        		login login_check_openid = select_openid.Select_info(usertimeid); //openid = id_add
            	//pwdinfoDao pd_check_repeat = new pwdinfoDao();
            	String id_add = login_check_openid.getId();
         
                shareDao s_check = new shareDao();
                boolean fff = check_key.check_key_null(key);
            	if(fff){
            		
            		share s_add = new share(key,user,pwd,beizhu,id_add,nowdate);
                	s_check.Addshare(s_add);
                	
                	msg mm_5 = new msg();
                	mm_5.setMsg("success_response");
                	ObjectMapper mapper = new ObjectMapper();
        			String re_error5_str = mapper.writeValueAsString(mm_5);
                	PrintWriter out_5 = response.getWriter();                                      
        	        out_5.write(re_error5_str);  
        	        out_5.flush();  
        	        out_5.close();
            	}else{               		
                	msg mm_4 = new msg();
                	mm_4.setMsg("error_it_repeat");
                	ObjectMapper mapper = new ObjectMapper();
        			String re_error4_str = mapper.writeValueAsString(mm_4);
                	PrintWriter out_4 = response.getWriter();                                      
        	        out_4.write(re_error4_str);  
        	        out_4.flush();  
        	        out_4.close();
            		}
            	  
            	
            	
            }
    	}
        
        
        ///////////////////////////////////////////////////////////////////////////////
	}
	
	@SuppressWarnings("unused")
	private void update_info(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ParseException {		
		response.setContentType("text/html;charset=utf-8");  
        response.setCharacterEncoding("UTF-8");  
        request.setCharacterEncoding("UTF-8");  
        
        Encode_decode decode = new Encode_decode();
		
        String user=request.getParameter("user");
        String pwd=request.getParameter("pwd");
        String tips=request.getParameter("tips");
        String feilei=request.getParameter("feilei");
        String remarks=request.getParameter("remarks");
        String usertimeid=request.getParameter("usertimeid");
        String check_username=request.getParameter("check_username");
        String check_searchword=request.getParameter("check_searchword");
        
        ///////////////////////////////////////////////////////////////////////////////
        
        loginDao select_login = new loginDao();
    	login login_flag = select_login.Select_info(usertimeid);
    	if(login_flag==null){
    		msg mm_2 = new msg();
        	mm_2.setMsg("error_no_usertimeid");
        	ObjectMapper mapper = new ObjectMapper();
			String re_error2_str = mapper.writeValueAsString(mm_2);
        	PrintWriter out_2 = response.getWriter();                                      
	        out_2.write(re_error2_str);  
	        out_2.flush();  
	        out_2.close();
    	}else{
    		//System.out.println("112233");
    		loginDao select_time = new loginDao();
    		login login_checktime = select_time.Select_info(usertimeid);
    		String latetime = login_checktime.getBegintime();
    		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String nowdate=sdf.format(new Date());
            int time_flag = Time_utils.timesBetween(latetime, nowdate);
            if(time_flag>=1800){
            	msg mm_3 = new msg();
            	mm_3.setMsg("error_over_time");
            	ObjectMapper mapper = new ObjectMapper();
    			String re_error3_str = mapper.writeValueAsString(mm_3);
            	PrintWriter out_3 = response.getWriter();                                      
    	        out_3.write(re_error3_str);  
    	        out_3.flush();  
    	        out_3.close();
            }else{
            	loginDao select_openid = new loginDao();
        		login login_check_openid = select_openid.Select_info(usertimeid);
            	pwdinfoDao pd_check_repeat = new pwdinfoDao();
            	String id_add = login_check_openid.getId();
            /*	String searchword_add = decode.decode_data(tips);*/
            //	String username_add = decode.decode_data(user);
            	String feilei_add = decode.decode_data(feilei);               	
            //	String pwd_add = decode.decode_data(pwd);
            //	String remarks_add = decode.decode_data(remarks);

            	boolean fff = pd_check_repeat.select_repeat_all_pwd(id_add,tips,feilei_add,user,pwd,remarks).isEmpty();
            	if(fff){
            		
            		updatebean u = new updatebean(tips, feilei_add, user, pwd, remarks,id_add,check_searchword,check_username);
                	
                	pd_check_repeat.update_info(u);
                	
                	msg mm_5 = new msg();
                	mm_5.setMsg("success_response");
                	ObjectMapper mapper = new ObjectMapper();
        			String re_error5_str = mapper.writeValueAsString(mm_5);
                	PrintWriter out_5 = response.getWriter();                                      
        	        out_5.write(re_error5_str);  
        	        out_5.flush();  
        	        out_5.close();
            	}else{             
                	msg mm_4 = new msg();
                	mm_4.setMsg("error_it_repeat");
                	ObjectMapper mapper = new ObjectMapper();
        			String re_error4_str = mapper.writeValueAsString(mm_4);
                	PrintWriter out_4 = response.getWriter();                                      
        	        out_4.write(re_error4_str);  
        	        out_4.flush();  
        	        out_4.close();
            	}
            }
    	}
        
        ///////////////////////////////////////////////////////////////////////////////
        
        
	}
	
	@SuppressWarnings("unused")
	private void delete_info(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ParseException {
		response.setContentType("text/html;charset=utf-8");  
        response.setCharacterEncoding("UTF-8");  
        request.setCharacterEncoding("UTF-8");  
        
        Encode_decode decode = new Encode_decode();
        String usertimeid=request.getParameter("usertimeid");
        String user=request.getParameter("user");
        String tips=request.getParameter("tips");
        loginDao select_login = new loginDao();
    	login login_flag = select_login.Select_info(usertimeid);
    	if(login_flag==null){
    		msg mm_2 = new msg();
        	mm_2.setMsg("error_no_usertimeid");
        	ObjectMapper mapper = new ObjectMapper();
			String re_error2_str = mapper.writeValueAsString(mm_2);
        	PrintWriter out_2 = response.getWriter();                                      
	        out_2.write(re_error2_str);  
	        out_2.flush();  
	        out_2.close();
    	}else{   		
    		loginDao select_time = new loginDao();
    		login login_checktime = select_time.Select_info(usertimeid);
    		String latetime = login_checktime.getBegintime();
    		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String nowdate=sdf.format(new Date());
            int time_flag = Time_utils.timesBetween(latetime, nowdate);
            if(time_flag>=1800){
            	msg mm_3 = new msg();
            	mm_3.setMsg("error_over_time");
            	ObjectMapper mapper = new ObjectMapper();
    			String re_error3_str = mapper.writeValueAsString(mm_3);
            	PrintWriter out_3 = response.getWriter();                                      
    	        out_3.write(re_error3_str);  
    	        out_3.flush();  
    	        out_3.close();
            }else{
            	loginDao select_openid = new loginDao();
        		login login_check_openid = select_openid.Select_info(usertimeid);
            	pwdinfoDao pd_check_repeat = new pwdinfoDao();
            	String id_add = login_check_openid.getId();          

            	boolean fff = pd_check_repeat.select_repeat_pwd(id_add,tips,user).isEmpty();
            	if(fff){
            
                	
                	msg mm_5 = new msg();
                	mm_5.setMsg("error_it_null");
                	ObjectMapper mapper = new ObjectMapper();
        			String re_error5_str = mapper.writeValueAsString(mm_5);
                	PrintWriter out_5 = response.getWriter();                                      
        	        out_5.write(re_error5_str);  
        	        out_5.flush();  
        	        out_5.close();
            	}else{           
            		pwdinfoDao p_delete = new pwdinfoDao();
            		p_delete.delete_pwd(id_add, tips, user);
            		
            		
                	msg mm_4 = new msg();
                	mm_4.setMsg("success_delete");
                	ObjectMapper mapper = new ObjectMapper();
        			String re_error4_str = mapper.writeValueAsString(mm_4);
                	PrintWriter out_4 = response.getWriter();                                      
        	        out_4.write(re_error4_str);  
        	        out_4.flush();  
        	        out_4.close();
            	}
            }
    	}
         
	}
	
	@SuppressWarnings("unused")
	private void getall_index_onload(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ParseException {		
		response.setContentType("text/html;charset=utf-8");  
        response.setCharacterEncoding("UTF-8");  
        request.setCharacterEncoding("UTF-8");  
        String usertimeid=request.getParameter("usertimeid");
        
        loginDao s_login = new loginDao();
    	login l_flag = s_login.Select_info(usertimeid);
    	if(l_flag==null){
    		msg m_2 = new msg();
        	m_2.setMsg("error_info_index_onload");
        	ObjectMapper mapper = new ObjectMapper();
			String restr = mapper.writeValueAsString(m_2);
        	PrintWriter out_index2 = response.getWriter();                                      
	        out_index2.write(restr);  
	        out_index2.flush();  
	        out_index2.close();
    	}else{
    		//System.out.println("112233");
    		loginDao s_time = new loginDao();
    		login index_checktime = s_time.Select_info(usertimeid);
    		String latetime2 = index_checktime.getBegintime();
    		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String nowdate=sdf.format(new Date());
            int time_flag_index = Time_utils.timesBetween(latetime2, nowdate);
            if(time_flag_index>=1800){
            	msg mindex_3 = new msg();
            	mindex_3.setMsg("error_info_index_onload");
            	ObjectMapper mapper = new ObjectMapper();
    			String re_error3_str = mapper.writeValueAsString(mindex_3);
            	PrintWriter out_3 = response.getWriter();                                      
    	        out_3.write(re_error3_str);  
    	        out_3.flush();  
    	        out_3.close();
            }else{
            	String id_index = s_login.Select_info(usertimeid).getId();
            	pwdinfoDao p_index = new pwdinfoDao();
            	index_json i_j = new index_json();
            	i_j.setLife(p_index.select_all_index(id_index, "生活"));
            	i_j.setWork(p_index.select_all_index(id_index, "工作")); 
            	i_j.setGame(p_index.select_all_index(id_index, "娱乐")); 
            	i_j.setNav(p_index.select_all_index(id_index, "其他")); 
            	ObjectMapper mapper_index = new ObjectMapper();
    			String re_index_str = mapper_index.writeValueAsString(i_j);
            	PrintWriter out_index = response.getWriter();                                      
    	        out_index.write(re_index_str);  
    	        out_index.flush();  
    	        out_index.close();
            }
    	}
		
         
	}
	
	@SuppressWarnings("unused")  //登录的意义，获取最新的登录态凭证
	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, NoSuchAlgorithmException {
		//System.out.println("---------login---------");
		response.setContentType("text/html;charset=utf-8");  
        response.setCharacterEncoding("UTF-8");  
        request.setCharacterEncoding("UTF-8");  
        String code=request.getParameter("code");
        if(code==null)code="";
		//System.out.println("code_info:  "+code);
		usertimeid u_id = new usertimeid();
		u_id.setUsertimeid("error_info");
		
		try{
			
		String re = GET("https://api.weixin.qq.com/sns/jscode2session?appid=***你的appid***&secret=***你的app密码***&js_code="+code+"&grant_type=authorization_code");
		String code_re_utf = new String(re.getBytes("ISO-8859-1"),"utf-8");
		
		
		Gson gson = new Gson();
		codeinfo cinfo = gson.fromJson(code_re_utf, codeinfo.class);
		//System.out.println("session_key:"+cinfo.getSession_key()+"   "+"openid:"+cinfo.getOpenid());
		
		if(cinfo.getOpenid()!=null){
		userDao new_user = new userDao();	
		
		user checkuser = new_user.Select_id(cinfo.getOpenid());//检测数据库中是否已经存在此用户openid
		//System.out.println(checkuser);
		if(checkuser==null){
			user u = new user();
			u.setId(cinfo.getOpenid());
			new_user.Adduser(u);
		}
		
		loginDao new_login = new loginDao();
		new_login.Deletelogin(cinfo.getOpenid());
		MD5_utils new_usertimeid = new MD5_utils();
		String s_o = cinfo.getSession_key()+cinfo.getOpenid();
		String usertimeid_base64_md5 = new_usertimeid.EncoderByMd5(s_o);
		//System.out.println(usertimeid_base64_md5);
		u_id.setUsertimeid(usertimeid_base64_md5);
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date=sdf.format(new Date());
        //System.out.println(date);
        login log_user = new login();
        log_user.setBegintime(date);
        log_user.setId(cinfo.getOpenid());
        log_user.setUsertimeid(usertimeid_base64_md5);
        new_login.Addlogin(log_user);
		}
		
		
		
		
		}finally{
			ObjectMapper mapper = new ObjectMapper();
			String respon_str = mapper.writeValueAsString(u_id);
	        PrintWriter out = response.getWriter();                                      
	        out.write(respon_str);  
	        out.flush();  
	        out.close(); 
		}
	}
	
	@SuppressWarnings("unused")
	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ParseException {		
		response.setContentType("text/html;charset=utf-8");  
        response.setCharacterEncoding("UTF-8");  
        request.setCharacterEncoding("UTF-8");  
        
        
        Encode_decode decode = new Encode_decode();
		//String methodName = de.decode_data();
        String user=request.getParameter("user");
        String pwd=request.getParameter("pwd");
        String tips=request.getParameter("tips");
        String feilei=request.getParameter("feilei");
        String remarks=request.getParameter("remarks");
        String usertimeid=request.getParameter("usertimeid");
       
        if(user==null||pwd==null||tips==null||feilei==null||usertimeid==null){//这个判断没什么用
        	msg mm = new msg();
        	mm.setMsg("error:Is no parameter!");
        	ObjectMapper mapper = new ObjectMapper();
			String re_error_str = mapper.writeValueAsString(mm);
        	PrintWriter out = response.getWriter();                                      
	        out.write(re_error_str);  
	        out.flush();  
	        out.close(); 
        }else{
        	loginDao select_login = new loginDao();
        	login login_flag = select_login.Select_info(usertimeid);
        	if(login_flag==null){
        		msg mm_2 = new msg();
            	mm_2.setMsg("error_no_usertimeid");
            	ObjectMapper mapper = new ObjectMapper();
    			String re_error2_str = mapper.writeValueAsString(mm_2);
            	PrintWriter out_2 = response.getWriter();                                      
    	        out_2.write(re_error2_str);  
    	        out_2.flush();  
    	        out_2.close();
        	}else{
        		//System.out.println("112233");
        		loginDao select_time = new loginDao();
        		login login_checktime = select_time.Select_info(usertimeid);
        		String latetime = login_checktime.getBegintime();
        		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String nowdate=sdf.format(new Date());
                int time_flag = Time_utils.timesBetween(latetime, nowdate);
                if(time_flag>=1800){
                	msg mm_3 = new msg();
                	mm_3.setMsg("error_over_time");
                	ObjectMapper mapper = new ObjectMapper();
        			String re_error3_str = mapper.writeValueAsString(mm_3);
                	PrintWriter out_3 = response.getWriter();                                      
        	        out_3.write(re_error3_str);  
        	        out_3.flush();  
        	        out_3.close();
                }else{
                	loginDao select_openid = new loginDao();
            		login login_check_openid = select_openid.Select_info(usertimeid);
                	pwdinfoDao pd_check_repeat = new pwdinfoDao();
                	String id_add = login_check_openid.getId();
               
 
                	boolean fff = pd_check_repeat.select_repeat_pwd(id_add,tips,user).isEmpty();
                	if(fff){
                		String feilei_add = decode.decode_data(feilei);
                   
                    	pwdinfo add_pwdinfo = new pwdinfo(id_add, tips, feilei_add, user, pwd, remarks);
                    	pd_check_repeat.addpwdinfo(add_pwdinfo);
                    	
                    	msg mm_5 = new msg();
                    	mm_5.setMsg("success_response");
                    	ObjectMapper mapper = new ObjectMapper();
            			String re_error5_str = mapper.writeValueAsString(mm_5);
                    	PrintWriter out_5 = response.getWriter();                                      
            	        out_5.write(re_error5_str);  
            	        out_5.flush();  
            	        out_5.close();
                	}else{             
                    	msg mm_4 = new msg();
                    	mm_4.setMsg("error_it_repeat");
                    	ObjectMapper mapper = new ObjectMapper();
            			String re_error4_str = mapper.writeValueAsString(mm_4);
                    	PrintWriter out_4 = response.getWriter();                                      
            	        out_4.write(re_error4_str);  
            	        out_4.flush();  
            	        out_4.close();
                	}
                }
        	}
        }
	}
	
	
	
	@SuppressWarnings("unused")
	private void get_code(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		//System.out.println("get_code!!!");
		response.setContentType("text/html;charset=utf-8");  
        response.setCharacterEncoding("UTF-8");  
        request.setCharacterEncoding("UTF-8"); 
		String code=request.getParameter("code");
		if(code==null)code="";
		System.out.println("code_info:  "+code);
		
		String re = GET("https://api.weixin.qq.com/sns/jscode2session?appid=**你的appid**&secret=**你的app密码**&js_code="+code+"&grant_type=authorization_code");
		String str = new String(re.getBytes("ISO-8859-1"),"utf-8");
		System.out.println("openid_info:  "+str);
		 
        PrintWriter out = response.getWriter();                  
                  
        out.write(str);  
        out.flush();  
        out.close();  
	}

	
	public void init() throws ServletException {
		// Put your code here
	}
	public static String GET(String url) {  
	    String result = "";  
	    BufferedReader in = null;  
	    InputStream is = null;  
	    InputStreamReader isr = null;  
	    try {  
	        URL realUrl = new URL(url);  
	        URLConnection conn = realUrl.openConnection();  
	        
	        conn.setRequestProperty("Accept-Charset", "ISO-8859-1");
	        conn.setRequestProperty("contentType", "ISO-8859-1");
	       
	        conn.connect();  
	        
	        @SuppressWarnings("unused")
			Map<String, List<String>> map = conn.getHeaderFields();  
	        is = conn.getInputStream();  
	        
	        isr = new InputStreamReader(is,"ISO-8859-1");  
	        
	        in = new BufferedReader(isr);  
	        
	        String line;  
	        while ((line = in.readLine()) != null) {  
	            result += line;  
	        }  
	    } catch (Exception e) {  
	        e.printStackTrace();
	    } finally {  
	        try {  
	            if (in != null) {  
	                in.close();  
	            }  
	            if (is != null) {  
	                is.close();  
	            }  
	            if (isr != null) {  
	                isr.close();  
	            }  
	        } catch (Exception e2) {  
	            // 异常记录  
	        }  
	    }  
	    return result;  
	}

}
