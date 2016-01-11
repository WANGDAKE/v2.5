package com.test.ios;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.robotium.solo.Solo;
import com.test.robotium.bean.TestcaseBean;


import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import android.app.Activity;
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener;
import android.widget.TextView;



public class Dynamic {
	/**********动态数据处理***********/
	public List<String> dates = new ArrayList<String>();
	public List<String> compu = new ArrayList<String>();
	public Solo solo;
	Label res = null;
	float result = 0;
	String mess = null;
	public void add(String date,int i,Label b,WritableSheet ws) throws RowsExceededException, WriteException
	{
		dates.add(date);
		System.out.println("add--["+date+"]存在动态数据第"+dates.size());
		b = new Label(20, i, "["+date+"]存在动态数据第["+dates.size()+"]");
		ws.addCell(b);
		WriteLog.writeComLog("执行第["+i+"]行["+date+"]存在动态数据第["+dates.size()+"]");
	}
	public void compare_yes(String date,int i,Label b,WritableSheet ws) throws RowsExceededException, WriteException
	{
		
		if(dates.size()==0){
			System.out.println("list中没有数据");
			b = new Label(19, i, "fail");
			res = new Label(20, i, "list中没有数据");
			ws.addCell(res);
			ws.addCell(b);
			WriteLog.writeComLog("执行第["+i+"]行.fail.list中没有数据");
			return;
		}
		
		String value = null;
		int n=0;
		for(;n<dates.size();n++)
		{
			
			value =value+"第"+(n+1)+""+dates.get(n);
			System.out.println("n是等于"+n);
			if(dates.get(n).equals(date))
			{
				System.out.println("compare_yes通过---"+date+"["+dates+"]");
				b = new Label(19, i, "pass");
				res = new Label(20, i, "实际结果["+date+"]与动态数据中有相同数据["+dates+"]第["+(n+1)+"]行相同");
				ws.addCell(res);
				ws.addCell(b);
				WriteLog.writeComLog("执行第["+i+"]行，pass实际结果与动态数据中有相同数据["+date+"]第["+(n+1)+"]行");
				return;
			}
		}
		if(n==dates.size())
		{
			System.out.println("compare_yes不通过实际结果未在动态数据中查询到"+date);
			b = new Label(19, i, "fail");
			res = new Label(20, i, "实际结果未在动态数据中查询到["+date+"].动态数据中所有数据["+dates+"]");
			ws.addCell(b);
			ws.addCell(res);
			WriteLog.writeComLog("执行第["+i+"]行.fail实际结果未在动态数据中查询到["+date+"]动态数据中所有数据["+dates+"]");
		}
	}
	
	
	public void compare_no(String date,int i,Label b,WritableSheet ws) throws RowsExceededException, WriteException
	{
		
	
		if(dates.size()==0){
			System.out.println("list中没有数据");
			b = new Label(19, i, "fail");
			res = new Label(20, i, "list中没有数据");
			ws.addCell(res);
			ws.addCell(b);
			WriteLog.writeComLog("执行第["+i+"]行.fail.list中没有数据");
			return;
		}
		String value = null;
		for(int n=0;n<dates.size();n++)
		{
			System.out.println("compare_no通过list值是["+dates+"],当前值["+date+"]");
			value =value+"第"+(n+1)+""+dates.get(n);
			if(dates.get(n).equals(date)==false)
			{
				b = new Label(19, i, "pass");
				res = new Label(20, i, "实际结果"+date+"与动态数据都不同。您的预期结果是NO.["+dates+"]");
				ws.addCell(res);
				ws.addCell(b);
				WriteLog.writeComLog("执行第["+i+"]行，pass实际结果与动态数据都不同。您的预期结果是NO");
			}else if(dates.get(n).equals(date)){
				b = new Label(19, i, "fail");
				res = new Label(20, i, "实际结果在动态数据中查询到["+date+"]，您的期望是NO.动态数据中所有数据["+dates+"]");
				ws.addCell(b);
				ws.addCell(res);
				WriteLog.writeComLog("执行第["+i+"]行.fail实际结果在动态数据中查询到["+date+"]，您的期望是NO");
				return;
			}
			else if(n==dates.size())
			{
				System.out.println("compare_no不通过list值是["+dates+"],当前值["+date+"]");
				b = new Label(19, i, "fail");
				res = new Label(20, i, "实际结果在动态数据中查询到["+date+"]，您的期望是NO.动态数据中所有数据["+dates+"]");
				ws.addCell(b);
				ws.addCell(res);
				WriteLog.writeComLog("执行第["+i+"]行.fail实际结果在动态数据中查询到["+date+"]，您的期望是NO");
			}
		}
	}
	
	public void compare_in(String date,int i,Label b,WritableSheet ws) throws RowsExceededException, WriteException
	{
		if(dates.size()==0){
			System.out.println("list中没有数据");
			b = new Label(19, i, "fail");
			res = new Label(20, i, "list中没有数据");
			ws.addCell(res);
			ws.addCell(b);
			WriteLog.writeComLog("执行第["+i+"]行.fail.list中没有数据");
			return;
		}
		
		
		String value = null;
		int n=0;
		for(;n<dates.size();n++)
		{
			value =value+"第"+(n+1)+""+dates.get(n);
			if(dates.get(n).contains(date)||date.contains(dates.get(n)))
			{
				System.out.println("compare_in通过list值是["+dates+"],当前值["+date+"]");
				b = new Label(19, i, "pass");
				res = new Label(20, i, "["+date+"]包含["+dates.get(n)+"]");
				ws.addCell(b);
				ws.addCell(res);
				WriteLog.writeComLog("执行第["+i+"]行，pass["+date+"]包含["+dates.get(n)+"]");
				return;
			}
		
		}
		 if(n==dates.size())
			{
				System.out.println("compare_in不通过list值是["+dates+"],当前值["+date+"]");
				b = new Label(19, i, "fail");
				res = new Label(20, i, "动态结果中不存在["+date+"].动态数据中所有数据"+dates);
				ws.addCell(b);
				ws.addCell(res);
				WriteLog.writeComLog("执行第["+i+"]行.fail["+date+"]不包含["+dates+"]中的值");
			}
	}
	
	public void compare_all( Boolean result,String 	datevalue,int i,Label b,WritableSheet ws) throws RowsExceededException, WriteException
	{
		String value = null;
			if(result){
				System.out.println("compare_all_true"+datevalue);
				b = new Label(19, i, "pass");
				res = new Label(20, i, "界面中有找到你的期望值"+datevalue);
				ws.addCell(b);
				ws.addCell(res);
				WriteLog.writeComLog("执行第["+i+"]行.界面中有找到你的期望值"+datevalue);
				return;
			}else{
				System.out.println("compare_all_false"+datevalue);
				b = new Label(19, i, "fail");
				res = new Label(20, i, "界面中m没有找到你的期望值"+datevalue);
				ws.addCell(b);
				ws.addCell(res);
				WriteLog.writeComLog("执行第["+i+"]行.界面中有找到你的期望值"+datevalue);
			}
		}
	
	
	public void clean()
	{
		dates.clear();
		WriteLog.writeComLog("清除动态数据");
	}
	public void compare_money(String nomoney,String bookmoney,String equalsmoney ,int i,Label b,WritableSheet ws) throws RowsExceededException, WriteException
	{
		if(dates.size()==0){
			System.out.println("list中没有数据");
			b = new Label(19, i, "fail");
			res = new Label(20, i, "总阅点没有获取到");
			ws.addCell(res);
			ws.addCell(b);
			WriteLog.writeComLog("执行第["+i+"]行.fail.总阅点没有获取到");
			return;
		}
		
		
		String value = null;
		int n=0;
		for(;n<dates.size();n++)
		{
			value =value+"第"+(n+1)+""+dates.get(n);
			if(dates.get(n).equals(equalsmoney))
			{
				System.out.println("compare_money当前阅点["+nomoney+"]书本阅点["+bookmoney+"]购买前阅点["+dates.get(n)+"]");
				b = new Label(19, i, "pass");
				res = new Label(20, i, "当前阅点["+nomoney+"]书本阅点["+bookmoney+"]购买前阅点["+dates.get(n)+"]");
				ws.addCell(b);
				ws.addCell(res);
				WriteLog.writeComLog("执行第["+i+"]行，pass当前阅点["+nomoney+"]书本阅点["+bookmoney+"]购买前阅点["+dates.get(n)+"]");
				return;
			}
		
		}
		 if(n==dates.size())
			{
				System.out.println("compare_money不通过当前阅点["+nomoney+"]书本阅点["+bookmoney+"]购买前阅点["+dates.get(n)+"]");
				b = new Label(19, i, "fail");
				res = new Label(20, i, "当前阅点["+nomoney+"]书本阅点["+bookmoney+"]购买前阅点["+dates.get(n)+"]");
				ws.addCell(b);
				ws.addCell(res);
				WriteLog.writeComLog("执行第["+i+"]行.fail当前阅点["+nomoney+"]书本阅点["+bookmoney+"]购买前阅点["+dates.get(n)+"]");
			}
	}
	
	
	/****获取元素中得数字用来计算****/
	public void addDigital(String mon,TestcaseBean test)
	{
		if(test.getValue().equals("")||test.getValue()==null&&test.getValue1().equals("")||test.getValue1()==null)
		{//判断用例中参数1及参数2都为空 不截取操作
			compu.add(mon);
		}
		else if(!test.getValue().equals("")||test.getValue()!=null&&test.getValue1().equals("")||test.getValue1()==null)
		{//判断用例中参数1有值 参数2为空 从参数1得值开始截取
			mess = mon.substring(Integer.parseInt(test.getValue()));
			compu.add(mess);
			mess = null;
		}
		else if(!test.getValue().equals("")||test.getValue()!=null&&!test.getValue1().equals("")||test.getValue1()!=null)
		{//判断用例中参数1及参数2都有值得情况下截取
			mess = mon.substring(Integer.parseInt(test.getValue()), Integer.parseInt(test.getValue1()));
			compu.add(mess);
			mess = null;
		}
	}
	public void digitalComputation_add(TestcaseBean test,int i,Label b,WritableSheet ws) throws RowsExceededException, WriteException
	{
		for(int dig = 0;dig < compu.size();dig++)
		{
			result=result+Float.parseFloat(compu.get(dig));
			System.out.println(result);
		}
		if(result==Float.parseFloat(test.getExvalue()))
		{//计算结果与期望比较通过
			System.out.println( "计算结果实际值与期望值一致为"+result);
			b = new Label(20, i, "计算结果实际值与期望值一致为"+result);
			ws.addCell(b);
			WriteLog.writeComLog("执行第"+i+"行计算结果实际值与期望值一致为"+result);
		}
		else
		{//计算结果与期望比较不通过
			System.out.println("计算结果与预期值不一致计算结果为"+result+"预期值为"+test.getExvalue());
			b = new Label(20, i, "计算结果与预期值不一致计算结果为"+result+"预期值为"+test.getExvalue());
			ws.addCell(b);
			WriteLog.writeComLog("执行第"+i+"行计算结果与预期值不一致计算结果为"+result+"预期值为"+test.getExvalue());
		}
		
	}
	
	
	
	
}
