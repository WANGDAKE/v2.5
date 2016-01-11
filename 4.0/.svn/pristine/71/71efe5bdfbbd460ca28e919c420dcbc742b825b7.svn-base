package com.test.ios;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.test.robotium.bean.CasecoverBean;
import com.test.robotium.bean.TestcaseBean;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;


public class CaseCover {
	/******遍历用例中得数据检查是否有自动跳转及用例套并存入CasecoverBean对象中******/
	ArrayList<CasecoverBean> arr = new ArrayList<CasecoverBean>();
	int auto = 1; //自动跳转初始化
	int casejunp = 1;  //用例套跳转初始化
	int maxAuto = 0;
	int maxCasejumo = 0;
	public boolean caseCover() throws BiffException, IOException
	{
		int startline = 0;
		boolean b = false;
		CasecoverBean caseBaen = new CasecoverBean();;
		FileInputStream input = new FileInputStream(Config.excelPath);
		Workbook book=null;
			book=Workbook.getWorkbook(input);
			Cell cell=null;
		TestcaseBean testcase=null;
		Sheet sheet=book.getSheet(0);
		for(int i=1;i<sheet.getRows();i++)
		{
			String[] str=new String[sheet.getColumns()];
			for(int j=0;j<sheet.getColumns();j++)
			{
				cell=sheet.getCell(j, i);
				str[j]=cell.getContents();
			}
			testcase = new TestcaseBean(str[0], str[1], str[2], str[3], str[4], str[5], str[6], str[7], str[8], str[9], str[10], str[11], str[12], str[13], str[14], str[15], str[16]);
			if(testcase.getTestcaseID()!=null||!testcase.getTestcaseID().equals(""))
			{
				try{
				if(!testcase.getAutojump().equals("")||testcase.getAutojump()!=null||!testcase.getCasecover().equals("")||testcase.getCasecover()!=null)
				{
//					if(startline != 0)
//					{
//						caseBaen.setEndLine((i-1));
						if(caseBaen.getAutojump() > maxAuto){
							maxAuto = caseBaen.getAutojump();
						}
						if(caseBaen.getCasejunp() > maxCasejumo){
							maxCasejumo = caseBaen.getCasejunp();
						}
						caseBaen = new CasecoverBean();
						startline = i;
						caseBaen.setStartLine(startline);
						caseBaen.setTestcaseID(testcase.getTestcaseID());
						caseBaen.setAutojump(Integer.parseInt(testcase.getAutojump()));
						caseBaen.setCasejunp(Integer.parseInt(testcase.getCasecover()));
						caseBaen.setEndLine(0);
						arr.add(caseBaen);
//					}
//					else
//					{
//					startline = i;
//					caseBaen.setStartLine(startline);
//					caseBaen.setTestcaseID(testcase.getTestcaseID());
//					caseBaen.setAutojump(Integer.parseInt(testcase.getAutojump()));
//					caseBaen.setCasejunp(Integer.parseInt(testcase.getCasecover()));
//					}
					b = true;
				}
				else if(testcase.getAutojump().equals("")||testcase.getAutojump()==null||testcase.getCasecover().equals("")||testcase.getCasecover()==null)
				{
					b= false;
					break;
				}
				}catch(Exception e){
					System.out.println("未获取到用例套及自动跳转信息,不执行用例套");
					WriteLog.writeComLog("未获取到用例套及自动跳转信息,不执行用例套");
					b = false;
					break;
				}
			}
		}
		book.close();
		return b;
	}
	/****判断是否执行自动跳转及用例套跳转***/
	public int deterMine()
	{
		int result = 0;//返回执行得行号
		for(int n = 0;n <=arr.size();n++)
		{
//			if(arr.get(n).getTestcaseID().equals(t.getTestcaseID()))
//			{
				if(arr.get(n).getAutojump()!=0&&arr.get(n).getCasejunp()!=0)
				{//判断用例中自动跳转及用例套都有值
					if(auto==arr.get(n).getAutojump()&&casejunp==arr.get(n).getCasejunp())
					{//找到相同得用例套id及自动跳转id
						result = arr.get(n).getStartLine();
						auto = auto + 1;
						break;
					}
					else if(n==(arr.size()-1))
					{//如果对象中没有找到相应得自动跳转id  用例套id＋1  自动跳转id初始化
						auto = 1;
						casejunp = casejunp +1;
						n = 0;
					}
					if(casejunp > maxCasejumo)
					{//如果用例套id超过最大用例套id 返回0停止自动化测试
						result = -1;
					}
				}
				else if (arr.get(n).getAutojump()!=0&&arr.get(n).getCasejunp()==0)
				{//判断用例中自动跳转
					if(auto==arr.get(n).getAutojump())
					{//找到相同得用例套id及自动跳转id
						result = arr.get(n).getStartLine();
						auto = auto + 1;
						break;
					}
					if(auto > maxAuto)
					{//如果自动跳转id大于最大跳转id  返回0停止自动化测试
						result = -1;
					}
				}
			}
//		}
		
		return result;
	}
	
	
	
	
}
