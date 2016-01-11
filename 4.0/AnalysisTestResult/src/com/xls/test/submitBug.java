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
		
		String Failed = null;//���������error
		String Theme = null; //��������
		String Num = null;//���������NO��	
		int NumOfCase = 0;//�������
		int ErrorAmount = 0;//����error������fail������
		int CaseNum = 0;//�������
		String Num1 = null;//��������
		String Findaction= null;
		int BeginRowCount = 0;//�������ִ���ʱ��������"��ʼ����"������
		int EndRowCount = 0;//�������ִ���ʱ��������"�˳�����"������
		String Result = "";//���Խ����
		String DetInfo = "";//���Խ����ϸ��Ϣ��
		String time;//����ʱ��
		String version = null;//���԰汾
		String VerSion = "version";
		String Switch = "Switch";
		System.out.println();
		String lj = lujing.substring(0,lujing.lastIndexOf("\\"));
		System.out.println(lujing);
		String caseName1 = lj+File.separator+"�Զ������Խ��BUG.xls";
		System.out.println("caseName1="+caseName1);
		//�ж��ļ��Ƿ����
		String path = testcase;
		File file = new File(path);
		
		if(!file.exists()){
			System.out.println("-----------���ļ�������-----------");
		}	
		else{
			Date now1 = new Date();
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
			time = dateformat.format(now1);
			
			//��ȡ��������ļ�
			String filenamemenu = testcase ;
			Workbook workbook1 = null;
			Sheet sheet1 = null;
			InputStream is1 = new FileInputStream(filenamemenu);
			workbook1 = Workbook.getWorkbook(is1);
			sheet1 = workbook1.getSheet(0);
			int RowCount = sheet1.getRows();//���������������
		
			// �½�bug�ύ�ļ�
			String filenamemenu1 = caseName1;
			WritableWorkbook wwb = null;
			WritableSheet ws = null;
//			OutputStream os = null;
//			os = new FileOutputStream("D:\\jenkins\\jobs\\2.9.9\\workspace\\tempResult\\2014-0319-09-56-00\\�Զ������Խ��BUG.xls");
//			wwb = Workbook.createWorkbook(os);
			wwb = Workbook.createWorkbook(new File(caseName1));
			ws = wwb.createSheet("�Զ������Խ��BUG�ύ", 0);	
				
		try{
				version = Configuration.readConfigString(VerSion, Switch);
				//��ȡ������ִ��ʧ�ܺ�����fail������
				for(int i = 0; i<RowCount;i++){
					Failed = String.valueOf((sheet1.getCell(20, i)).getContents());
					if(Failed.equals("error") || Failed.equals("fail")){
						ErrorAmount ++;
					}
				}
				
				int erroramount = 1;
	
				for(int rowCount=1;rowCount<RowCount;rowCount++){
					String Step = "";//��������
					//�жϸ�������Ϊ�ڼ�������,����ȡ��������ʼ������
					Num = String.valueOf((sheet1.getCell(0, rowCount)).getContents());
					if(Num.equals("")==false){
						NumOfCase++;
						Num1 =  String.valueOf((sheet1.getCell(0, rowCount)).getContents());
						BeginRowCount = rowCount;
						Step = "";//�����������Ĳ��Բ���������
					}
					
					Failed = String.valueOf((sheet1.getCell(20, rowCount)).getContents());			
					
					if(Failed.equals("error") || Failed.equals("fail")){
												
				inner:	for(int j=BeginRowCount+1;j<RowCount;j++){
							Findaction =  String.valueOf((sheet1.getCell(5, j)).getContents());
							if(Findaction.equals("�˳�����")){
								EndRowCount = j;
								break inner;
							}
						}
						
						//bug��ͷд��
							Label NumBT = new Label(0, 0, "BUG���");
							Label UsecaseNumBT = new Label(1, 0, "�������");
							Label ThemeBT = new Label(2, 0, "BUG��������");
							Label StepBT = new Label(3, 0, "���Բ���");
							Label VersionBT = new Label(4, 0, "���԰汾");
							Label TimeBT = new Label(5, 0, "����ʱ��");
							
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
								Step = Step + step[beginRowCount-1] + "("+Result+","+DetInfo+")��";
							} else{
								step[beginRowCount-1] = tempstep;
								Step = Step + step[beginRowCount-1];
							}
						}
							
						//bugд��excel��
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
				System.out.println("---------------------BUG ��  ��  ��  ��--------------------");
				workbook1.close();
			}
	}
}