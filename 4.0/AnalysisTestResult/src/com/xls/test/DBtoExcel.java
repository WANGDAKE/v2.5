package com.xls.test;

import java.io.FileOutputStream;
import java.io.OutputStream;

import jxl.*; 
import jxl.write.*;
import java.sql.*;
public class DBtoExcel {
	
	public static String getExcel(String filePath,String filePath1){
		/**
		 * 导出Excel表
		 * @param rs 数据库结果集
		 * @param filePath 要保存的路径，文件名为 fileName.xls
		 * @param sheetName 工作簿名称 工作簿名称，本方法目前只支持导出一个Excel工作簿
		 * @param columnName 列名，类型为Vector
		 */

			String Switch = "Switch";
			String Sheetname = "sheetname";
			String Sheetname1 = "sheetname1";
			String usecasename = "测试用例";
			String usecasename1 = "testcasemenu";
			String casename = "Excelname";
			String casename1 = "Excelname1";
			String n= "导入成功";
			Connection con = null;
			
			String Casename = Configuration.readConfigString(casename, Switch);
			String Casename1 = Configuration.readConfigString(casename1, Switch);
			System.out.println(Casename);
			
//----------用例导出--------
				System.out.println(Casename);
				String[] title={"用例","执行","期望标识","模块","对象别名","查找对象方式","对象属性","延时",
							"动作","值1","值2","值3","值4","期望值","期望对比","异常跳转","用例编号","用例步骤","ID"};
				try {
					//String filePath = "F:\\数据导出.xls";
					OutputStream os = new FileOutputStream(filePath);
					WritableWorkbook wwb = Workbook.createWorkbook(os);
					// 添加第一个工作表并设置第一个Sheet的名字
					WritableSheet sheet = wwb.createSheet("ceshiyongli", 0);
					Label label = null;
					for (int i = 0; i < title.length; i++) {
						// Label(x,y,z)其中x代表单元格的第x+1列，第y+1行, 单元格的内容是y
						// 在Label对象的子对象中指明单元格的位置和内容 
						label = new Label(i, 0, title[i]);
						sheet.addCell(label);
					}
					con = JDBCUtil.getConnection();
					Statement stmt = con.createStatement();
					//查询表中的所有数据
					//String sql="select * from sheet1";
//----------数据库中 目标表格--------					
					String sql= Configuration.readConfigString(Sheetname, Switch);
					ResultSet result = stmt.executeQuery(sql);
					int x = 0;
					while (result.next()) { // 从数据库中取得所有数据
						x++; // 控制行数
						for (int y = 0; y < title.length; y++) {// 获得列数.getString(y + 1))
							label = new Label(y, x, result.getString(y + 1));
							//X代表行，Y代表列，通过rst.getString()设置单元格内容
							sheet.addCell(label);// 将内容加到excel表中
						}
					}
					JDBCUtil.close(result, stmt, con);
					wwb.write(); 
					wwb.close(); 
					System.out.println("导出成功!");
				} catch (Exception e) {
					e.printStackTrace();
				}//准备设置excel工作表的标题
			
			
//----------			
/*				String[] title1={"路径目录","测试用例集合名称","测试结果"};
				try {
					//String filePath = "F:\\数据导出.xls";
					OutputStream os = new FileOutputStream(filePath1);
					WritableWorkbook wwb = Workbook.createWorkbook(os);
					// 添加第一个工作表并设置第一个Sheet的名字
					WritableSheet sheet = wwb.createSheet("mulu", 0);
					Label label = null;
					for (int i = 0; i < title1.length; i++) {
						// Label(x,y,z)其中x代表单元格的第x+1列，第y+1行, 单元格的内容是y
						// 在Label对象的子对象中指明单元格的位置和内容
						label = new Label(i, 0, title1[i]);
						sheet.addCell(label);
					}
					con = JDBCUtil.getConnection();
					Statement stmt = con.createStatement();
					//查询表中的所有数据
					//String sql="select * from sheet1";
					String sql= Configuration.readConfigString(Sheetname1, Switch);
					ResultSet result = stmt.executeQuery(sql);
					int x = 0;
					while (result.next()) { // 从数据库中取得所有数据
						x++; // 控制行数
						
						for (int y = 0; y < title1.length; y++) {// 获得列数.getString(y + 1))
							label = new Label(y, x, result.getString(y + 1));
							//X代表行，Y代表列，通过rst.getString()设置单元格内容
							sheet.addCell(label);// 将内容加到excel表中
						}
					}
					JDBCUtil.close(result, stmt, con);
					wwb.write(); 
					wwb.close(); 
					System.out.println("导入成功!");
				} catch (Exception e) {
					e.printStackTrace();
				}
			*/
		return n;
	}
	
		public static void main(String[] agrs){
			String FilePath = "Path";
			String FilePath1 = "Path1";
			String Switch = "Switch";
			
			String filePath = Configuration.readConfigString(FilePath, Switch);
			String filePath1 = Configuration.readConfigString(FilePath1, Switch);
			
			System.out.println("数据导出结果：" + getExcel(filePath,filePath1));
		}
	
}
