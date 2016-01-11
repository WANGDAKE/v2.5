package com.xls.test;

import java.io.File;
import java.io.FileOutputStream;

import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Administrator
 *
 */
public class WriteXmlFile {
	
//	public static JSONObject mapToJson(HashMap<String,Boolean> map){
//		JSONObject testCaseJSONObject = new JSONObject();
//		
//		JSONObject passCaseJSONObject = new JSONObject();
//		JSONObject failCaseJSONObject = new JSONObject();
//		Iterator<Entry<String, Boolean>> iterator = map.entrySet().iterator();
//		int fail=0;
//		int testcases = map.size();
//		while(iterator.hasNext()){
//			Entry<String, Boolean> entry = iterator.next();
//			String key = entry.getKey();
//			boolean value = entry.getValue();
//			
//			if(value){
//				passCaseJSONObject.element("@classname", "com.lectek.android.ecp.MainFrontActivity");
//				passCaseJSONObject.element("@name", "翼聊测试"+key);
//				passCaseJSONObject.element("@time", "0.1");
//				passCaseJSONObject.discard("@class");
//				testCaseJSONObject.accumulate("testsuite", passCaseJSONObject);
//				
//			}else{
//				failCaseJSONObject.element("@classname", "com.lectek.android.ecp.MainFrontActivity");
//				failCaseJSONObject.element("@name", "翼聊测试" + key);
//				failCaseJSONObject.element("@time", "0.1");
//				failCaseJSONObject.element("failure", "失败了！");
//				testCaseJSONObject.accumulate("testsuite", failCaseJSONObject);
//				fail++;
//			}
//		}
//		testCaseJSONObject.element("@errors", 0);
//		testCaseJSONObject.element("failures",fail);
//		//TODO 获取系统主机名待优化
//		testCaseJSONObject.element("@hostname", "2012-20130117UF");
//		testCaseJSONObject.element("@name", "翼聊自动化测试");
//		testCaseJSONObject.element("@tests", testcases);
//		testCaseJSONObject.element("@skipped", 0);
//		testCaseJSONObject.element("@time", 10.905);
//		
//		testCaseJSONObject.element("@timestamp", "2013-07-22T10:20:03");
//		return testCaseJSONObject;
//	}
	
	/**
	 * @param dir
	 * @param fileName
	 * @param fileContent
	 * @update
	 */
	public static void writeXmlFile(String dir, String fileName, String fileContent){
		try {
			//指定xml文件输出路径   
			File parent = new File(dir);
			//判断xml文件输出路径是否存在,不存在则创建
			if(!parent.isDirectory()) {
				parent.mkdir();
			}
			
			SimpleDateFormat filesd = new SimpleDateFormat("yyyyMMdd-HHmmss");
			String now = filesd.format(new Date());
			
			//命名xml文件
			String sourcename = "test-" + fileName + "-" + now;
			String filename = sourcename + ".xml";
			File file = new File(parent, filename);
			
			//输出xml文档
//			OutputFormat outfmt = new OutputFormat();
//			outfmt.setEncoding("UTF-8");
//			XMLWriter output = new XMLWriter(new FileOutputStream(file),outfmt);
//			output.write(fileContent.getBytes("UTF-8"));
//			output.close();
			
			OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(file),"UTF-8");
			out.write(fileContent);
			out.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			   }
		
	}
	
	public static void print(String forPrint){
		System.out.println(forPrint);
	}
	/**
	 * @param args
	 * @throws Exception 
	 */
//	public static void main(String[] args) throws Exception {
//		ReadExcelFile readExcelFile= new ReadExcelFile("C:"+ File.separator + "temp" + File.separator + "result.xls");
//		System.out.println(readExcelFile.getCaseResultJSON());
//		XMLSerializer xMLSerializer = new XMLSerializer();
////		print("xMLSerializer.getObjectName()" + xMLSerializer.getObjectName());
//		xMLSerializer.setObjectName("testsuites");
//		
////		print("xMLSerializer.getArrayName()" + xMLSerializer.getArrayName());
////		print("xMLSerializer.getElementName()" + xMLSerializer.getElementName());
//		xMLSerializer.setElementName("testcase");
////		print("xMLSerializer.getElementName()" + xMLSerializer.getElementName());
////		print("xMLSerializer.getRootName()" + xMLSerializer.getRootName());
////		xMLSerializer.removeNamespace("class");
////		xMLSerializer.setRootName("testsuite");
////		xMLSerializer.setElementName("testcase");
////		@SuppressWarnings("static-access")
//		String xml2 = xMLSerializer.write(readExcelFile.getCaseResultJSON());
//		
//		print( xml2 );
//		String testDir = "d:"+ File.separator + "jenkins"+ File.separator +"jobs"+ 
//				File.separator +"demo"+ File.separator +"workspace"+ File.separator +"test";
//		writeXmlFile(testDir ,"ecp",xml2);
////		readXML2Object();
//		
//	}

}