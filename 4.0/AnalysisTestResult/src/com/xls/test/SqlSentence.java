package com.xls.test;
public class SqlSentence {
	
	static String Sheetname = "sheetname";
	static String Sheetname1 = "sheetname1";
	static String Sheetname2 = "sheetname2";
	static String Sheetname3 = "sheetname3";
	static String filename = "Switch";
	
	static String createtable = "CREATE TABLE "+ Configuration.readConfigString(Sheetname, filename) 
			+"(Company varchar(255), Usecasename varchar(255),ID int NOT NULL AUTO_INCREMENT, PRIMARY KEY (`ID`))";
	
	/*static String createtable1 = "CREATE TABLE "+ Configuration.readConfigString(Sheetname1, filename) 
					+"(��������  varchar(255),���Խ�� varchar(255) NOT NULL,����·�� varchar(255),"+"ID int)";*/
	
	static String createtable1 = "CREATE TABLE work1" 
			+"(ceshijieguo varchar(255), ceshimingcheng varchar(255) NOT NULL,ceshilujing varchar(255),"
			+"ID int NOT NULL AUTO_INCREMENT, PRIMARY KEY (`ID`))";
	
	static String createtable2 = "CREATE TABLE work2"
			+"(no varchar(255),number varchar(255),expert varchar(255),module varchar(255),identifying varchar(255),"
			+"findaction varchar(255),property varchar(255),delaytime varchar(555),action1 varchar(255),"
			+"value1 varchar(255),value2 varchar(255),value3 varchar(255),value4 varchar(255),exvalue varchar(255),"
			+"exist varchar(255),Goto varchar(255),NumOfUsecase varchar(255),StepOfUsecase varchar(255),a varchar(255)," +
			"b varchar(255),c varchar(255),d varchar(255), e varchar(255),f varchar(255),ID int)";
	
	static String createtble3 = "CREATE TABLE work3"
			+ "(bianhao varchar(255), yonglibianhao varchar(255), BUGzhuti varchar(255), ceshibuzhou varchar(255)," 
			+ "ceshibanben varchar(255), ceshishijian varchar(255), ID int)";
	
	static String alert1 = "alter table work1"+" add foreign key "
			+ Configuration.readConfigString(Sheetname1, filename)+"_ibfk2 (ID) references "
			+ Configuration.readConfigString(Sheetname, filename) +"(ID)";
	
	static String alert2 = "alter table work2"+" add foreign key work2"
			+"_ibfk2 (ID) references work1"
			+ "(ID)";
	
	static String alert3 = "alter table work3" +" add foreign key work3"
			+ "_ibfk2 (ID) references work1"
			 +"(ID)";
	
	static String alert4 = "alter table work3"+" add foreign key work3"
			+ "_ibfk3 (ID) references work2"
			+"(ID)";
	
	static String insert = "insert into work"  +
			 " (Company,Usecasename) values (?,?)";
	
	static String insert1 = "insert into work1"  +
			 " (ceshijieguo,ceshimingcheng,ceshilujing) values (?,?,?)";
	
	static String insert2 = "insert into work2"  +
			 " (no,number,expert,module,identifying,findaction,property,delaytime,action1,value1,value2,value3,"
			+"value4,exvalue,exist,Goto,NumOfUsecase,StepOfUsecase,a,b,c,d,e,f,ID) " +
			 "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	static String insert3 = "insert into work3" +
			 " (bianhao,yonglibianhao,BUGzhuti,ceshibuzhou,ceshibanben,ceshishijian,ID) values (?,?,?,?,?,?,?)";
	
	
}
