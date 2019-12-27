package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JTextArea;

public class SendThread extends Thread {

	private Socket s;
	private String role;
	static String sendMsg;

	public SendThread(Socket s, String role) {
		this.s = s;
		this.role = role;
	}

	public void run() {
		try {
			OutputStream os = s.getOutputStream();
			// socket输出流。将控制台信息数据传给对方
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(os));

			// System.out.println("请输入：");

			// 获取键盘录入。获取控制台用户输入的信息
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			while ((sendMsg = br.readLine()) != null) {
				pw.println(role + "：" + sendMsg);
				pw.flush();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}