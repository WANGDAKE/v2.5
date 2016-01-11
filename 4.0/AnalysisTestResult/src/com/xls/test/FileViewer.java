package com.xls.test;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import org.apache.tools.ant.taskdefs.Dirname;

import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WritableHyperlink;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

/**
* 读取目录及子目录下指定文件名的路径 并放到一个数组里面返回遍历
* @author huangpanfeng
*
*/
public class FileViewer {
	//系统当前路径
//	static String systemPath = System.getProperty("user.dir");
	
//	D:\Jenkins\jobs\4.0\workspace\testcase
	
	static String systemPath = "D:" + File.separator+"Jenkins"+File.separator+"jobs"+File.separator+ "4.0" + File.separator+"workspace"+File.separator+"AnalysisTestResult";
//	static String systemPath = "F:" + File.separator + "jenkins" + File.separator + "jobs" +
//			File.separator + "ee937665" +File.separator + "workspace" + File.separator 
//			+ "AnalysisTestResult";
//	static String systemPath = "D:\\Jenkins\\jobs\\4.0\\workspace\\testcase";
	//当前任务工程目录
	static String jobPath = systemPath.substring(0, systemPath.lastIndexOf(File.separator));
	//在当前任务目录下tempResult文件夹，保存测试详细结果。
	static String tempResultPath = jobPath + File.separator+ "tempResult" + File.separator;
	//在当前任务目录下的testReport文件夹，用于保存解析后的xml文件
	static String testReportPath = jobPath + File.separator+ "testReport" + File.separator;
	//当前任务名 截取的是jobs文件下的子文件夹名称
	static String jobName =(jobPath.split("jobs")[1]).split("\\\\")[1];;
//	static String jobName = jobPath.substring(jobPath.lastIndexOf(File.separator)+1,jobPath.length());
	 
//	static String jobName = jobPath.substring(jobPath.lastIndexOf(),);temp
//	static String[] jobName1 = jobPath.split("/",0);
	//集群任务名
	static String jqjobName = jobPath.substring(21,jobPath.length()-1);
	//测试报告excel文件名
	static String excelname =  File.separator+"testReport.xls";
	//测试结果归档目录
	static String backupDir = "D:" + File.separator + "testResult" + File.separator + jobName;
	//tsetCasemenu路径
	static String testCaseMenu;
	//结果目录
	static String result;
	//用例路径
	static String testcase = tempResultPath + jobName+".xls";
	static SimpleDateFormat filesd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	static int failcases = 0;
	static int tests = 0;
	static String fileName;
	

	@SuppressWarnings("static-access")
	public static void main(String[] args){
		
		
		
		System.out.println("systemPath----"+systemPath);
		System.out.println("jobPath----"+jobPath);
		System.out.println("tempResultPath----"+tempResultPath);
		System.out.println("jobName----"+jobName);
		System.out.println("-----"+jobPath.lastIndexOf(File.separator));
		System.out.println("excelname----"+excelname);
		System.out.println("backupDir----"+backupDir);
		System.out.println("testcase----"+testcase);
		
		String [] device = Configuration.devicesName.split(",");
		String [] slave = Configuration.devicesSlave.split(",");
		fileName = jobName + ".xls";
		
		for(int n=0;n<device.length;n++){
    		if(jobName.equals(device[n])){
    			fileName = jobName + ".xls";
    			System.out.println("fileName---"+fileName);
    			jobName = jobName;
    			backupDir = "D:" + File.separator + "testResult" + File.separator + jobName;
    			testcase = tempResultPath + jobName+".xls";
    			System.out.println("testcase---"+testcase);
    			break;
    		}
    	}
		for(int m=0;m<slave.length;m++){
			System.out.println(jqjobName+"--"+slave[m]);
			if(jqjobName.equals(slave[m]))
    		{
    			fileName = jqjobName + ".xls";
    			System.out.println("fileName---"+fileName);
    			jobName = jqjobName;
    			backupDir = "D:" + File.separator + "testResult" + File.separator + jobName;
    			System.out.println("backupDir"+backupDir);
    			testcase = tempResultPath + jobName+ ".xls";
    			System.out.println("testcase---"+testcase);
    			break;
    		}
		}
    			
		List<String> tempList = getListFiles(tempResultPath,"xls",true);
		
		JSONObject testResults = new JSONObject();
		try {
			testResults = buildMultifileTestResult(tempList);
			System.out.println("testResults"+testResults);
			System.out.println("tempList"+tempList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*将测试结果转换为jenkins的JUnit Attachments Plugin所能读取的xml文件
		 * 并放入工作目录下的testReport文件夹下
		 */
		WriteXmlFile.writeXmlFile(testReportPath,jobName,getTestResults(testResults));
		//读取测试结果excel文件
		ReadExcelFile readExcelFile = new ReadExcelFile(jobPath,excelname);
		try {
			System.out.println(jobPath+excelname);
			Workbook workbook1= Workbook.getWorkbook(new File(jobPath+excelname));
			System.out.println(readExcelFile.getFilePath());
			WritableWorkbook wbe1 = Workbook.createWorkbook(new File(readExcelFile.getFilePath()),workbook1);
			WritableSheet sheet1 = wbe1.getSheet(0);
			WritableSheet testCaseResult = wbe1.getSheet(1);
			WritableSheet failCaseResult = wbe1.getSheet(2);
			//分析测试结果数据，整合到工作目录下的testReport.xls文件里，形成测试报告
			//更新测试报告概要
			System.out.println("------开始更新测试报告概要------");
			AnalysisTestResult.updateReport(sheet1);
			System.out.println("------测试报告概要更新完毕------");
	
			//更新测试用例执行结果
			System.out.println("------开始更新测试用例执行结果------");
			AnalysisTestResult.updateTestCaseResult(testCaseResult,tempList);
			System.out.println("------测试用例执行结果更新完毕------");
			
			//更新失败用例原因
    		System.out.println("------开始更新用例失败原因------");
    		AnalysisTestResult.updateFailCaseResult(failCaseResult,tempList);
    		System.out.println("------用例失败原因记录完毕------");
			
			wbe1.write();//将修改保存到workbook --》一定要保存
			wbe1.close();//关闭workbook，释放内存 ---》一定要释放内存
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
		//将截屏图片链接添加到测试结果excel里
		excelhyperlink(tempList);
		/************天翼阅读项目用到入库*********/
//		//用例入库
/***		System.out.println("用例入库");
		ExcelOperate excel=new ExcelOperate();
		try {
			excel.excelOprate(testCaseMenu, testcase);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			System.out.println("用例入库失败");
			e1.printStackTrace();
		}
		//提交BUG
		System.out.println("调用提交BUG");
		submitBug sub = new submitBug();
		try {
			sub.submitbug(testcase,result);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}***/
		/*************************/
//		//测试结果tempResult文件夹
		File testResultFolder = new File(tempResultPath);
		//测试报告testReport.xls
		File testReportFile = new File(jobPath +File.separator +"testReport.xls");
		//测试日志logs文件夹
		File testLogFolder = new File(tempResultPath +File.separator+"logs"+ File.separator);
		
		File testResultBackupFolder = new File(backupDir  + File.separator
				+ "testResult" + File.separator);
		File testReportBackupFile = new File(backupDir  + File.separator 
				+"testReport.xls");
		File testLogBackupFolder = new File(backupDir + File.separator
				+ "logs" + File.separator);
		System.out.println("tempResultPath"+tempResultPath);
		System.out.println("testResultFolder"+testResultFolder);
		System.out.println("testReportFile"+testReportFile);
		System.out.println("testLogFolder"+testLogFolder);
		System.out.println("testResultBackupFolder"+testResultBackupFolder);
		System.out.println("testReportBackupFile"+testReportBackupFile);
		System.out.println("testLogBackupFolder"+testLogBackupFolder);
		
		
		
		
		copyfolder(testResultFolder, testResultBackupFolder);
		copyfile(testReportFile, testReportBackupFile);
		copyfolder(testLogFolder, testLogBackupFolder);
		//将测试结果打包压缩，并放到jenkins工作区间里
    	String zipfilePath = jobPath +File.separator+"testResult.zip";
    	ZipCompressorByAnt zc = new  ZipCompressorByAnt(zipfilePath);  
    	zc.compress(tempResultPath); 
    	
		
		System.out.println("执行结束！！");
	}
	@SuppressWarnings("static-access")
	public static void copyfolder(File src, File dest){
		System.out.println("--"+src+dest);
		try{
			CopyFolder cf = new CopyFolder();
			if(!dest.exists() && !dest.isDirectory()){
				dest.mkdir();  
			}
			if(src.equals(null)){
				System.err.println(src + "文件未找到！！");
			}else{
				cf.copyFolder(src, dest, cf.filterFile, null);
			}			
		}catch (Exception e){
			e.printStackTrace();
		}	
	}
	
	public static void copyfile(File src, File dest){
		try{
			CopyFolder cf = new CopyFolder();
			System.out.println("--"+src+"--"+dest);
			if(src.equals(null)){
				System.err.println(src + "文件未找到！！");
			}else{
				cf.copyFile(src, dest, null);
				
			}
		}catch (Exception e){
			e.printStackTrace();
		}	
	}
	public static String getTestResults(JSONObject jSONObject){
		XMLSerializer xMLSerializer = new XMLSerializer();
		xMLSerializer.setObjectName("testsuites");
		xMLSerializer.setElementName("testcase");
	    String xml = xMLSerializer.write(jSONObject);
	    return xml;
	}
	@SuppressWarnings("unchecked")
	public static JSONObject buildMultifileTestResult(List<String> list){
		JSONObject testcasejson = new JSONObject();
		JSONObject  tempjson = new JSONObject();
		JSONObject testsuites = new JSONObject();
		DecimalFormat df = new DecimalFormat("########0.00"); 
		List<String> arrayList = list;
		double time = 0;
		System.out.println("arrayList"+arrayList);
		if(arrayList.isEmpty()){
			
			System.out.println("没有符合要求的文件");
		}
		else{
		    String message = "";
		    message += "符合要求的文件数："+ (arrayList.size()-1) + "\r\n";
		    System.out.println(message);
		    
		    
	//		String fileNameWithOutExtName = "";
		    for (Iterator<String> i = arrayList.iterator(); i.hasNext();){
		    	String temp = i.next();
		    	System.out.println("temp"+temp);
		    	if(temp.contains("testcasemenu")){
		    		continue;
		    	}
		    	System.out.println(temp.substring(0, temp.length()- fileName.length()));
		    	String filedir = temp.substring(0, temp.length()- fileName.length());
		    	System.out.println("fileName"+fileName);
		    	System.out.println("filedir"+filedir);
	//	    	fileNameWithOutExtName = (String) temp.subSequence(8,temp.length()-3);
			    ReadExcelFile readExcelFile = new ReadExcelFile(filedir,fileName);
//			    System.out.println(readExcelFile.getCaseResultMap());
			    
			    HashMap<String, HashMap<String, ?>> tempMap = readExcelFile.getCaseResultMap();
			    HashMap<String,Integer> countMap = (HashMap<String, Integer>) tempMap.get("count");
			    
			    List<String> tempList = readExcelFile.getCasesList();
			    System.out.println("tempList"+tempList);
			    
			    setFailcases(getFailcases() + countMap.get("failCount"));
				setTests(tests +  countMap.get("testsCount"));
				time += countMap.get("casesRunTime");
				
				tempMap.remove("count");
				Iterator<String> j = tempList.iterator();
				while(j.hasNext()){
					String tempCaseNumb =  j.next();
					HashMap<String, String> tempResultMap = (HashMap<String, String>) tempMap.get(tempCaseNumb);
					testcasejson.element("@classname", tempResultMap.get("@classname"));
					testcasejson.element("@name", tempResultMap.get("@name"));
					double tempint = Integer.parseInt(tempResultMap.get("@time"));
					double timedouble = (double) tempint/1000;
					String tempstr = df.format(timedouble);
					testcasejson.element("@time", tempstr);
					if(tempResultMap.containsKey("failure")){
						testcasejson.element("failure", tempResultMap.get("failure"));
					}
					tempjson.accumulate("testsuite", testcasejson);
					testcasejson.clear();
					
				}
		    }   
		}
		double timedouble = (double) time/1000;
		String tempstr = df.format(timedouble);
		testsuites.accumulate("testsuite", tempjson.get("testsuite"));
		testsuites.element("@errors", 0);
		testsuites.element("failures",getFailcases());
		testsuites.element("@name", "自动化测试");
		testsuites.element("@tests", getTests());
		testsuites.element("@skipped", 0);
		testsuites.element("@time", tempstr);

		return testsuites;
		
	}
	@SuppressWarnings("null")
	public static JSONObject buildSingleTestFileResult(String filedir,List<String> list) throws Exception{
		
		List<String> arrayList = list;
		JSONObject testsuites = null;
		if(arrayList.isEmpty()){
			System.err.println("没有符合要求的文件");
		}
		else{
		    String message = "";
		    message += "符合要求的文件数：" + arrayList.size() + "\r\n";
		    System.out.println(message);
		    
		    
			String fileName = "";
	//		String fileNameWithOutExtName = "";
		    for (Iterator<String> i = arrayList.iterator(); i.hasNext();){
		    	String temp = i.next();
		    	fileName = (String) temp.subSequence(8,temp.length());
	//	    	fileNameWithOutExtName = (String) temp.subSequence(8,temp.length()-3);
//			    System.out.println(filedir+fileName);
			    ReadExcelFile readExcelFile = new ReadExcelFile(filedir,fileName);
//			    System.out.println(readExcelFile.getCaseResultJSON());
			    JSONObject tempJson =  readExcelFile.getCaseResultJSON();
			    setFailcases (getFailcases() + (Integer) readExcelFile.getCaseResultJSON().get("@failures"));
				setTests(getTests() + (Integer) readExcelFile.getCaseResultJSON().get("@tests"));

				tempJson.discard("@failures");
				tempJson.discard("@tests");
				testsuites.accumulate("testsuite", tempJson.get("testsuite"));
			}

		    testsuites.element("@errors", 0);
		    testsuites.element("@failures", getFailcases());
		    testsuites.element("@name", "ecptest");
		    testsuites.element("@tests", getTests());
		    testsuites.element("@skipped", 0);
			SimpleDateFormat filesd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String now = filesd.format(new Date());
			testsuites.element("@timestamp", now);

		}
		return testsuites;
	}
	
	/**
	*
	* @param path 文件路径
	* @param suffix 后缀名
	* @param isdepth 是否遍历子目录
	* @return
	*/
	public static List<String> getListFiles(String path, String suffix, boolean isdepth)
	{
		System.out.println(path+"---"+suffix+"--"+isdepth);
	   File file = new File(path);
	   return listFile(file ,suffix, isdepth);
	}
	static List<String> fileList = new ArrayList<String>();
	public static List<String> listFile(File f, String suffix, boolean isdepth){
		System.out.println(f+"--"+suffix+"--"+isdepth);
	   
		//是目录，同时需要遍历子目录
	   if (f.isDirectory() && isdepth == true)
	   {
	    File[] t = f.listFiles();
	    for (int i = 0; i < t.length; i++)
	    {
	     listFile(t[i], suffix, isdepth);
	    }
	   }
	   else
	   {
	    String filePath = f.getAbsolutePath();
	   
	    if(suffix !=null)
	    {
	     int begIndex = filePath.lastIndexOf(".");//最后一个.(即后缀名前面的.)的索引
	     String tempsuffix = "";
	    
	     if(begIndex != -1)//防止是文件但却没有后缀名结束的文件
	     {
	      tempsuffix = filePath.substring(begIndex + 1, filePath.length());
	      System.out.println("tempsuffix"+tempsuffix);
	      
	     }
	    System.out.println(tempsuffix+"---"+tempsuffix);
	     if(tempsuffix.equals(suffix))
	     {
	      fileList.add(filePath);
	     }
	    }
	    else
	    {
	    	//后缀名为null则为所有文件
	     fileList.add(filePath);
	    }
	   
	   }
	  
	   System.out.println("fileList"+fileList);
	   return fileList;
	}
	
	/**
	    * 方法追加文件：使用FileWriter
	    * @param fileName
	    * @param content
	    */
	public static void appendMethod(String fileName, String content)
	{
	    try
	    {
	    	//打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
	     FileWriter writer = new FileWriter(fileName, true);
	     writer.write(content + "\r\n");
	     writer.close();
	    }
	    catch (IOException e)
	    {
	     e.printStackTrace();
	    }
	} 
	
	public static int getFailcases() {
		return failcases;
	}
	private static void setFailcases(int failcases) {
		FileViewer.failcases = failcases;
	}
	public static int getTests() {
		return tests;
	}
	private static void setTests(int tests) {
		FileViewer.tests = tests;
	}
	
	/**
	 * @param args
	 * @throws Exception 
	 * @throws IOException 
	 * @throws IOException 
	 * @throws WriteException 
	 * @throws BiffException 
	 */
	public static void excelhyperlink(List<String> temp) {
		if(temp.isEmpty()){
			System.out.println("没有符合要求的文件");
		}
		System.out.println("开始往测试结果excel文件里添加截屏图片链接。。。");
		for (Iterator<String> j = temp.iterator(); j.hasNext();){
			String tempfileName = j.next();
			
			if(tempfileName.contains("testcasemenu")){
				continue;
			}
			System.out.println(tempfileName);
				Workbook workbook = null;
				WritableWorkbook wbe = null;
				WritableSheet sheet = null;
					System.out.println("");
					try {
						workbook = Workbook.getWorkbook(new File(tempfileName));
						wbe = Workbook.createWorkbook(new File(tempfileName),workbook);
						sheet = wbe.getSheet(0);
					} catch (IOException e) {
						e.printStackTrace();
					} catch (BiffException e) {
						e.printStackTrace();
					} 
					
					String dirName = tempfileName.substring(0, tempfileName.length()-jobName.length()-4);
					System.out.println("tempfileName"+tempfileName);
					String time=dirName;
					testCaseMenu=dirName+"testcasemenu2result.xls";
					testcase=dirName+jobName+".xls";
					result=dirName;
				System.out.println("dirname:"+dirName);
					//遍历文件夹并写入excel
					File dir = new File(dirName); 
			        File[] files = dir.listFiles();
			        if (files == null) 
			            return; 
			        for (int i = 0; i < files.length; i++) { 
			        	if(files[i].getName().endsWith("jpg")){
			        		int writerow = Integer.parseInt(files[i].getName().substring(0,files[i].getName().length()-4));
			        		File file = new File(".\\"+files[i].getName());
			        		WritableHyperlink wh = new WritableHyperlink(18, writerow-1, file, "点击这里查看截屏！");
								try {
									sheet.addHyperlink(wh);
								} catch (RowsExceededException e) {
									e.printStackTrace();
								} catch (WriteException e) {
									e.printStackTrace();
								} 
			        	}
			        	 
			        }
			        try {
						wbe.write();
						wbe.close();
					} catch (IOException e) {
						e.printStackTrace();
					} catch (WriteException e) {
						e.printStackTrace();
					}
			        
			        
		}
		System.out.println("添加截屏链接成功！");
	}
}
