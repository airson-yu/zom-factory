package com.zom.factory.zfactory.zlab;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

/**
 * http://www.runoob.com/java/java-sending-email.html
 */
public class SendMail {

    public static void main(String[] args) {
        // 收件人电子邮箱
        String to = "airson_yu@163.com";

        // 发件人电子邮箱
        String from = "zom_robot@sina.com";

        // 指定发送邮件的主机为 localhost
        String host = "smtp.sina.com";

        // 获取系统属性
        Properties properties = System.getProperties();

        // 设置邮件服务器
        properties.setProperty("mail.smtp.host", host);

        properties.put("mail.smtp.auth", "true");
        /*properties.setProperty("mail.user", "zom_robot.sina.com");
        properties.setProperty("mail.password", "test123");*/

        // 获取默认session对象
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("zom_robot@sina.com", "test123"); //发件人邮件用户名、授权码
            }
        });

        // 获取默认session对象
        //Session session = Session.getDefaultInstance(properties);

        try {
            // 创建默认的 MimeMessage 对象
            MimeMessage message = new MimeMessage(session);

            // Set From: 头部头字段
            message.setFrom(new InternetAddress(from));

            // Set To: 头部头字段
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: 头部头字段
            message.setSubject("This is the Subject Line!");

            // 设置消息体
            message.setText("This is actual message");

            // 发送消息
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}