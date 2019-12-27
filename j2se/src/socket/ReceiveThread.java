package socket;
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.JTextArea;

 
public class ReceiveThread extends Thread {
 
    private Socket s;
    static String receiveMsg = "";
    static JTextArea messageBox;
 
    public ReceiveThread(Socket s, JTextArea ta) {
        this.s = s;
        this.messageBox = ta;
    }

	public void run() {
        try {
            InputStream is = s.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is)); // 读取客户端过来的流
            
            while(true){
            	receiveMsg = br.readLine();
            	messageBox.append(receiveMsg + "\n");
            	System.out.println(receiveMsg);
            	System.out.println("请输入：");
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
 
    }
 
}