package com.xls.test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ConvertFormat {
	private static String fileName ;
	
	private static File myExcel(){
		File myExcel = new File(getFileName());
		return myExcel;
	
	}
	
	public ConvertFormat(String fileName){
		setFileName(fileName);
	}

	public static String getFileName() {
		return fileName;
	}

	private static void setFileName(String fileName) {
		ConvertFormat.fileName = fileName;
	}

	public static HashMap<Integer,String> getCaseNumbMap(){
		HashMap<Integer,String> caseNumbMap = new HashMap<Integer,String>();
		Cell[] caseNumbCell = getColumnData(0);
		for(int i=1;i<caseNumbCell.length;i++){
			
			String testCaseNumb = caseNumbCell[i].getContents();
			if(!"".equals(caseNumbCell[i].getContents())&& caseNumbCell[i].getContents() != null ){
				caseNumbMap.put(i, testCaseNumb);
			}
		}
		
		return caseNumbMap;
	}
	
	public static HashMap<String,Boolean> getCaseResultMap(){
		boolean result = false;
		
		Cell[] expectCell = getColumnData(2);
		HashMap<Integer,String> caseNumbMap = getCaseNumbMap();
		HashMap<String,Boolean> caseResultMap = new HashMap<String,Boolean>();
		for(int i=0;i<expectCell.length;i++){
			
			if(expectCell[i].getContents().equals("期望")){
				String testResult = getSheet().getCell(15, i).getContents();
				if(testResult.equals("运行第1次，OK")){
					result = true;	
				}
				String caseNumb = caseNumbMap.get(i-2);
				caseResultMap.put(caseNumb, result);
			}
		}
		
		return caseResultMap;
	}

	private static Sheet getSheet(){
		Workbook book = null;
		try {
			book = Workbook.getWorkbook(myExcel());
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Sheet sheet = book.getSheet(0);
		return sheet;
	}
	
	private static Cell[] getColumnData(int column){
		
		
		Cell[] cell = getSheet().getColumn(column);
		return cell;
	}
	
//	public static void main(String args[]){
//		new ConvertFormat("C:"+ File.separator + "temp" + File.separator + "result.xls");
//			
//		System.out.println(getCaseNumbMap());
//		System.out.print(getCaseResultMap());
//		
//	}
}
