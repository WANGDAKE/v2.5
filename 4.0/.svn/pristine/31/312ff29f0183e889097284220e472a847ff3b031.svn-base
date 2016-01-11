package com.xls.test;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public class Configuration {
	static String devicesName="ee937665,cdesfsa";//主机挂载设备
	static String devicesSlave="abcde";//备机挂载设备
    public static String readConfigString(String name, String filename) {
        String result = "";
        try {
        	Locale loacle = Locale.getDefault();
			ResourceBundle rb = ResourceBundle.getBundle(filename,loacle);
            result = rb.getString(name);
        } catch (Exception e) {
        	System.out.println(filename +name+":" + e.getMessage());
        }
        return result;
    }
}
