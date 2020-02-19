package day12;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Demo {
    @Test
    public void jsoup() throws IOException {
        // get xml file path
        String path = Objects.requireNonNull(Demo.class.getClassLoader().getResource("users.xml")).getPath();
        //get document obj
        //by File in
        Document document = Jsoup.parse(new File(path), "utf-8");
        /*//by string
        String str = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n" +
                "<users>\n" +
                "    <user id=\"1\">\n" +
                "        <name>张三</name>\n" +
                "        <age>13</age>\n" +
                "    </user>\n" +
                "    <user id=\"2\">\n" +
                "        <name>lisi</name>\n" +
                "        <age>13</age>\n" +
                "    </user>\n" +
                "</users>\n";
        Document document1 = Jsoup.parse(str, "utf-8"); */

        /*//by url
        Document document2 = Jsoup.parse(new URL("http://www.baidu.com/"), 5000);
        System.out.println(document2);*/

        //get elements
        //Elements elements = document.getElementsByTag("name");
        Elements elements = document.getElementsByAttributeValue("id", "2");
        Elements ele_name = elements.get(0).getElementsByTag("name");
        System.out.println(ele_name);
        Element element = elements.get(0);
        System.out.println(element.text());
    }
}
