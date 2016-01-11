package com.xls.test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import net.sf.json.JSONObject;

/**
 * @author huangpanfeng
 *
 */
public class ReadExcelFile {
	private static String fileDir ;
	private static String fileName;
	private static String filePath;
		
	public static String getFileDir() {
		return fileDir;
	}

	private static void setFileDir(String fileDir) {
		ReadExcelFile.fileDir = fileDir;
	}

	public static String getFilePath() {
		return filePath;
	}

	private static void setFilePath(String filePath) {
		ReadExcelFile.filePath = filePath;
	}

	public  String getFileName() {
		return fileName;
	}

	private static void setFileName(String fileName) {
		ReadExcelFile.fileName = fileName;
	}

	public ReadExcelFile(String filedir,String filename){
		setFileDir(filedir);
		setFileName(filename);
		setFilePath(filedir+filename);
	}
	public ReadExcelFile(String filepath){
		setFilePath(filepath);
	}
	
	/**
	 * @return HashMap<Integer,String> ���ظ������������������map
	 * @update
	 * @param
	 * @exception
	 */
	public static HashMap<Integer,String> getCaseNumbMap(){
		HashMap<Integer,String> caseNumbMap = new HashMap<Integer,String>();
		Cell[] caseNumbCell = getColumnData(getSheet(myExcel()),0);
		for(int i=1;i<caseNumbCell.length;i++){
			
			String testCaseNumb = caseNumbCell[i].getContents();
			if(!"".equals(caseNumbCell[i].getContents())&& caseNumbCell[i].getContents() != null ){
				caseNumbMap.put(i, testCaseNumb);
			}
		}
		
		return caseNumbMap;
	}
	/**
	 * 2013-08-01 ���Խ���ж��߼��޸�
	 * 1���������Խ��Ϊfail��ǰ�ò����Ҷ���ʱ���쳣ʱ������������Ϊδͨ����
	 * 2���������Խ����Ϊpass�������������Ҷ�������쳣ʱ������������Ϊͨ����
	 * */
	@SuppressWarnings("unchecked")
	public HashMap<String, HashMap<String, ?>> getCaseResultMap(){
		HashMap<String, HashMap<String, ?>> resultMap = new HashMap<String, HashMap<String, ?>>();
		
		HashMap<String, Integer> countMap = new HashMap<String, Integer>();
		//��JSON����תΪmap
		Map<String, Map<String,Integer>> testCases = (Map<String, Map<String, Integer>>) getCaseRows();
		Iterator<Entry<String, Map<String, Integer>>> iterator = testCases.entrySet().iterator();
		String caseNumb = null;
		int startRow = 0;
		int endRow = 0;
		int fail=0;
		int testCasesnumb = testCases.size();
		int casesruntime=0;
		String ExtName = "test_";
//		DecimalFormat df = new DecimalFormat("########0.00"); 
		while(iterator.hasNext()){
			boolean result = false;
			Entry<String, Map<String, Integer>> entry = iterator.next();
			caseNumb = entry.getKey();
			Map<String, Integer> iterator2 = entry.getValue();
			startRow = iterator2.get("start");
			endRow = iterator2.get("end");
			int needCompare = 0;
			int realResult = 0;
			boolean isError = false;
			String failInfo = "";
			int temp = 0;
			HashMap<String, String> tempcaseMap = new HashMap<String, String>();
			for(int i= startRow; i <= endRow; i++){
				String isNeedCompare = getSheet(myExcel()).getCell(13, i).getContents();
				String testResult = getSheet(myExcel()).getCell(19, i).getContents();
				String errorJump = getSheet(myExcel()).getCell(21, i).getContents();
				String runtime = getSheet(myExcel()).getCell(18, i).getContents();
				if(!runtime.equals("")&&runtime != null){
					temp += Integer.parseInt(runtime.substring(3, runtime.length()-2));
//					System.out.println(temp);
				}
//				System.out.println(temp);
				/**
				 * 2013-08-01 ���Խ���ж��߼��޸�
				 * 1���������Խ��Ϊfail��ǰ�ò����Ҷ���ʱ���쳣ʱ������������Ϊδͨ����
				 * 2���������Խ����Ϊpass�������������Ҷ�������쳣ʱ������������Ϊͨ����
				 * if(!runtime.equals("")&&runtime != null){
				 	temp += Integer.parseInt(runtime.substring(6, runtime.length()-2));
				}
				if(isNeedCompare.equals("yes")){
					needCompare++;
				}
				if(testResult.contains("OK")||testResult.contains("ok")){
					realResult++;
				}
				if(isNeedCompare.equals("yes")&&!testResult.contains("OK")){
					failInfo += "������"+ ++i +"��ִ��ʧ��,������ϢΪ��" + 
						getSheet(myExcel()).getCell(18, --i).getContents() +"\n";
				}
				 */
//				System.out.println("isNeedCompare"+isNeedCompare);
//				System.out.println("testResult"+testResult);
//				System.out.println("runtime"+runtime);
				
				if((isNeedCompare.equals("")||isNeedCompare.equals(null))&& testResult.contains("error")||testResult.contains("Error")){
					isError = true;
					failInfo += "������"+(i+1) +"��ִ���쳣,������ϢΪ��" + "\n" +
							getSheet(myExcel()).getCell(20, i).getContents() +"\n";
				} else{
					if(isNeedCompare.equals("yes")||isNeedCompare.equals("exist")||isNeedCompare.equals("all")||isNeedCompare.equals("in")){
						needCompare++;
					}
					if(testResult.contains("pass")||testResult.contains("PASS")){
						realResult++;
					}
					if(isNeedCompare.equals("yes")||isNeedCompare.equals("all")||isNeedCompare.equals("in")){
						if(!testResult.contains("pass")&&errorJump.equals("")||errorJump.equals("null")){
							failInfo += "������"+ i +"��ִ��ʧ��,������ϢΪ��" + "\n" +
									getSheet(myExcel()).getCell(20, i).getContents() +"\n";
						}
						else if(!testResult.contains("pass")){
								failInfo += "������"+ i +"��ִ��ʧ��,������ϢΪ��" + "\n" +
										getSheet(myExcel()).getCell(20, i).getContents() +"\n";
						}
					}
				}
			}	
			if((needCompare == realResult ) && !isError){
				result = true;
			}
			if(result){
				
				tempcaseMap.put("@classname", "com.lectek.android.sfreader.ui.test");
				tempcaseMap.put("@name", ExtName + caseNumb);
				tempcaseMap.put("@time", String.valueOf(temp));
			}else{
				tempcaseMap.put("@classname", "com.lectek.android.sfreader.ui.test");
				tempcaseMap.put("@name", ExtName + caseNumb);
				tempcaseMap.put("@time", String.valueOf(temp));
				tempcaseMap.put("failure", failInfo);
				fail++;
			}
			casesruntime += temp;
			resultMap.put(caseNumb,(HashMap<String, String>) tempcaseMap.clone());
			tempcaseMap.clear();
		}
//		String casesRunTime = df.format(casesruntime/1000);
		countMap.put("casesRunTime", casesruntime);
		countMap.put("failCount", fail);
		countMap.put("testsCount", testCasesnumb);
		resultMap.put("count", countMap);
		return resultMap;
	}
	
	/**
	 * @return 
	 * ������������������ִ�н����json����<br>
	 * ���һ�������ж�������ȶԣ��������бȶԽ����Ϊok,�����������Ϊʧ�ܡ�
	 * @throws Exception 
	 * @update
	 */
	public JSONObject getCaseResultJSON() throws Exception{
		//��JSON����תΪmap
		@SuppressWarnings("unchecked")
		Map<String, Map<String,Integer>> testCases = (Map<String, Map<String, Integer>>) getCaseRows();
		String caseNumb = null;
		int startRow = 0;
		int endRow = 0;
		int fail=0;
		int testCasesnumb = testCases.size();
		
		JSONObject passCaseJSONObject = new JSONObject();
		JSONObject failCaseJSONObject = new JSONObject();
		JSONObject testCaseJSONObject = new JSONObject();
		Iterator<Entry<String, Map<String, Integer>>> iterator = testCases.entrySet().iterator();
		while(iterator.hasNext()){
			boolean result = false;
			Entry<String, Map<String, Integer>> entry = iterator.next();
			caseNumb = entry.getKey();
			Map<String, Integer> iterator2 = entry.getValue();
			startRow = iterator2.get("start");
			endRow = iterator2.get("end");
			int needCompare = 0;
			int realResult = 0;
			String failInfo = "";
			for(int i= startRow; i <= endRow; i++){
				String isNeedCompare = getSheet(myExcel()).getCell(14, i).getContents();
				String testResult = getSheet(myExcel()).getCell(15, i).getContents();
				if(isNeedCompare.equals("yes")){
					needCompare++;
				}
				if(testResult.contains("OK")||testResult.contains("ok")){
					realResult++;
				}
				if(isNeedCompare.equals("yes")&&!testResult.contains("OK")){
					failInfo += "������"+ ++i +"��ִ��ʧ��,������ϢΪ��" + 
						getSheet(myExcel()).getCell(20, i).getContents() +"\n";
				}
			}
			if(needCompare == realResult ){
				result = true;
			}
			String fileNameWithOutExtName = getFileName().substring(0, getFileName().length()-3);
			if(result){
				
				passCaseJSONObject.element("@classname", "com.lectek.android.ecp.MainFrontActivity");
				passCaseJSONObject.element("@name", fileNameWithOutExtName + caseNumb);
//				caseJSONObject.element("@time", "0.1");
				passCaseJSONObject.discard("@class");
				testCaseJSONObject.accumulate("testsuite", passCaseJSONObject);
			}
			if(!result){
				failCaseJSONObject.element("@classname", "com.lectek.android.ecp.MainFrontActivity");
				failCaseJSONObject.element("@name", fileNameWithOutExtName + caseNumb);
//				caseJSONObject.element("@time", "0.1");
				failCaseJSONObject.element("failure", failInfo);
				fail++;
				testCaseJSONObject.accumulate("testsuite", failCaseJSONObject);
			}
			
		}
		
//		testCaseJSONObject.element("@errors", 0);
		testCaseJSONObject.element("@failures",fail);
//		testCaseJSONObject.element("@name", "ecptest");
		testCaseJSONObject.element("@tests", testCasesnumb);
//		testCaseJSONObject.element("@skipped", 0);
		
//		testCaseJSONObject.element("@time", 10.905);
		return testCaseJSONObject;
	}
	
	public List<String> getCasesList(){
		List<String> caseList = new ArrayList<String>();
		Cell[] caseNumbCell = getColumnData(getSheet(myExcel()),0);
		String caseNumb = null;
		for(int i=1;i<caseNumbCell.length;i++){
			caseNumb = caseNumbCell[i].getContents();
			if(!"".equals(caseNumbCell[i].getContents())
					&& caseNumbCell[i].getContents() != null 
					&& !caseNumbCell[i].getContents().contains("end")){
				caseList.add(caseNumb);
			}
		}
		return caseList;
	}

	/**
	 * @return ���ظ�������������������ʼ�����ͽ���������json����
	 * ���ʾ��:{"001":{"start":1,"end":8},"002":{"start":9,"end":13}...}
	 * @update
	 */
	public JSONObject getCaseRows(){
		Cell[] caseNumbCell = getColumnData(getSheet(myExcel()),0);
		JSONObject cases = new JSONObject();
		JSONObject rows = new JSONObject();
		int start= 0;
		int end = 0;
		String caseNumb = null;
		for(int i=1;i<caseNumbCell.length;i++){
//			String testCaseNumb= caseNumbCell[i].getContents();
			if(!"".equals(caseNumbCell[i].getContents())&& caseNumbCell[i].getContents() != null ){
				
				if(start >0){
					rows.element("start", start);
					rows.element("end", end);
					cases.accumulate(caseNumb, rows);
					rows.clear();
				}
				start = i;
				caseNumb = caseNumbCell[i].getContents();
			}else{
				end = i;
			}
		}
		return cases;
	}
	
	static File myExcel(){
		File myExcel = new File(getFilePath());
		return myExcel;
	}
	
	public static Sheet getSheet(File file){
		Workbook book = null;
		try {
			book = Workbook.getWorkbook(file);
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Sheet sheet = book.getSheet(0);
		return sheet;
	}
	
	public Sheet getSheet(Workbook workbook){
		Sheet sheet = workbook.getSheet(0);
		return sheet;
	}
	
	public static Cell[] getColumnData(Sheet sheet,int column){
		
		
		Cell[] cell = sheet.getColumn(column);
		return cell;
	}

//	public static void main(String args[]){
//		new ReadExcelFile("C:"+ File.separator + "temp" + File.separator,"result.xls");
////			
//////		System.out.println(getCaseNumbMap());
//////		System.out.print(getCaseResultMap());
////		JSONObject jSONObject = new JSONObject();
////		@SuppressWarnings("static-access")
////		JSONObject jb = jSONObject.fromObject(getCaseResultMap());
//////		JSONArray jsons = jb.getJSONArray("params");
//////		int jsonLength = jsons.size();
//////		for(int i=0;i<jsonLength;i++){
//////			JSONObject tempJson = JSONObject.fromObject(jsons.get(i));
//////		     System.out.println("ddd:" + tempJson);
//////		}
////		System.out.println(jb);
//////		XMLSerializer xMLSerializer = new XMLSerializer();
////		xml2 = new XMLSerializer().write(jb);
////		System.out.println("xml2:" + xml2);
////		System.out.println(getCaseRows());
////		System.out.println(getCaseResultMap());
//	}
}