package hutool;

import java.util.Date;

import cn.hutool.core.date.DateUtil;

public class TestHutool {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String dateStr = "2012-12-12 12:12:12";
        Date date = DateUtil.parse(dateStr);
        System.out.println(date);
	}

}
