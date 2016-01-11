package com.xls.test;

import java.io.FileOutputStream;
import java.io.OutputStream;

import jxl.*; 
import jxl.write.*;
import java.sql.*;
public class DBtoExcel {
	
	public static String getExcel(String filePath,String filePath1){
		/**
		 * ����Excel��
		 * @param rs ���ݿ�����
		 * @param filePath Ҫ�����·�����ļ���Ϊ fileName.xls
		 * @param sheetName ���������� ���������ƣ�������Ŀǰֻ֧�ֵ���һ��Excel������
		 * @param columnName ����������ΪVector
		 */

			String Switch = "Switch";
			String Sheetname = "sheetname";
			String Sheetname1 = "sheetname1";
			String usecasename = "��������";
			String usecasename1 = "testcasemenu";
			String casename = "Excelname";
			String casename1 = "Excelname1";
			String n= "����ɹ�";
			Connection con = null;
			
			String Casename = Configuration.readConfigString(casename, Switch);
			String Casename1 = Configuration.readConfigString(casename1, Switch);
			System.out.println(Casename);
			
//----------��������--------
				System.out.println(Casename);
				String[] title={"����","ִ��","������ʶ","ģ��","�������","���Ҷ���ʽ","��������","��ʱ",
							"����","ֵ1","ֵ2","ֵ3","ֵ4","����ֵ","�����Ա�","�쳣��ת","�������","��������","ID"};
				try {
					//String filePath = "F:\\���ݵ���.xls";
					OutputStream os = new FileOutputStream(filePath);
					WritableWorkbook wwb = Workbook.createWorkbook(os);
					// ��ӵ�һ�����������õ�һ��Sheet������
					WritableSheet sheet = wwb.createSheet("ceshiyongli", 0);
					Label label = null;
					for (int i = 0; i < title.length; i++) {
						// Label(x,y,z)����x����Ԫ��ĵ�x+1�У���y+1��, ��Ԫ���������y
						// ��Label������Ӷ�����ָ����Ԫ���λ�ú����� 
						label = new Label(i, 0, title[i]);
						sheet.addCell(label);
					}
					con = JDBCUtil.getConnection();
					Statement stmt = con.createStatement();
					//��ѯ���е���������
					//String sql="select * from sheet1";
//----------���ݿ��� Ŀ����--------					
					String sql= Configuration.readConfigString(Sheetname, Switch);
					ResultSet result = stmt.executeQuery(sql);
					int x = 0;
					while (result.next()) { // �����ݿ���ȡ����������
						x++; // ��������
						for (int y = 0; y < title.length; y++) {// �������.getString(y + 1))
							label = new Label(y, x, result.getString(y + 1));
							//X�����У�Y�����У�ͨ��rst.getString()���õ�Ԫ������
							sheet.addCell(label);// �����ݼӵ�excel����
						}
					}
					JDBCUtil.close(result, stmt, con);
					wwb.write(); 
					wwb.close(); 
					System.out.println("�����ɹ�!");
				} catch (Exception e) {
					e.printStackTrace();
				}//׼������excel������ı���
			
			
//----------			
/*				String[] title1={"·��Ŀ¼","����������������","���Խ��"};
				try {
					//String filePath = "F:\\���ݵ���.xls";
					OutputStream os = new FileOutputStream(filePath1);
					WritableWorkbook wwb = Workbook.createWorkbook(os);
					// ��ӵ�һ�����������õ�һ��Sheet������
					WritableSheet sheet = wwb.createSheet("mulu", 0);
					Label label = null;
					for (int i = 0; i < title1.length; i++) {
						// Label(x,y,z)����x����Ԫ��ĵ�x+1�У���y+1��, ��Ԫ���������y
						// ��Label������Ӷ�����ָ����Ԫ���λ�ú�����
						label = new Label(i, 0, title1[i]);
						sheet.addCell(label);
					}
					con = JDBCUtil.getConnection();
					Statement stmt = con.createStatement();
					//��ѯ���е���������
					//String sql="select * from sheet1";
					String sql= Configuration.readConfigString(Sheetname1, Switch);
					ResultSet result = stmt.executeQuery(sql);
					int x = 0;
					while (result.next()) { // �����ݿ���ȡ����������
						x++; // ��������
						
						for (int y = 0; y < title1.length; y++) {// �������.getString(y + 1))
							label = new Label(y, x, result.getString(y + 1));
							//X�����У�Y�����У�ͨ��rst.getString()���õ�Ԫ������
							sheet.addCell(label);// �����ݼӵ�excel����
						}
					}
					JDBCUtil.close(result, stmt, con);
					wwb.write(); 
					wwb.close(); 
					System.out.println("����ɹ�!");
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
			
			System.out.println("���ݵ��������" + getExcel(filePath,filePath1));
		}
	
}
