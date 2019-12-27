package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestSocket {
	static InetAddress Host;
	static String Ip;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Host = InetAddress.getLocalHost();
			Ip = Host.getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		test01();
		// ping();
		// getHostName();
		// getIp();
	}

	public static void test01() {
		List<String> ipList = new ArrayList<>();
		String[] ipResolve = Ip.split("\\.");
		
		String pre = ipResolve[0] + "." + ipResolve[1] + "." + ipResolve[2] + ".";
		try {
			for (int i = 1; i <= 255; i++) {
				String ip = pre + i;
				InetAddress ad = InetAddress.getByName(ip);
				boolean state = ad.isReachable(1000);// 测试是否可以达到该地址 ,判断ip是否可以连接
														// 1000是超时时间
				if (state) {
					ipList.add(ip);
					System.out.println("连接成功" + ip);
				}
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("连接成功的：" + ipList);
	}

	public static void ping() {
		Process p;
		try {
			p = Runtime.getRuntime().exec("ping  " + "127.0.0.1");

			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = null;
			StringBuilder sb = new StringBuilder();

			while ((line = br.readLine()) != null) {
				if (line.length() != 0)
					sb.append(line + "\r\n");
			}

			System.out.println("本次指令返回的消息是：");
			System.out.println(sb.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void getHostName() {
		System.out.println(Host.getHostName());
	}

	public static void getIp() {
		System.out.println(Ip);
	}

}
