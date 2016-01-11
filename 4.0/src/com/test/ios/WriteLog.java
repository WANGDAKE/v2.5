package com.test.ios;

import android.annotation.SuppressLint;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;


@SuppressLint("SdCardPath")
public class WriteLog {



	    

	  
	    /**

	     * 记录日志内容

	     * */

	    public static void writeComLog(String str) {

	        Calendar c = GregorianCalendar.getInstance();

	        String filename = "/sdcard/Testathrun/logs/appium.log";
	        try {

	            BufferedWriter bufOut;

	            File f = new File(filename);

	            if(f.exists()==true){

	                bufOut = new BufferedWriter(new FileWriter(f,true));

	            }else {

	                bufOut = new BufferedWriter(new FileWriter(f));

	            }

	            String datetime = "" + c.get(c.YEAR) + "-"

	                               + fillZero(1+c.get(c.MONTH)+"", 2) + "-"

	                               + fillZero(""+c.get(c.DAY_OF_MONTH), 2) + " "

	                               + fillZero(""+c.get(c.HOUR), 2) + ":"

	                               + fillZero(""+c.get(c.MINUTE), 2) + ":"

	                               + fillZero(""+c.get(c.SECOND), 2);

	            bufOut.write("----------------------------------------------------------------------\n");

	            bufOut.write("\t" + datetime + "\n");

	            bufOut.write(str + "\r\n");

	            bufOut.write("----------------------------------------------------------------------\n");

	            bufOut.close();

	         

	        } catch(Exception e) {

	            System.out.println("Error");

	        }

	    } 

	     

	      /*右对齐左补零*/

	    static String fillZero(String str, int len) {

	        int tmp = str.length();

	        int t;

	        String str1 = str;

	        if(tmp >= len)

	          return str1;

	        t = len - tmp;

	        for(int i = 0; i < t; i++ )

	          str1 = "0" + str1;

	        return str1;

	     }



}
