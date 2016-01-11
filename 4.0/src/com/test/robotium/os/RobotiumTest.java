package com.test.robotium.os;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import junit.framework.AssertionFailedError;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import android.annotation.SuppressLint;
import android.app.Instrumentation;
import android.os.Environment;
import android.test.ActivityInstrumentationTestCase2;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.SlidingDrawer;
import android.widget.TextView;

import com.robotium.solo.By;
import com.robotium.solo.Solo;
import com.robotium.solo.WebElement;
import com.test.ios.CaseCover;
import com.test.ios.Config;
import com.test.ios.DoExpert;
import com.test.ios.Dynamic;
import com.test.ios.Jump;
import com.test.ios.ScreenShot;
import com.test.ios.WriteLog;
import com.test.robotium.bean.TestcaseBean;


@SuppressLint("SdCardPath")
public class RobotiumTest extends ActivityInstrumentationTestCase2 {
	public Solo solo;	
	public static Class launcherActivityClass;
	public static final String PackageName = "com.huika.o2o.android.xmdd.debug";
	public static final String Lanch_activity_full_className = "com.huika.o2o.android.XMDDLaunch";
	public static final String Reset_activity="PersonalCenterActivity";
	public String path=Environment.getExternalStorageDirectory().getPath()+"/错误步骤/";
	static {
		try {
			launcherActivityClass = Class.forName(Lanch_activity_full_className);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public RobotiumTest() throws Exception {
		super(PackageName, launcherActivityClass);
		
		// TODO Auto-generated constructor stub
	}

	protected void setUp() throws Exception {
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
	}
	
	

	@SuppressWarnings({ "unused", "deprecation" })
	public void testApp() throws Exception {
		

		/******* 读取用例 *******/
		InputStream input;
		TestcaseBean testcase;
		CaseCover casecover = new CaseCover();
		DoExpert ex = new DoExpert();
		Jump jump = new Jump();
		int times = 1;// 是否需要进入跳转
		int n = 0;// 普通滑动循环
		String [] bookmoney=null;
		String bookyuedian=null;
		boolean boole;
		Boolean Screen = null;// 期望判断
		String message = null;
		Label error = null;
		Label b = null;
		boole = casecover.caseCover();
		ScreenShot sshot=new ScreenShot();
		Dynamic dt = new Dynamic();
		input = new FileInputStream(Config.excelPath);
		Workbook book = null;
		Cell cell = null;
		book = Workbook.getWorkbook(input);
		WritableWorkbook wbe = Workbook.createWorkbook(new File(Config.excelPath), book);
		Sheet sheet = book.getSheet(0);
		WritableSheet ws = wbe.getSheet(0);
		
		
		a: for (int i = 1; i < sheet.getRows(); i++) {
			
			String[] str = new String[sheet.getColumns()];
			for (int j = 0; j < sheet.getColumns(); j++) {
				cell = sheet.getCell(j, i);
				str[j] = cell.getContents();
			}
			testcase = new TestcaseBean(str[0], str[1], str[2], str[3], str[4],str[5], str[6], str[7], str[8], str[9], str[10], str[11],str[12], str[13], str[14], str[15], str[16]);

			/**** 判断是否有用例标题 有进入用例套自动跳转判断 ****/
			if (!testcase.getTestcaseID().equals("")) {
				dt.clean();
//				if (boole) {
//					i = casecover.deterMine();
//					WriteLog.writeComLog("执行用例套或自动跳转--用例跳转指" + i + "行");
//					i = i - 1;
//					if (i == -1) {
//						WriteLog.writeComLog("所有用例套或自动跳转执行完毕停止自动化测试");
//						tearDown();
//						break a;
//					}
//					testcase = null;
//					times = 0;
//					continue a;
//				}
			}
			// if(!testcase.getTime().equals("")){
			// solo.sleep(Integer.valueOf(testcase.getTime())*1000);
			// }
			/******* 操作 *****/
			
				Date start = new Date();
				SimpleDateFormat dateformat = new SimpleDateFormat("yyyy_MM_dd HH:mm:ss:SS");
				String starttime = dateformat.format(start);
				if(!testcase.getDelaytime().equals(""))
				{
					Thread.sleep(Integer.parseInt(testcase.getDelaytime())*1000);
//					TimeUnit.SECONDS.sleep(Integer.parseInt(testcase.getDelaytime()));
				}else {
					Thread.sleep(5000);
				}
				try {	
					if (testcase.getFindaction().equals("ID-text")) {
						if (testcase.getAction().equals("getText")) {
							TextView textView = (TextView) solo.getView(Integer.valueOf(testcase.getProperty()));							
							message = textView.getText().toString();
						} else if (testcase.getAction().equals("setText")) {
							EditText textView = (EditText) solo.getView(Integer.valueOf(testcase.getProperty()));
							solo.enterText(textView, testcase.getValue());
						}
					} else if (testcase.getFindaction().equals("ID-viewElt")) {
						View view = (View) solo.getView(Integer.valueOf(testcase.getProperty()));
						if (testcase.getAction().equals("doClick")) {
							solo.clickOnView(view);
						} else if (testcase.getAction().equals("longClick")) {
							solo.clickLongOnView(view,Integer.valueOf(testcase.getValue()));
						}
					} else if (testcase.getFindaction().equals("ID-viewElt-index")) {
						View view = (View) solo.getView(Integer.valueOf(testcase.getProperty()),Integer.valueOf(testcase.getValue()));
						if (testcase.getAction().equals("doClick")) {
							solo.clickOnView(view);							
						}
					} else if (testcase.getFindaction().equals("ID-text-index")) {
						if (testcase.getAction().equals("getText")) {
							TextView textView = (TextView) solo.getView(Integer.valueOf(testcase.getProperty()),Integer.valueOf(testcase.getValue()));
							solo.getView(Integer.valueOf(testcase.getProperty())).getBaseline();
							message = textView.getText().toString();
							
						}
					} else if (testcase.getFindaction().equals("xpath-webview")) {
//						solo.waitForWebElement(By.xpath(testcase.getProperty()));
						if (testcase.getAction().equals("doClick")) {
							solo.clickOnWebElement(By.xpath(testcase.getProperty()), 0);
						}

					} else if (testcase.getFindaction().equals("xpath-webtext")) {
//						solo.waitForWebElement(By.xpath(testcase.getProperty()));
						if (testcase.getAction().equals("getText")) {
							WebElement WebElement = solo.getWebElement(By.xpath(testcase.getProperty()), 0);
							message = WebElement.getText();
						}
					} else if (testcase.getFindaction().equals("id-webtext")) {
//						solo.waitForWebElement(By.xpath(testcase.getProperty()));
						if (testcase.getAction().equals("getText")) {
							WebElement WebElement = solo.getWebElement(By.id(testcase.getProperty()), Integer.valueOf(testcase.getValue()));
							message = WebElement.getText();
						}
					}else if (testcase.getFindaction().equals("id-webview")) {
//						solo.waitForWebElement(By.xpath(testcase.getProperty()));
						if (testcase.getAction().equals("doClick")) {
							solo.clickOnWebElement(By.id(testcase.getProperty()), Integer.valueOf(testcase.getValue()));
						}

					}else if (testcase.getFindaction().equals("taost")) {
						ex.doExpert_taost(testcase,i, b, ws);
//						
					} else if (testcase.getFindaction().equals("文本-字段")) {
						solo.waitForText(testcase.getProperty());
						solo.clickOnText(testcase.getProperty());
						
					} else if (testcase.getFindaction().equals("物理键-返回")) {
						solo.goBack();
					} else if (testcase.getFindaction().equals("物理键-home")) {
//						 solo.sendKey(solo.);
						
					} else if (testcase.getFindaction().equals("物理键-菜单")) {
						solo.sendKey(Solo.MENU);
					} else if (testcase.getFindaction().equals("物理键-往左")) {
						solo.sendKey(Solo.LEFT);
					} else if (testcase.getFindaction().equals("物理键-往右")) {
						 solo.sendKey(solo.RIGHT);
					} else if (testcase.getFindaction().equals("物理键-往上")) {
						 solo.sendKey(solo.UP);
					} else if (testcase.getFindaction().equals("物理键-往下")) {
						 solo.sendKey(solo.DOWN);
					} else if (testcase.getFindaction().equals("物理键-键盘输入")) {
						sendKeys(Integer.parseInt(testcase.getValue()));
					}else if (testcase.getFindaction().equals("开始程序")) {
						setUp();
						System.out.println("开始程序");
					} else if (testcase.getFindaction().equals("退出程序")) {
						tearDown();
						System.out.println("程序已退出。");
					}else if (testcase.getFindaction().equals("重置")) {
						solo.goBackToActivity(Reset_activity);
					}else if (testcase.getFindaction().equals("坐标画图")) {
						solo.drag(Integer.valueOf(testcase.getValue()), Integer.valueOf(testcase.getValue1()), Integer.valueOf(testcase.getValue2()), Integer.valueOf(testcase.getValue3()), 1);
					}
					
					
					if (testcase.getExpert().equals("期望")&& testcase.getExist().equals("yes")) {
						System.out.println("message的值是--"+message);
						Screen = ex.doExpert_yes(testcase, message, i, b, ws);
						/*** 期望截图 ***/
						// if (Config.ScreenShot == 0 || !Screen) {
						// ScreenShot.takeScreenShot(getDevice()
						// .getCurrentActivity(), Config.resultPath + "/"
						// + (i + 1) + ".png");
						// }
					} else if (testcase.getExpert().equals("期望")&& testcase.getExist().equals("in")) {
						System.out.println("doExpert_in_message的值是--"+message);
						Screen = ex.doExpert_in(testcase, message, i, b, ws);
					} else if (testcase.getExpert().equals("动态")&& testcase.getExist().equals("yes")) {
						System.out.println("compare_yes_message的值是--"+message);
						dt.compare_yes(message, i, b, ws);
					} else if (testcase.getExpert().equals("动态")&& testcase.getExist().equals("in")) {
						System.out.println("compare_in_message的值是--"+message);
						dt.compare_in(message, i, b, ws);
					} else if (testcase.getExpert().equals("动态")&& testcase.getExist().equals("")) {
						System.out.println("add_message的值是--"+message);
						dt.add(message, i, b, ws);
					} else if (testcase.getExpert().equals("动态")&& testcase.getExist().equals("no")) {
						System.out.println("compare_no_message的值是--"+message);
						dt.compare_no(message, i, b, ws);
					}else if (testcase.getExpert().equals("期望")&& testcase.getExist().equals("all")) {
						boolean result=false;
							if(solo.waitForText(testcase.getExvalue())){
								result=true;
							}else{
								result=false;
						}
						ex.doExpert_all(result,testcase, i, b, ws);
					}else if (testcase.getExpert().equals("动态")&& testcase.getExist().equals("all")) {
						boolean result=false;
						String datevalue=null;
						for(int u=0;u<dt.dates.size();u++)
						{
							if(solo.waitForText(dt.dates.get(u))){
								result=true;
								datevalue=dt.dates.get(u);
								u=dt.dates.size();
							}else{
								result=false;
							}
						}
						dt.compare_all(result,datevalue,i, b, ws);
					}else if (testcase.getExpert().equals("动态")&& testcase.getExist().equals("清除动态数据")) {
						dt.clean();
					}else if (testcase.getExpert().equals("书本阅点")) {
						String [] strs = message.split("（");
						bookmoney =strs[1].split("阅");
						System.out.println("书本阅点数"+bookmoney[0]);
					}else if (testcase.getExpert().equals("获取剩余阅点对比")) {
						System.out.println(message);
						String[] strs=message.split("阅");
						System.out.println(bookmoney[0]+"--"+strs[0]);
						int h=Integer.valueOf(bookmoney[0])+Integer.valueOf(strs[0]);
						String equalsmoney=String.valueOf(h)+"阅点";
						System.out.println(equalsmoney);
						System.out.println(message+"--"+bookmoney[0]+"--"+"--"+equalsmoney);
						dt.compare_money(message, bookmoney[0],equalsmoney,i, b, ws);
						System.out.println("5");
					}
			} catch (AssertionFailedError e) {
				// TODO: handle exception
				System.out.println("AssertionFailedError");
				error = new Label(19, i, "error");
				b=new Label(20, i, "对象未找到");
				ws.addCell(error);
				ws.addCell(b);
				/** 异常截图 ***/
				
//				sshot.takeScreenShot(solo.getCurrentActivity(), path, "AssertionFailedError_"+i);
				solo.takeScreenshot(String.valueOf(i+1));
//				 ScreenShot.takeScreenShot(getDevice().getCurrentActivity(),
//				 Config.resultPath + "/" + (i + 1) + ".png");

				if (testcase.getTryjump() != null
						&& !testcase.getTryjump().equals("")) {
					System.out.println("getTryjump" + testcase.getTryjump());
					i = jump.errorJump(testcase, i, b, ws);
					i = i - 1;

				}
				/**** 判断是否有用例标题 有进入用例套自动跳转判断 ****/
				else {
					System.out.println("bool进来");
//					if (boole) {
//						i = casecover.deterMine();
//						WriteLog.writeComLog("执行用例套或自动跳转--用例跳转指" + i+ "行");
//						i = i - 1;
//						if (i == -1) {
//							WriteLog.writeComLog("所有用例套或自动跳转执行完毕停止自动化测试");
//							tearDown();
//							break a;
//						}
//						testcase = null;
//						times = 0;
//						continue a;
//					}
				}
				WriteLog.writeComLog("Error--执行第" + i + "行对象未找到--查找对象方式："
						+ testcase.getFindaction() + "操作动作："
						+ testcase.getAction() + "对象属性为："
						+ testcase.getProperty());

			}catch (NullPointerException e) {
				// TODO: handle exception				
				solo.takeScreenshot(String.valueOf(i+1));
				error = new Label(19, i, "error");
				b=new Label(20, i, "对象为空");
				ws.addCell(error);
				ws.addCell(b);
				if (testcase.getTryjump() != null
						&& !testcase.getTryjump().equals("")) {
					System.out.println("getTryjump" + testcase.getTryjump());
					i = jump.errorJump(testcase, i, b, ws);
					i = i - 1;
				}
				System.out.println("NullPointerException");
				
			}catch (ClassCastException e) {
				solo.takeScreenshot(String.valueOf(i+1));
				error = new Label(19, i, "error");
				b=new Label(20, i, "转换失败");
				ws.addCell(error);
				ws.addCell(b);
				if (testcase.getTryjump() != null
						&& !testcase.getTryjump().equals("")) {
					System.out.println("getTryjump" + testcase.getTryjump());
					i = jump.errorJump(testcase, i, b, ws);
					i = i - 1;
				}
				System.out.println("ClassCastException");
				
			}catch(Exception e){
				solo.takeScreenshot(String.valueOf(i+1));
				error = new Label(19, i, "error");
				b=new Label(20, i, "程序出错");
				ws.addCell(error);
				ws.addCell(b);
				if (testcase.getTryjump() != null
						&& !testcase.getTryjump().equals("")) {
					System.out.println("getTryjump" + testcase.getTryjump());
					i = jump.errorJump(testcase, i, b, ws);
					i = i - 1;
				}
				System.out.println("Exception");
				
			}

			/****** 写结果 *****/
			b = new Label(17, i, testcase.getTime());
			ws.addCell(b);
			WriteLog.writeComLog("执行第[" + i + "]行操作完成-----查找对象方式：["
					+ testcase.getFindaction() + "]id是["
					+ testcase.getProperty() + "]操作动作：[" + testcase.getAction()
					+ "]对象属性为：[" + testcase.getProperty() + "]消耗时间：["
					+ testcase.getTime() + "]");

			/**** 清除对象数据 ***/
			System.out.println("你现在是在第" + i + "行");
			testcase = null;
			times = 1;
			n = 0;
			

		}
		wbe.write();
		wbe.close();
		book.close();

	}

	

}
