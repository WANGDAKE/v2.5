package com.xls.test;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
//-------------------用例入库------------------
	/**Dictionary_Geology -- 表名称 
	*数据库第一个字段id--自增长，不用处理
	*chinese -- 数据库第二个字段 
	*english -- 数据库第三个字段 
	*content -- 数据库第四个字段 
	在数据库中创建sheet **/

public class ExcelOperate {

	public void excelOperate(String testcase,String lujing) throws Exception {
		//-----设置开始时间以及时间格式----
		String lj = lujing.substring(0,lujing.lastIndexOf("\\"));
		String caseName1 = lj+File.separator+"自动化测试结果BUG.xls";
		String caseName =lujing; 
		String caseMenu = testcase;
		
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss:SS"); 
		TimeZone t = sdf.getTimeZone(); 
		t.setRawOffset(0); 
		sdf.setTimeZone(t); 
		Long startTime = System.currentTimeMillis(); 
		
		String SQLname = "sqlname";
		String filename = "Switch";
		String Sheetname1 = "sheetname1";
		String Sheetname2 = "sheetname2";
		String Sheetname3 = "sheetname3";
		
		//--------创建数据库表格--------
		String Createtable = SqlSentence.createtable;
		String Createtable1 = SqlSentence.createtable1;
		String Createtable12 = SqlSentence.createtable2;
		String Createtable13 = SqlSentence.createtble3;
		
	//	String Alert1 = SqlSentence.alert1;
		String Alert12 = SqlSentence.alert2;
		String Alert13 = SqlSentence.alert3;
		String Alert14 = SqlSentence.alert4;
		
		//--------向sql中插入数据------
		String Insert = SqlSentence.insert;
		String Insert1 = SqlSentence.insert1;
		String Insert12= SqlSentence.insert2;
		String Insert13= SqlSentence.insert3;
		
		
//		System.out.println("Createtable1 " + Createtable1);
//		System.out.println("Createtable12 " + Createtable12);
//		System.out.println("Insert1 " + Insert1);
//		System.out.println("Insert12 " + Insert12);
//		System.out.println("Insert13 " + Insert13);
//		
//------连接数据库-------
		Connection conn = null;
		PreparedStatement pst = null;
		PreparedStatement pst1 = null;
		PreparedStatement pst2 = null;
		PreparedStatement pst3 = null;
		int autoInckey = -1;
	//	int autoInckey1 = -1;
		
 		int NumOfUsecase = 0;
		try {
			final String DRIVER="com.mysql.jdbc.Driver";
			final String URL="jdbc:mysql://localhost:3306/xxxx";
			final String USER="root";
			final String PWD="jenkins";
			Class.forName(DRIVER);
			conn=DriverManager.getConnection(URL, USER, PWD);
			conn.setAutoCommit(false); 
			Statement st=conn.createStatement();

			
//--------------------------------testcasemenu导入-----------------------
//		  st.executeUpdate(Createtable1);
//			st.executeUpdate(Alert1);
			pst1 = (PreparedStatement) conn.prepareStatement(Insert1,Statement.RETURN_GENERATED_KEYS); 
			File file1 = new File(caseMenu);
			String[][] result1 = getData(file1,1);
			int rowLength1 = result1.length;
			for (int i = 0; i < rowLength1; i++) {
						pst1.setString(1, result1[i][0]);
						pst1.setString(2, result1[i][1]);
						pst1.setString(3, result1[i][2]);
						pst1.executeUpdate(); 
					//	pst1.addBatch();
						ResultSet rs = pst1.getGeneratedKeys(); 
						if (rs.next()) {
							 autoInckey = rs.getInt(1);//取得ID
							} else {
							}
			}
			pst1.executeBatch(); 
			conn.setAutoCommit(true); 
			System.out.println(Configuration.readConfigString(Sheetname1, filename)+"数据库写入成功"); 
			
			
//------------------------------------测试用例导入-------------------------------------			
//			st.executeUpdate(Createtable12);
//			st.executeUpdate(Alert12);
			//pst2 = (PreparedStatement) conn.prepareStatement(Insert12);
			pst2 = (PreparedStatement) conn.prepareStatement(Insert12,Statement.RETURN_GENERATED_KEYS); 
			File file2 = new File(caseName);
			String[][] result2 = getData(file2,1);
			System.out.println("result2 =" + result2);
	//		int StepOfUsecase = 0;
			int rowLength2 = result2.length;
			for (int i = 0; i < rowLength2; i++) {
						pst2.setString(1, result2[i][0]);
						/*if(result2[i][0]!=""){
							int g = 1;
							NumOfUsecase = g;
							StepOfUsecase ++;
						}*/
//						System.out.println(result2[i][0]);
						pst2.setString(2, result2[i][1]);
						pst2.setString(3, result2[i][2]);
						pst2.setString(4, result2[i][3]);
						pst2.setString(5, result2[i][4]);
						pst2.setString(6, result2[i][5]);
						pst2.setString(7, result2[i][6]);
						pst2.setString(8, result2[i][7]);
						pst2.setString(9, result2[i][8]);
						pst2.setString(10, result2[i][9]);
						pst2.setString(11, result2[i][10]);
						pst2.setString(12, result2[i][11]);
						pst2.setString(13, result2[i][12]);
						pst2.setString(14, result2[i][13]);
						pst2.setString(15, result2[i][14]);
						pst2.setString(16, result2[i][15]);
						pst2.setString(17, result2[i][16]);
						pst2.setString(18, result2[i][17]);
						pst2.setString(19, result2[i][18]);
						pst2.setString(20, result2[i][19]);
						pst2.setString(21, result2[i][20]);
						pst2.setString(22, result2[i][21]);
						pst2.setString(23, result2[i][22]);
						pst2.setString(24, result2[i][23]);
						pst2.setString(25, String.valueOf(autoInckey));
						/*ResultSet rs1 = pst2.getGeneratedKeys(); 
						if (rs1.next()) {
							 autoInckey1 = rs1.getInt(1);//取得ID
							} else {
							}*/
						pst2.executeUpdate(); 
					//	StepOfUsecase++;
			}
			pst2.executeBatch(); 
			conn.setAutoCommit(true); 
			System.out.println(Configuration.readConfigString(Sheetname2, filename)+"数据库写入成功");
			
			
	//------------------bug入库--------------------
			
//			st.executeUpdate(Createtable13);
//			st.executeUpdate(Alert13);
//			st.executeUpdate(Alert14);
			pst3 = (PreparedStatement) conn.prepareStatement(Insert13);
			File file3 = new File(caseName1);
			String[][] result3 = getData(file3,1);
	//		int StepOfUsecase = 0;
			int rowLength3 = result3.length;
			for (int i = 0; i < rowLength3; i++) {
						pst3.setString(1, result3[i][0]);
						pst3.setString(2, result3[i][1]);
						pst3.setString(3, result3[i][2]);
						pst3.setString(4, result3[i][3]);
						pst3.setString(5, result3[i][4]);
						pst3.setString(6, result3[i][5]);
						pst3.setString(7, String.valueOf(autoInckey)); 
					//	pst2.addBatch();
					//	pst3.setString(8, String.valueOf(autoInckey1));
						pst3.executeUpdate(); 
					//	StepOfUsecase++;
			}
			pst2.executeBatch(); 
			conn.setAutoCommit(true); 
			System.out.println(Configuration.readConfigString(Sheetname3, filename)+"数据库写入成功");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("数据库连接失败");
		} catch (SQLException e) {
			e.printStackTrace();
		}  finally{
			//关闭PreparedStatement
			if(pst != null) {
				pst.close();
				pst = null;
			}
			//关闭Connection
			if(conn != null) {
				conn.close();
				pst = null;
			}
		}
		
		//-----结束时间------
		Long endTime = System.currentTimeMillis(); 
		System.out.println("用时：" + sdf.format(new Date(endTime - startTime))); 	
	}

	
	@SuppressWarnings("deprecation")
	public static String[][] getData(File file, int ignoreRows) throws FileNotFoundException, IOException {
		List<String[]> result = new ArrayList<String[]>();
		int rowSize = 0;
//		System.out.println("-----------"+file);
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(
				file));
		// 打开HSSFWorkbook
		POIFSFileSystem fs = new POIFSFileSystem(in);
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		HSSFCell cell = null;
//获取整个excel的行数
		for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
				
			HSSFSheet st = wb.getSheetAt(sheetIndex);
			System.out.println( "st.getLastRowNum = " +st.getLastRowNum() );
			// 第一行为标题，不取
			for (int rowIndex = ignoreRows; rowIndex <= st.getLastRowNum(); rowIndex++) {
				HSSFRow row = st.getRow(rowIndex);
				if (row == null) {
					continue;
				}
				int tempRowSize = row.getLastCellNum() + 1;
				
				if (tempRowSize > rowSize) {
					rowSize = tempRowSize;
				}
				String[] values = new String[rowSize];
				Arrays.fill(values, "");
				boolean hasValue = false;
				for (short columnIndex = 0; columnIndex <= row.getLastCellNum(); columnIndex++) {
					String value = "";
					cell = row.getCell(columnIndex);
					if (cell != null) {
						//cell.setEncoding(HSSFCell.ENCODING_UTF_16);
						switch (cell.getCellType()) {
						case HSSFCell.CELL_TYPE_STRING:
							value = cell.getStringCellValue();
							break;
						case HSSFCell.CELL_TYPE_NUMERIC:
							if (HSSFDateUtil.isCellDateFormatted(cell)) {
								Date date = cell.getDateCellValue();
								if (date != null) {
									value = new SimpleDateFormat("yyyy-MM-dd")
											.format(date);
								} else {
									value = "";
								}
							} else {
								value = new DecimalFormat("0").format(cell
										.getNumericCellValue());
							}
							break;
						case HSSFCell.CELL_TYPE_FORMULA:
//--------------- 导入时如果为公式生成的数据则无值------------------
							if (!cell.getStringCellValue().equals("")) {
								value = cell.getStringCellValue();
							} else {
								value = cell.getNumericCellValue() + "";
							}
							break;
						case HSSFCell.CELL_TYPE_BLANK:
							break;
						case HSSFCell.CELL_TYPE_ERROR:
							value = "";
							break;
						case HSSFCell.CELL_TYPE_BOOLEAN:
							value = (cell.getBooleanCellValue() == true ? "Y"
									: "N");
							break;
						default:
							value = "";
						}
					}
					
//-------					
					/*if (columnIndex == 0 && value.trim().equals("")) {
						break;
					}*/
					values[columnIndex] = rightTrim(value);
					hasValue = true;
				}

				if (hasValue) {
					result.add(values);
				}
			}
		}
		in.close();
		String[][] returnArray = new String[result.size()][rowSize];
		for (int i = 0; i < returnArray.length; i++) {
			returnArray[i] = (String[]) result.get(i);
		}
		
		return returnArray;
	}

	/**
	 * 去掉字符串右边的空格
	 * 
	 * @param str
	 *            要处理的字符串
	 * @return 处理后的字符串
	 */
	public static String rightTrim(String str) {
		if (str == null) {
			return "";
		}
		int length = str.length();
		for (int i = length - 1; i >= 0; i--) {
			if (str.charAt(i) != 0x20) {
				break;
			}
			length--;
		}
		return str.substring(0, length);
	}
}