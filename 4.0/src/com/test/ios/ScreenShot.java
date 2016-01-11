package com.test.ios;

import java.io.File;
import java.io.FileOutputStream;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;

public class ScreenShot {
   
   
    public void takeScreenShot(Activity Activity ,String path,String pname) throws Exception {
    	try {
    		 View view = Activity.getWindow().getDecorView();  
    	        view.setDrawingCacheEnabled(true);  
    	        view.buildDrawingCache();  
//    	        Bitmap b1 = view.getDrawingCache();
//    		view.setDrawingCacheEnabled(true);
//    		view.buildDrawingCache();
    		Bitmap bitmap = view.getDrawingCache();
    		System.out.println("bitmap" + bitmap);
    		int w = bitmap.getWidth();
    		int h = bitmap.getHeight();
    		Canvas canvas = new Canvas(bitmap);
    		FileOutputStream fos = null;
    		try {
    			File sddir = new File(path);
    			if (!sddir.exists()) {
    				sddir.mkdirs();
    			}

    			File file = new File(path + File.separator + pname + ".png");

    			fos = new FileOutputStream(file);
    			if (fos != null) {
    				bitmap.compress(Bitmap.CompressFormat.PNG, 90, fos);
    				fos.close();
    			}
    		} catch (Exception e) {
    			Log.e("TakeViewShot", e.getCause().toString());
    			e.printStackTrace();
    		}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("截图出错");
		}
		
	}
    
    
    
    
    

}
