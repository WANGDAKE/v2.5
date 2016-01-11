package com.xls.test;

import java.io.BufferedInputStream; 
import java.io.BufferedOutputStream; 
import java.io.File; 
import java.io.FileInputStream; 
import java.io.FileOutputStream; 
import java.io.IOException; 

 
import javax.swing.JTextArea; 
 
public class CopyFolder { 
 
    //允许复制的文件类型  
    public static String[] filterFile = {".java",".xml",".xls",".properties",".sql",".jupiter",".wsdl",".txt",".png"}; 
    private  long total = 0l; 

    /** 
     * 
     * @param folder
     * @param filterFile 
     * @param status 
     * @throws Exception 
     */ 
    public void copyFolder(File srcFolder,File destFolder,String[] filterFile, JTextArea status) throws Exception 
    { 
        File[] files = srcFolder.listFiles(); 
        for (File file : files) 
        { 
            if(file.isFile()) 
            { 
                String pathname = destFolder+File.separator+file.getName(); 
                 
                for (String suff : filterFile) 
                { 
                     if(pathname.endsWith(suff)) 
                     { 
                         File dest = new File(pathname); 
                         File destPar =  dest.getParentFile(); 
                         destPar.mkdirs(); 
                         if(!dest.exists()) 
                         { 
                             dest.createNewFile(); 
                         } 
                         copyFile(file, dest,status); 
                     } 
                } 
            }else{ 
                 copyFolder(file, destFolder, filterFile,status); 
            } 
        } 
    } 
    /***
     * copy file
     * 
     * @param src
     * @param dest
     * @param status 
     * @throws IOException
     */ 
    public void copyFile(File src, File dest, JTextArea status) throws Exception { 
        BufferedInputStream reader = null; 
        BufferedOutputStream writer = null; 
        try { 
            reader = new BufferedInputStream(new FileInputStream(src)); 
            writer = new BufferedOutputStream(new FileOutputStream(dest));  
            byte[] buff = new byte[reader.available()]; 
            while ((reader.read(buff)) != -1) { 
                writer.write(buff); 
            } 
            total++; 
            String temp = "\ncopy:\n"+src+"\tsize:"+src.length()+"\nto:\n"+dest+"\tsize:"+dest.length()+"\n complate\n totoal:"+total; 
            System.out.println(temp); 
            //status.append(temp);  
        } catch (Exception e) { 
            throw e; 
        } finally { 
            writer.flush(); 
            writer.close(); 
            reader.close(); 
             
        } 
    } 
} 
