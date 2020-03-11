package servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

/**
 * 验证码
 */
@WebServlet("/verifyCode")
public class VerifyCode extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 创建图片对象，放入缓存
        int width = 100;
        int height = 50;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        //2. 美化图片
        Graphics g = image.getGraphics();
        //2.1 fill react
        g.setColor(Color.pink);
        g.fillRect(0, 0, width, height);
        //2.2 draw border
        g.setColor(Color.blue);
        g.drawRect(0, 0, width, height);
        //2.3 write code
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        String code = "";
        Random random = new Random();
        for(int i=1; i <= 4; i++){
            g.drawString(String.valueOf(str.charAt(random.nextInt(str.length()))), width/5 * i , height / 2);
        }
        //2.4 draw noisy lines
        g.setColor(Color.green);
        for (int i = 0; i < 15; i++) {
            int x1 = random.nextInt(width);
            int x2 = random.nextInt(width);
            int y1 = random.nextInt(height);
            int y2 = random.nextInt(height);
            g.drawLine(x1, y1, x2, y2);
        }

        //3.输入图片到浏览器
        ImageIO.write(image, "jpg", resp.getOutputStream());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }


}
