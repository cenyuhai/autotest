package com.feisystems.automationtest.libary;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.util.Random;

import javax.swing.JOptionPane;

import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class TestUtils {
	private static Random RANDOM = new Random();
	public static Logger LOG = null;

	public static String getRandomNumber(int length) {
		long num = Math.abs(RANDOM.nextLong() % 10000000000L);
		String s = String.valueOf(num);
		for (int i = 0; i < length - s.length(); i++) {
			s = "0" + s;
		}
		return s;
	}
	
	public static <T> T[] getRangeArray(T[] source, int from, int to) {
		return Arrays.copyOfRange(source, from, to);
	}

	public static Logger getLogger(Class<?> cls) {
		Properties prop = new Properties();   
		InputStream in = Object.class.getResourceAsStream("/autotest.properties"); 
		try {
			prop.load(in);
		} catch (IOException e) {
			
		}   
		String logDir = prop.getProperty("LogDir").trim();
		String fileName = null;
		if(logDir != null && !logDir.equals("")) {
			fileName = logDir + "/" + cls.getSimpleName() + TestUtils.getCurrentTimeString() +  ".log";
		}else {
			String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
			fileName = basePath + "/log/" + cls.getSimpleName() + TestUtils.getCurrentTimeString() +  ".log";
		}
		CreateFileUtil.CreateFile(fileName);
		Logger logger = Logger.getLogger(cls.getName());  
		Layout layout = new PatternLayout("%m%n");
		Appender appender = null;
		try {
			appender = new FileAppender(layout, fileName);
		} catch (IOException e) {
			
		}  
		logger.addAppender(appender);
		LOG = logger;
		return logger; 
	}
	
	public static Logger getLogger() {
		return LOG;
	}

	private static String getCurrentTimeString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss");
		return dateFormat.format(new Date());
	}

	public static String getNowString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		String startDayString = dateFormat.format(new Date());
		return startDayString;
	}

	public static String getEndDayString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		calendar.add(Calendar.YEAR, 1);
		Date endDate = calendar.getTime();
		String endDayString = dateFormat.format(endDate);
		return endDayString;
	}
	
	public static String getFutureDay(int addDays) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_YEAR, addDays);
		Date endDate = calendar.getTime();
		String endDayString = dateFormat.format(endDate);
		return endDayString;
	}
	
	public static String getRandomDate() {
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		return format.format(randomDate("01/01/1960","01/01/2000"));
	}
	
	public static Date randomDate(final String beginDate, final String endDate) {  
        try {  
            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");  
            Date start = format.parse(beginDate); 
            Date end = format.parse(endDate); 
            if (start.getTime() >= end.getTime()) {  
                return null;  
            }  
            long date = random(start.getTime(), end.getTime());  
  
            return new Date(date);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
  
    private static long random(long begin, long end) {
        long rtn = begin + (long) (Math.random() * (end - begin));
        if (rtn == begin || rtn == end) {
            return random(begin, end);  
        }
        return rtn;  
    }
    
    public static void showMessage(String message) {
    	JOptionPane.showConfirmDialog(null, message); 
    }
    
    public static void main(String[] args) throws InterruptedException {
    	Thread.sleep(10000);
    }


}
