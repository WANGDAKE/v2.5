package com.xls.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import jxl.format.CellFormat;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;


public class AnalysisTestResult {
	static SimpleDateFormat filesd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	static double time = 0;
	static FileViewer fileViewer = new FileViewer();

	/**
	 * @param fileList 
	 * @param failCaseResult
	 * @update
	 * @param
	 * @exception
	 */
	@SuppressWarnings("unchecked")
	public static WritableSheet updateTestCaseResult(WritableSheet testCaseResult, List<String> fileList) {
		if(fileList.isEmpty()){
			System.out.println("没有符合要求的文件");
		}
		else{
			for (Iterator<String> i = fileList.iterator(); i.hasNext();){
				String tempFileName = i.next();
		    	if(tempFileName.contains("testcasemenu")){
		    		continue;
		    	}
		    	
		    	ReadExcelFile readExcelFile = new ReadExcelFile(tempFileName);
		    	List<String> tempList = readExcelFile.getCasesList();
		    	HashMap<String, HashMap<String, ?>> tempMap = readExcelFile.getCaseResultMap();
		    	Iterator<String> iterator = tempList.iterator();
		    	int row = 1;
		    	while(iterator.hasNext()){
		    		boolean caseRelsult = false;
		    		String caseName = iterator.next();
		    		HashMap<String, String> temp = (HashMap<String, String>) tempMap.get(caseName);
		    		if(!temp.containsKey("failure")){
		    			caseRelsult = true;
		    		}
		    		
		    		//更新测试用例详细执行结果
		    		
		    		try {
		    			CellFormat testresultcf = testCaseResult.getCell(0,1).getCellFormat();
			    		Label caseNamelabel =  new Label(0,row,caseName);
			    		caseNamelabel.setCellFormat(testresultcf);
			    		Label caseresultlabel = new Label(1,row,caseRelsult ? "通过":"未通过");
			    		caseresultlabel.setCellFormat(testresultcf);
			    		
			    		testCaseResult.addCell(caseNamelabel);
			    		testCaseResult.addCell(caseresultlabel);
						
					} catch (RowsExceededException e) {
						e.printStackTrace();
					} catch (WriteException e) {
						e.printStackTrace();
					}
		    		row++;
		    	}
		   
		    	
			}
		}
		return testCaseResult;
		
	}

	/**
	 * 
	 * @param reportsheet
	 * @return
	 * @update
	 * @param
	 * @exception
	 */
	@SuppressWarnings("static-access")
	public static WritableSheet updateReport(WritableSheet reportsheet){
		try {	
			CellFormat testinfocf = reportsheet.getCell(2,6).getCellFormat();
			CellFormat testresultcf = reportsheet.getCell(1,11).getCellFormat();
			//更新用例数
//			WritableCell casesCountCell = (WritableCell) reportsheet.getCell(2,7);
//			System.out.println("用例数：" + fileViewer.getTests());
			jxl.write.Number casesNumber = new jxl.write.Number(2,7,fileViewer.getTests());
			casesNumber.setCellFormat(testinfocf);
			reportsheet.addCell(casesNumber);
			
			//更新测试时间
			
			String now = filesd.format(new Date());
			Label timelbl =  new Label(2,8,now);
			timelbl.setCellFormat(testinfocf);
			reportsheet.addCell(timelbl);
			
			//更新通过的用例数
//			WritableCell passCountCell = (WritableCell) reportsheet.getCell(1,11);
			jxl.write.Number passNumber = new jxl.write.Number(1,12,fileViewer.getTests()-fileViewer.getFailcases());
			passNumber.setCellFormat(testresultcf);
			reportsheet.addCell(passNumber);

			
			//更新失败的用例数
//			WritableCell failCountCell = (WritableCell) reportsheet.getCell(1,13);
			jxl.write.Number failNumber = new jxl.write.Number(1,13,fileViewer.getFailcases());
			failNumber.setCellFormat(testresultcf);
			reportsheet.addCell(failNumber);
			
			
						
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
		return reportsheet;
	}
	
	/**
	 * 
	 * @param testcaseresult
	 * @return
	 * @update
	 * @param
	 * @exception
	 */
	@SuppressWarnings("unchecked")
	public static  WritableSheet updateFailCaseResult(WritableSheet failCaseResult,List<String> fileList){
		if(fileList.isEmpty()){
			System.out.println("没有符合要求的文件");
		}
		else{
			for (Iterator<String> i = fileList.iterator(); i.hasNext();){
				String tempFileName = i.next();
		    	if(tempFileName.contains("testcasemenu")){
		    		continue;
		    	}
		    	
		    	ReadExcelFile readExcelFile = new ReadExcelFile(tempFileName);
		    	List<String> tempList = readExcelFile.getCasesList();
		    	HashMap<String, HashMap<String, ?>> tempMap = readExcelFile.getCaseResultMap();
		    	Iterator<String> iterator = tempList.iterator();
		    	int row = 1;
		    	HashMap<String, Integer> countmap = (HashMap<String, Integer>) tempMap.get("count");
		    	if(countmap.get("failCount") == 0){
		    		System.out.println("用例结果文件："+tempFileName.substring(14, tempFileName.length())
		    				+"中无用例执行失败！");
		    		continue;
		    	}
		    	while(iterator.hasNext()){
		    		String caseName = iterator.next();
		    		HashMap<String, String> temp = (HashMap<String, String>) tempMap.get(caseName);
		    		
		    		//更新测试用例详细执行结果
		    		try {
		    			if(temp.containsKey("failure")){
		    				String failInfo = temp.get("failure");
				    		Label caseNamelabel =  new Label(0,row,caseName);
				    		CellFormat cf = failCaseResult.getCell(1, 1).getCellFormat();
				    		
							caseNamelabel.setCellFormat(cf );
							
				    		Label failInfolabel = new Label(1,row,failInfo);
				    		failInfolabel.setCellFormat(cf);
				    		failCaseResult.addCell(caseNamelabel);
				    		failCaseResult.addCell(failInfolabel);
				    		row++;
						}
						
					} catch (RowsExceededException e) {
						e.printStackTrace();
					} catch (WriteException e) {
						e.printStackTrace();
					}
					
		    	}    	
			}
		}
		return failCaseResult;
	}
	
}
