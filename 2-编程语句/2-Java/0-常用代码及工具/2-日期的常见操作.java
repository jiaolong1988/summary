package com.founder.encrypt;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * 常用日期操作
 * @author jiaolong
 * @date 2023-03-03 11:23:41
 */
public class DateOperate {
	
	//日期格式验证
	public static boolean isLegalDate(int length, String sDate,String format) {
        int legalLen = length;
        if ((sDate == null) || (sDate.length() != legalLen)) {
            return false;
        }
        DateFormat formatter = new SimpleDateFormat(format);
        try {
            Date date = formatter.parse(sDate);
            return sDate.equals(formatter.format(date));
        } catch (Exception e) {
            return false;
        }
    }
	
	public static void main(String[] args) {
		
		//String转换成Date类型	
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		String time = "2019-09-19";
		Date date = ft.parse(time);

		//Date转换成String类型
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String time = ft.format(date);


		Calendar dataCompare = Calendar.getInstance();
		//日期减法操作
		dataCompare.add(Calendar.DATE, -1);
		System.out.println(dataCompare.getTime());
		
		//日期的比较
		boolean x = dataCompare.getTime().compareTo(new Date())<0;
		System.out.println("dataCompare 是否小于当前日期 ："+x+"\n");
		
		//设置 毫秒值为0
		dataCompare.set(Calendar.MILLISECOND, 0);
			
			
		// Date 转 LocalDateTime
		Date startDate = new Date();
		LocalDateTime localDateTime1 = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();		
		// LocalDateTime 转 Date
		Date date = Date.from(localDateTime1.atZone(ZoneId.systemDefault()).toInstant());

		//日期格式化-线程安全
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String dateTimeStr = localDateTime1.format(dateTimeFormatter);
		System.out.println(dateTimeStr);
		//日期格式化-线程不安全
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("YYYYMMddHHmm");		
	    String transformDate=simpleDateFormat.format(new Date());
	    System.out.println("日期转换后："+transformDate+"\n");
		
		
		Calendar now = Calendar.getInstance();
		//获取日期信息
		System.out.println("年: " + now.get(Calendar.YEAR));
		System.out.println("月: " + (now.get(Calendar.MONTH) + 1) + "");
		System.out.println("日: " + now.get(Calendar.DAY_OF_MONTH));
		
		
		//设置起时间
		Calendar cal = Calendar.getInstance();
	   //cal.setTime(new Date());
		cal.set(2022, 3,4);
	    System.out.println("设置时间："+cal.getTime());
	    
	    //时间操作
	    cal.add(Calendar.YEAR, 1);//增加一年
	    //cd.add(Calendar.DATE, 1);//增加一天  
	    //cd.add(Calendar.DATE, -10);//减10天  
	    //cd.add(Calendar.MONTH, 1);//增加一个月   
	    
	    System.out.println("时间操作输出: "+cal.getTime());
			
	}

}
