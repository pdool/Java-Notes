package com.leosanqing.facade.pagemakeer;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

/**
 * @Author: leosanqing
 * @Date: 2019-09-16 22:38
 *
 * 这里封装我们调用数据库和调用HtmlWriter的逻辑，我们只需要传入两个参数，他就能完成页面的编写
 */
public class PageMaker {
    private PageMaker() {
    }

    public static void makeWelcomePage(String mailaddr, String filename) {
        try {
            Properties properties = Database.getProperties("maildata");
            String username = properties.getProperty(mailaddr);
            HtmlWriter htmlWriter = new HtmlWriter(new FileWriter(filename));
            htmlWriter.title("Welcome to " + username + "'s page");
            htmlWriter.paragraph(username + "欢迎来到" + username + "的主页");
            htmlWriter.paragraph("等你邮件哦");

            htmlWriter.mailto(mailaddr, username);
            htmlWriter.close();
            System.out.println(filename + " is created for " + mailaddr + " (" + username + " )");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
