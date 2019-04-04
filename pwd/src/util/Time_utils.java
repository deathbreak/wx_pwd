package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Time_utils {
	public static int timesBetween(String latedate,String nowdate) throws ParseException{
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();  
        cal.setTime(sdf.parse(latedate));  
        long time1 = cal.getTimeInMillis();               
        cal.setTime(sdf.parse(nowdate));  
        long time2 = cal.getTimeInMillis();       
        long between_=(time2-time1)/(1000);
          
       return Integer.parseInt(String.valueOf(between_));   
    }
}
