package com.test.ios;

import com.test.robotium.bean.TestcaseBean;

import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;


public class Jump {
	int jump;

	public int errorJump(TestcaseBean test, int i, Label b, WritableSheet ws)
			throws RowsExceededException, WriteException {
		/***** 异常跳转 *****/
		if (test.getTryjump() != null || !test.getTryjump().equals("")
				&& test.getAutojump() == null || test.getAutojump().equals("")
				&& test.getCasecover() == null
				|| test.getCasecover().equals("")) {
			jump = Integer.parseInt(test.getTryjump());
			for (int n = i; n <= jump; n++) {
				b = new Label(17, n, "此行不执行，跳转至" + jump);
				ws.addCell(b);
			}
		} else {
			jump = i;
		}
		return jump;
	}

}
