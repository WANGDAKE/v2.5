package com.test.robotium.bean;


public class TestcaseBean {
	public String testcaseID;//用例编号
	public String expert;//期望标识
	public String module;//模块
	public String identifying;//对象别名
	public String findaction;//查找对象方法
	public String property;//对象属性
	public String delaytime;//等待时间
	public String action;//执行动作
	public String value;
	public String value1;
	public String value2;
	public String value3;
	public String exvalue;//期望值
	public String exist;//期望对比yes or no
	public String tryjump;//异常跳转
	public String autojump;//自动跳转
	public String casecover;//用例套
	public String time;
	public String result;
	public TestcaseBean(String testcaseID, String expert, String module,
			String identifying, String findaction, String property,
			String delaytime, String action, String value, String value1,
			String value2, String value3, String exvalue, String exist,
			String tryjump, String autojump, String casecover, String time,
			String result) {
		super();
		this.testcaseID = testcaseID;
		this.expert = expert;
		this.module = module;
		this.identifying = identifying;
		this.findaction = findaction;
		this.property = property;
		this.delaytime = delaytime;
		this.action = action;
		this.value = value;
		this.value1 = value1;
		this.value2 = value2;
		this.value3 = value3;
		this.exvalue = exvalue;
		this.exist = exist;
		this.tryjump = tryjump;
		this.autojump = autojump;
		this.casecover = casecover;
		this.time = time;
		this.result = result;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getTestcaseID() {
		return testcaseID;
	}
	public void setTestcaseID(String testcaseID) {
		this.testcaseID = testcaseID;
	}
	public String getExpert() {
		return expert;
	}
	public void setExpert(String expert) {
		this.expert = expert;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getIdentifying() {
		return identifying;
	}
	public void setIdentifying(String identifying) {
		this.identifying = identifying;
	}
	public String getFindaction() {
		return findaction;
	}
	public void setFindaction(String findaction) {
		this.findaction = findaction;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public String getDelaytime() {
		return delaytime;
	}
	public void setDelaytime(String delaytime) {
		this.delaytime = delaytime;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getValue1() {
		return value1;
	}
	public void setValue1(String value1) {
		this.value1 = value1;
	}
	public String getValue2() {
		return value2;
	}
	public void setValue2(String value2) {
		this.value2 = value2;
	}
	public String getValue3() {
		return value3;
	}
	public void setValue3(String value3) {
		this.value3 = value3;
	}
	public String getExvalue() {
		return exvalue;
	}
	public void setExvalue(String exvalue) {
		this.exvalue = exvalue;
	}
	public String getExist() {
		return exist;
	}
	public void setExist(String exist) {
		this.exist = exist;
	}
	public String getTryjump() {
		return tryjump;
	}
	public void setTryjump(String tryjump) {
		this.tryjump = tryjump;
	}
	public String getAutojump() {
		return autojump;
	}
	public void setAutojump(String autojump) {
		this.autojump = autojump;
	}
	public String getCasecover() {
		return casecover;
	}
	public void setCasecover(String casecover) {
		this.casecover = casecover;
	}
	@Override
	public String toString() {
		return "TestcaseBean [testcaseID=" + testcaseID + ", expert=" + expert
				+ ", module=" + module + ", identifying=" + identifying
				+ ", findaction=" + findaction + ", property=" + property
				+ ", delaytime=" + delaytime + ", action=" + action
				+ ", value=" + value + ", value1=" + value1 + ", value2="
				+ value2 + ", value3=" + value3 + ", exvalue=" + exvalue
				+ ", exist=" + exist + ", tryjump=" + tryjump + ", autojump="
				+ autojump + ", casecover=" + casecover + ",]";
	}
	public TestcaseBean(String testcaseID, String expert, String module,
			String identifying, String findaction, String property,
			String delaytime, String action, String value, String value1,
			String value2, String value3, String exvalue, String exist,
			String tryjump, String autojump, String casecover) {
		super();
		this.testcaseID = testcaseID;
		this.expert = expert;
		this.module = module;
		this.identifying = identifying;
		this.findaction = findaction;
		this.property = property;
		this.delaytime = delaytime;
		this.action = action;
		this.value = value;
		this.value1 = value1;
		this.value2 = value2;
		this.value3 = value3;
		this.exvalue = exvalue;
		this.exist = exist;
		this.tryjump = tryjump;
		this.autojump = autojump;
		this.casecover = casecover;
	}
	public TestcaseBean() {
		super();
	}

}
