package com.xls.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;


import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class submitBug {

	public void submitbug(String testcase,String lujing) throws Exception{
		
		String Failed = null;//用例结果列error
		String Theme = null; //用例主题
		String Num = null;//用例结果的NO列	
		int NumOfCase = 0;//用例编号
		int ErrorAmount = 0;//出现error或者是fail的数量
		int CaseNum = 0;//用例编号
		String Num1 = null;//用例主题
		String Findaction= null;
		int BeginRowCount = 0;//用例出现错误时，该用例"开始程序"的行数
		int EndRowCount = 0;//用例出现错误时，该用例"退出程序"的行数
		String Result = "";//测试结果；
		String DetInfo = "";//测试结果详细信息；
		String time;//测试时间
		String version = null;//测试版本
		String VerSion = "version";
		String Switch = "Switch";
		System.out.println();
		String lj = lujing.substring(0,lujing.lastIndexOf("\\"));
		System.out.println(lujing);
		String caseName1 = lj+File.separator+"自动化测试结果BUG.xls";
		System.out.println("caseName1="+caseName1);
		//判断文件是否存在
		String path = testcase;
		File file = new File(path);
		
		if(!file.exists()){
			System.out.println("-----------该文件不存在-----------");
		}	
		else{
			Date now1 = new Date();
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
			time = dateformat.format(now1);
			
			//读取用例结果文件
			String filenamemenu = testcase ;
			Workbook workbook1 = null;
			Sheet sheet1 = null;
			InputStream is1 = new FileInputStream(filenamemenu);
			workbook1 = Workbook.getWorkbook(is1);
			sheet1 = workbook1.getSheet(0);
			int RowCount = sheet1.getRows();//用例结果的总行数
		
			// 新建bug提交文件
			String filenamemenu1 = caseName1;
			WritableWorkbook wwb = null;
			WritableSheet ws = null;
//			OutputStream os = null;
//			os = new FileOutputStream("D:\\jenkins\\jobs\\2.9.9\\workspace\\tempResult\\2014-0319-09-56-00\\自动化测试结果BUG.xls");
//			wwb = Workbook.createWorkbook(os);
			wwb = Workbook.createWorkbook(new File(caseName1));
			ws = wwb.createSheet("自动化测试结果BUG提交", 0);	
				
		try{
				version = Configuration.readConfigString(VerSion, Switch);
				//获取用例中执行失败和期望fail的行数
				for(int i = 0; i<RowCount;i++){
					Failed = String.valueOf((sheet1.getCell(20, i)).getContents());
					if(Failed.equals("error") || Failed.equals("fail")){
						ErrorAmount ++;
					}
				}
				
				int erroramount = 1;
	
				for(int rowCount=1;rowCount<RowCount;rowCount++){
					String Step = "";//用例步骤
					//判断该条用例为第几条用例,并获取该用例开始的行数
					Num = String.valueOf((sheet1.getCell(0, rowCount)).getContents());
					if(Num.equals("")==false){
						NumOfCase++;
						Num1 =  String.valueOf((sheet1.getCell(0, rowCount)).getContents());
						BeginRowCount = rowCount;
						Step = "";//对上条用例的测试步骤进行清空
					}
					
					Failed = String.valueOf((sheet1.getCell(20, rowCount)).getContents());			
					
					if(Failed.equals("error") || Failed.equals("fail")){
												
				inner:	for(int j=BeginRowCount+1;j<RowCount;j++){
							Findaction =  String.valueOf((sheet1.getCell(5, j)).getContents());
							if(Findaction.equals("退出程序")){
								EndRowCount = j;
								break inner;
							}
						}
						
						//bug表头写入
							Label NumBT = new Label(0, 0, "BUG编号");
							Label UsecaseNumBT = new Label(1, 0, "用例编号");
							Label ThemeBT = new Label(2, 0, "BUG主题描述");
							Label StepBT = new Label(3, 0, "测试步骤");
							Label VersionBT = new Label(4, 0, "测试版本");
							Label TimeBT = new Label(5, 0, "测试时间");
							
							ws.addCell(NumBT);
							ws.addCell(UsecaseNumBT);
							ws.addCell(ThemeBT);
							ws.addCell(StepBT);
							ws.addCell(VersionBT);
							ws.addCell(TimeBT);
							
						for(int beginRowCount = BeginRowCount;beginRowCount<=EndRowCount;beginRowCount++){
							String[] step = new String[EndRowCount];
							String tempstep = String.valueOf((sheet1.getCell(23, beginRowCount)).getContents());
							Result = String.valueOf((sheet1.getCell(20, beginRowCount)).getContents());
							DetInfo = String.valueOf((sheet1.getCell(21, beginRowCount)).getContents());
							
							if(beginRowCount==rowCount && (Result.equals("fail")||Result.equals("error"))){
								step[beginRowCount-1] = tempstep;
								Step = Step + step[beginRowCount-1] + "("+Result+","+DetInfo+")；";
							} else{
								step[beginRowCount-1] = tempstep;
								Step = Step + step[beginRowCount-1];
							}
						}
							
						//bug写入excel中
							Label numBT = new Label(0, erroramount, String.valueOf(erroramount));
							Label usecaseNumBT = new Label(1, erroramount, String.valueOf(NumOfCase));
							Label themeBT = new Label(2, erroramount, Num1);
							Label stepBT = new Label(3, erroramount, Step);
							Label versionBT = new Label(4, erroramount, version);
							Label timeBT = new Label(5, erroramount, time);
								
							ws.addCell(numBT);
							ws.addCell(themeBT);
							ws.addCell(usecaseNumBT);
							ws.addCell(stepBT);
							ws.addCell(versionBT);
							ws.addCell(timeBT);
							erroramount++;
						} 
					}
				} finally {
					wwb.write();
					wwb.close();
//					os.flush();
//					os.close();
					}
				System.out.println("---------------------BUG 提  交  成  功--------------------");
				workbook1.close();
			}
	}
}