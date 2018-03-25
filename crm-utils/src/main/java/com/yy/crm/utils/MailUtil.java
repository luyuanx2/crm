package com.yy.crm.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.File;
import java.io.UnsupportedEncodingException;

/**
 * @author luyuanyuan on 2018/2/3.
 */
@Component
@Slf4j
public class MailUtil {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;
    @Value("${yy.manage.url}")
    private String manageUrl;

    public void sendSimpleMail(String to,String subject,String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        mailSender.send(message);
    }

    @Async
    public void sendHtmlMessage(String to,String subject,String content) {
        MimeMessage message;
        message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            InternetAddress fromAddress = new InternetAddress(MimeUtility.encodeText("Flyat") + "<" + from + ">");// 创建邮件发送者地址
            helper.setFrom(fromAddress);
            InternetAddress toAddress = new InternetAddress(MimeUtility.encodeText("接收方") + "<" + to + ">");// 创建邮件发送者地址
            helper.setTo(toAddress);
            helper.setSubject(subject);
            //第二个参数指定发送的是HTML格式
            helper.setText(content, true);
        } catch (MessagingException | UnsupportedEncodingException e) {
            log.error("sendHtmlMessage error",e);
        }
        mailSender.send(message);
    }

    public void sendAttachmentsMail(String to, String subject, String content, String filePath,String fileName) {
        MimeMessage message = null;
        try {
            message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            InternetAddress fromAddress = new InternetAddress(MimeUtility.encodeText("Flyat") + "<" + from + ">");// 创建邮件发送者地址
            helper.setFrom(fromAddress);
            InternetAddress toAddress = new InternetAddress(MimeUtility.encodeText("接收方") + "<" + to + ">");// 创建邮件发送者地址
            helper.setTo(toAddress);
            helper.setSubject(subject);
            StringBuffer sb = new StringBuffer();
            sb.append("<h1>带附件的邮件内容</h1>")
                    .append("<p style='color:red;'>红色字</p>");
            //第二个参数指定发送的是HTML格式
            helper.setText(sb.toString(), true);
            //注意项目路径问题，自动补用项目路径
            FileSystemResource file = new FileSystemResource(new File(filePath));
            helper.addAttachment(fileName, file);
        } catch (Exception e) {
            log.error("sendAttachmentsMail error",e);
        }
        mailSender.send(message);
    }

    public void sendInlineMail(String to, String subject, String content, String filePath,String fileName) {
        MimeMessage message = null;
        try {
            message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            InternetAddress fromAddress = new InternetAddress(MimeUtility.encodeText("Flyat") + "<" + from + ">");// 创建邮件发送者地址
            helper.setFrom(fromAddress);
            InternetAddress toAddress = new InternetAddress(MimeUtility.encodeText("接收方") + "<" + to + ">");// 创建邮件发送者地址
            helper.setTo(toAddress);
            helper.setSubject(subject);
            //第二个参数指定发送的是HTML格式
            helper.setText("<html><body>带静态资源的邮件内容 图片:<img src='cid:forbidden' /></body></html>", true);

            FileSystemResource file = new FileSystemResource(new File(filePath));
            helper.addInline("forbidden", file);
        } catch (Exception e) {
            log.error("sendInlineMail error",e);
        }
        mailSender.send(message);
    }

    /**
     * 	后台用户注册邮件内容
     */
    public String getMailCapacity(String password,String username){

        try {
            if(!(StringUtils.isEmpty(password.trim()) && StringUtils.isEmpty(username.trim()))){
                StringBuffer sb = new StringBuffer();
                sb.append("亲爱的"+username+",您好!");
                sb.append("<br>");
                sb.append("</br>");
                sb.append("感谢您注册，您登录的用户名为: "+username);
                sb.append("<br>");
                sb.append("</br>");
                sb.append("登录密码为: "+password);
                sb.append("<br>");
                sb.append("</br>");
//                sb.append("请尽快登录系统修改密码。");
//                sb.append("<br>");
//                sb.append("</br>");
                sb.append("登录地址: ");
                // 服务器路径
                sb.append("<a href=\""+manageUrl);
                sb.append("\">");
                sb.append(manageUrl);
                sb.append("</a>");
                log.info(sb.toString());
                return sb.toString();
            }
        } catch (Exception e) {
            log.error("getMailCapacity异常",e);
        }
        return "";
    }
}
