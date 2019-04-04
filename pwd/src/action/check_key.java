package action;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import util.Time_utils;
import dao.shareDao;
//import dao.sharelookDao;

public class check_key {
			public static synchronized boolean check_key_null(String key) throws IOException, ParseException{
				
				
				shareDao s_dao = new shareDao();   		
    			//sharelookDao sl_dao = new sharelookDao();
            	
            

            	boolean fff = s_dao.Select_share(key).isEmpty();
				
            	if(fff){
            		return true;
            	}else{
            			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            			String nowdate=sdf.format(new Date());
            			String check_time = s_dao.Select_share(key).get(0).getSharetime();
            			int time_flag = Time_utils.timesBetween(check_time, nowdate);
            			if(time_flag<600){
            					return false;
            			}else{
            					s_dao.Deleteshare(key);
            					//sl_dao.Deletesharelook(key);
            					return true;
            			}
            	}
	}
}
