package com.xls.test;

import java.io.File;  

import org.apache.tools.ant.Project;  
import org.apache.tools.ant.taskdefs.Zip;  
import org.apache.tools.ant.types.FileSet;  
  
public class ZipCompressorByAnt {  
  
    private File zipFile;  
  
    public ZipCompressorByAnt(String pathName) {  
        zipFile = new File(pathName);  
    }  
      
    public void compress(String srcPathName) {  
        File srcdir = new File(srcPathName);  
        if (!srcdir.exists())  
            throw new RuntimeException(srcPathName + "�����ڣ�");  
        System.out.println("��ʼѹ���ļ�...");//��Դ�ļ�·��Ϊ��"+srcPathName
        Project prj = new Project();  
        Zip zip = new Zip();  
        zip.setProject(prj);  
        zip.setDestFile(zipFile);  
        FileSet fileSet = new FileSet();  
        fileSet.setProject(prj);  
        fileSet.setDir(srcdir);  
        //fileSet.setIncludes("**/*.java"); ������Щ�ļ����ļ��� eg:zip.setIncludes("*.java");  
        //fileSet.setExcludes(...); �ų���Щ�ļ����ļ���  
        zip.addFileset(fileSet);  
        zip.execute(); 
		System.out.println("pathName"+zipFile);
		System.out.println("srcPathName"+srcPathName);
		System.out.println("fileSet"+fileSet);
        System.out.println("�ļ�ѹ���ɹ���");
        }
//    public static void main(String args[]){
//    	//�����Խ�����ѹ�������ŵ�jenkins����������
//    	String sourceFilePath = "D:"+File.separator+"testReport"+File.separator+"2013-0802-12-10-35";
//    	String zipFilePath = "d:"+ File.separator + "jenkins"+ File.separator +"jobs"+ 
//    	File.separator +"tyydtest"+ File.separator +"workspace"+ File.separator;
//    	String zipfileName = "testResult.zip";
//    	ZipCompressorByAnt zc = new  ZipCompressorByAnt(zipFilePath+zipfileName);  
//    	zc.compress(sourceFilePath);  
//    	System.out.println("�ļ�ѹ���ɹ�����");
//    }
}