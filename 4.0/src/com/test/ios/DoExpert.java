package com.test.ios;

import android.widget.TextView;

import com.robotium.solo.Solo;
import com.test.robotium.bean.TestcaseBean;

import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;


public class DoExpert {
	Solo solo;
	Label res = null;
	/***********期望比较
	 * @throws WriteException 
	 * @throws RowsExceededException *********/
	public Boolean doExpert_yes(TestcaseBean test,String message,int i,Label b,WritableSheet ws) throws RowsExceededException, WriteException
	{
		
		try{
		if(test.getExvalue().equals(message))
		{
				System.out.println("doExpert_yes通过期望比较当前值----"+message+"用例中获取的值----"+test.getExvalue());
				test.setResult("pass预期值与实际值一致["+message+"]");
				b = new Label(19, i, "pass");
				res = new Label(20, i, "预期值与实际值一致"+message);
				ws.addCell(res);
				ws.addCell(b);
				WriteLog.writeComLog("执行第["+i+"]行"+"pass预期值与实际值一致["+message+"]");
				return true;
		}
		else
		{
			System.out.println("doExpert_yes不通过期望比较当前值----"+message+"用例中获取的值----"+test.getExvalue());
			test.setResult("fail实际值：["+message+"]预期值["+test.getExvalue()+"]");
			b = new Label(19, i, "fail");
			res = new Label(20, i, "预期值与实际值不一致---实际值：["+message+"]预期值["+test.getExvalue()+"]");
			ws.addCell(res);
			ws.addCell(b);
			WriteLog.writeComLog("执行第["+i+"]行"+"fail预期值与实际值不一致---实际值：["+message+"]预期值["+test.getExvalue()+"]");
			return false;
		}
	}catch(NullPointerException e)
	{System.out.println("doExpert_yes通过期望比较当前值出现错误----"+message+"用例中获取的值----"+test.getExvalue());
		WriteLog.writeComLog("执行第["+i+"]行"+"发现空值请检查");
		return false;
	}
		}
	
	public Boolean  doExpert_in(TestcaseBean test,String message,int i,Label b,WritableSheet ws)throws RowsExceededException, WriteException
	{
		try{	
			if(message.contains(test.getExvalue())||test.getExvalue().contains(message))
			{
				System.out.println("匹配记过in通过，期望比较当前值----"+message+"用例中获取的值----"+test.getExvalue());
				test.setResult("pass["+message+"]中包含["+test.getExvalue()+"]");
				b = new Label(19, i, "pass");
				res = new Label(20, i, "["+message+"]中包含["+test.getExvalue()+"]");
				ws.addCell(res);
				ws.addCell(b);
				WriteLog.writeComLog("执行第["+i+"]行"+"pass["+message+"]中包含["+test.getExvalue()+"]");
				return true;
			}else {
				System.out.println("匹配记过in不通过，期望比较当前值----"+message+"用例中获取的值----"+test.getExvalue());
				test.setResult("fail["+message+"]不中包含["+test.getExvalue()+"]");
				b = new Label(19, i, "fail");
				res = new Label(20, i, "["+message+"]不中包含["+test.getExvalue()+"]");
				ws.addCell(res);
				ws.addCell(b);
				WriteLog.writeComLog("执行第["+i+"]行"+"fail["+message+"]不中包含["+test.getExvalue()+"]");
				return false;
			}
		}catch(NullPointerException e)
			{
			System.out.println("匹配记过in通过错误，期望比较当前值----"+message+"用例中获取的值----"+test.getExvalue());
				WriteLog.writeComLog("执行第["+i+"]行"+"发现空值请检查");
				return false;
			}
	
	}
	public void doExpert_all(Boolean result,TestcaseBean test,int i,Label b,WritableSheet ws) throws RowsExceededException, WriteException
	{
		
			if(result)
			{
				System.out.println("doExpert_all_true"+solo.waitForText(test.getExvalue()));
				b = new Label(19, i, "pass");
				res = new Label(20, i, "当前界面能够找到期望值["+test.getExvalue()+"]");
				ws.addCell(res);
				ws.addCell(b);
				WriteLog.writeComLog("执行第["+i+"]行.pass当前界面能够找到期望值"+test.getExvalue());
				return;
				
			}else {
				System.out.println("doExpert_all_false"+solo.waitForText(test.getExvalue()));
				b = new Label(19, i, "fail");
				res = new Label(20, i, "当前屏幕没有找到你的期望值["+test.getExvalue()+"]");
				ws.addCell(res);
				ws.addCell(b);
				WriteLog.writeComLog("执行第["+i+"]行.fail当前屏幕没有找到你的期望值["+test.getExvalue()+"]");
			}
			

	}
	public boolean  doExpert_taost(TestcaseBean test,int i,Label b,WritableSheet ws)throws RowsExceededException, WriteException
	{
		System.out.println("doExpert_taost进入");
	try{	
		if(test.getExist().equals("yes")){
			if(solo.waitForText(test.getExvalue()))
			{
				System.out.println("taost验证通过，界面有出现-"+test.getExvalue());
				test.setResult("taost验证通过，界面有出现-"+test.getExvalue());
				b = new Label(19, i, "pass");
				res = new Label(20, i, "界面有出现-"+test.getExvalue());
				ws.addCell(res);
				ws.addCell(b);
				WriteLog.writeComLog("taost验证通过，界面有出现-"+test.getExvalue());
				return true;
				
			}else{
				System.out.println("taost验证不通过，界面没有出现-"+test.getExvalue());
				test.setResult("taost验证不通过，界面没有出现-"+test.getExvalue());
				b = new Label(19, i, "fail");
				res = new Label(20, i, "界面没有出现-"+test.getExvalue());
				ws.addCell(res);
				ws.addCell(b);
				WriteLog.writeComLog("taost验证不通过，界面没有出现-"+test.getExvalue());
				
			}
			
		}
			
		}catch(NullPointerException e)
			{
			System.out.println("taost验证不通过，界面没有出现-"+test.getExvalue());
				WriteLog.writeComLog("执行第["+i+"]行"+"发现空值请检查");
			}
	return false;
	
	}
	
}
