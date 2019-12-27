package socket;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import jdbc.Android;
import jdbc.AndroidDAO;

public class Server {
	static ServerSocket ss;
	static Socket s;
	static String receiveMsg = "";
	static String sendMsg = null;
	static JTextArea messageBox;

	public static void main(String[] args) {

		try {
			ss = new ServerSocket(8888);
			System.out.println("服务已建立，等待客户端消息");
			s = ss.accept();

			chat();
			// multiThread();
			// singleThread();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 聊天框
	 */
	private static void chat() {
		JFrame f = new JFrame("SmileChat");
		f.setSize(500, 400);
		f.setLocation(900, 200);
		f.setLayout(new BorderLayout());

		// 顶部文本条
		JPanel p = new JPanel();
		JLabel l = new JLabel("Client");
		p.add(l);

		// 使用滚动面板,表格放到面板上
		messageBox = new JTextArea();
		messageBox.setPreferredSize(new Dimension(200, 150));
		messageBox.setLineWrap(true); // 设置自动换行
		messageBox.setEditable(false); // 因为是展示聊天内容，所以不允许编辑

		JScrollPane sp = new JScrollPane(messageBox);

		JPanel bottomP = new JPanel();
		bottomP.setLayout(new FlowLayout());
		JLabel tlMsg = new JLabel("输入内容：");
		JTextField tfMsg = new JTextField("");
		tfMsg.setPreferredSize(new Dimension(200, 30));
		JButton sendB = new JButton("发送");
		sendB.setPreferredSize(new Dimension(80, 30));
		bottomP.add(tlMsg);
		bottomP.add(tfMsg);
		bottomP.add(sendB);

		f.add(p, BorderLayout.NORTH);
		f.add(sp, BorderLayout.CENTER);
		f.add(bottomP, BorderLayout.SOUTH);

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);

		tfMsg.grabFocus();
		// 登陆事件
		sendB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String msg = tfMsg.getText();
				if (msg.equals("")) {
					JOptionPane.showMessageDialog(f, "不能发送空消息");
					return;
				}
				
				try {
					OutputStream os = s.getOutputStream();
					// socket输出流。将控制台信息数据传给对方
					PrintWriter pw = new PrintWriter(new OutputStreamWriter(os));

					messageBox.append("我：" + msg + "\n");
					pw.println("Server" + "：" + msg);
					pw.flush();
					//清空对话框
					tfMsg.setText("");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		multiThread();
	}

	/**
	 * 多线程
	 */
	private static void multiThread() {
		// 启动发送消息线程
		//new SendThread(s, "Server").start();
		// 启动接受消息线程
		new ReceiveThread(s, messageBox).start();
	}

	/**
	 * 单线程
	 */
	private static void singleThread() {
		while (receiveMsg != null) {
			receive();
		}
	}

	/**
	 * 接收消息并回复
	 */
	private static void receive() {
		try {
			// ======================接收消息======================
			// 打开客户端的输入流
			InputStream is = s.getInputStream();
			// 使用缓存流读出客户端过来的数据流
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			receiveMsg = br.readLine();
			System.out.println("客户端说：" + receiveMsg);

			// ======================回复=========================
			// 使用客户端socket对象的输出流给客户端返回数据
			OutputStream os = s.getOutputStream();
			// 使用缓存流写出数据
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(os));

			// androidResponse(); //使用机器人回复
			humenResponse(); // 人工回复

			pw.println(sendMsg);
			pw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 人工客服回复
	 * 
	 * @throws IOException
	 */
	private static void humenResponse() throws IOException {
		BufferedReader brsi = new BufferedReader(new InputStreamReader(System.in));
		sendMsg = brsi.readLine();
	}

	/**
	 * 机器人回复
	 */
	private static void androidResponse() {
		HashMap<String, String> hm = new HashMap<>();
		hm.put("receive", receiveMsg);

		AndroidDAO dao = new AndroidDAO();
		List<Android> ads = dao.selectByMap(hm);
		if (ads.isEmpty()) {
			sendMsg = "我无法回答你^_^";
		} else {
			for (Android ad : ads) {
				sendMsg = ad.response;
			}
		}
	}

}
