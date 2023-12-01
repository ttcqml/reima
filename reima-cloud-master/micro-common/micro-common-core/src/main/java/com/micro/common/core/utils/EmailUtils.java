package com.micro.common.core.utils;

import com.micro.common.core.utils.DateUtils;
import io.jsonwebtoken.lang.Strings;
import org.slf4j.LoggerFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class EmailUtils {
    private static org.slf4j.Logger log = LoggerFactory.getLogger(EmailUtils.class);

    public static void main(String[] args) {
        sendExceptionMail("注册异常","异常内容");
    }

    public static void sendExceptionMail(String title,String content){
        String html = "<table cellpadding=\"0\"cellspacing=\"0\"width=\"96%\"style=\"background:#ffffff;border:1px solid rgb(204,204,204);margin:2%;\"><tbody><tr><td width=\"30px;\">&nbsp;</td><td align=\"\"><p style=\"margin:0px;padding:0px;line-height:30px;font-size:14px;color:#333333;font-family:'宋体',arial,sans-serif;\">"+DateUtils.getTime()+"</p><div style=\"line-height:20px;height:20px;\">&nbsp;</div><p style=\"margin:0px;padding:0px;line-height:30px;font-size:14px;color:#333333;font-family:'宋体',arial,sans-serif;\">"+content+"</p><div style=\"line-height:80px;height:80px;\">&nbsp;</div></td></tr></tbody></table>";
        send("baolb0717@163.com,ttcqml@163.com","【REIMA项目】"+title, html);
    }

    public static void send(String toaddr,String title,String html){

        String port = "465";
        String host = "smtp.163.com";
        String userName = "exception110@163.com";
        String password = "GFXDSIQQFIGQBITG";

        EmailSendInfo mailInfo = new EmailSendInfo();
        mailInfo.setMailServerHost(host);
        mailInfo.setMailServerPort(port);
        mailInfo.setValidate(true);
        mailInfo.setUserName(userName);
        mailInfo.setPassword(password);
        mailInfo.setFromAddress(userName);
        mailInfo.setSubject(title);
        mailInfo.setContent(html);
        String[] address = toaddr.split(",");
        for (int i = 0;i<address.length;i++){
            mailInfo.setToAddress(address[i]);
            //发送含附件的邮件
            sendHtmlMail(mailInfo);
        }
    }

    public static boolean sendHtmlMail(EmailSendInfo mailInfo){
        boolean sendStatus = false;//发送状态
        // 判断是否需要身份认证
        EmailAuthenticator authenticator = null;
        Properties pro = mailInfo.getProperties();
        //如果需要身份认证，则创建一个密码验证器
        if (mailInfo.isValidate()) {
            authenticator = new EmailAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
        }
        // 根据邮件会话属性和密码验证器构造一个发送邮件的session
        Session sendMailSession = Session.getDefaultInstance(pro,authenticator);
        //【调试时使用】开启Session的debug模式
        sendMailSession.setDebug(true);
        try {
            // 根据session创建一个邮件消息
            Message mailMessage = new MimeMessage(sendMailSession);
            // 创建邮件发送者地址
            Address from = new InternetAddress(mailInfo.getFromAddress());
            // 设置邮件消息的发送者
            mailMessage.setFrom(from);
            // 创建邮件的接收者地址，并设置到邮件消息中
            Address to = new InternetAddress(mailInfo.getToAddress());
            // Message.RecipientType.TO属性表示接收者的类型为TO
            mailMessage.setRecipient(Message.RecipientType.TO,to);
            // 设置邮件消息的主题
            mailMessage.setSubject(mailInfo.getSubject());
            // 设置邮件消息发送的时间
            mailMessage.setSentDate(new Date());
            // 设置邮件内容
            mailMessage.setContent(mailInfo.getContent(), "text/html;charset=utf-8");
            // 发送邮件
            Transport.send(mailMessage);
            sendStatus = true;
        } catch (MessagingException ex) {
            log.error("以HTML格式发送邮件出现异常:{}", ex);
            return sendStatus;
        }
        return sendStatus;
    }

    static class EmailSendInfo {

        // 发送邮件的服务器的IP和端口
        private String mailServerHost;
        private String mailServerPort = "25";
        // 邮件发送者的地址
        private String fromAddress;
        // 邮件接收者的地址
        private String toAddress;
        // 登陆邮件发送服务器的用户名和密码
        private String userName;
        private String password;
        // 是否需要身份验证
        private boolean validate = true;
        // 邮件主题
        private String subject;
        // 邮件的文本内容
        private String content;
        // 邮件附件的文件名
        private String[] attachFileNames;

        /**
         * 获得邮件会话属性
         */
        public Properties getProperties() {
            Properties p = new Properties();
            p.put("mail.smtp.host", this.mailServerHost);
            p.put("mail.smtp.port", this.mailServerPort);
            p.put("mail.smtp.auth", validate ? "true" : "false");
            // 补充
            p.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
            p.put("mail.smtp.socketFactory.fallback","false");
            p.put("mail.smtp.socketFactory.port","465");
            return p;
        }

        public String getMailServerHost() {
            return mailServerHost;
        }

        public void setMailServerHost(String mailServerHost) {
            this.mailServerHost = mailServerHost;
        }

        public String getMailServerPort() {
            return mailServerPort;
        }

        public void setMailServerPort(String mailServerPort) {
            this.mailServerPort = mailServerPort;
        }

        public boolean isValidate() {
            return validate;
        }

        public void setValidate(boolean validate) {
            this.validate = validate;
        }

        public String[] getAttachFileNames() {
            return attachFileNames;
        }

        public void setAttachFileNames(String[] fileNames) {
            this.attachFileNames = fileNames;
        }

        public String getFromAddress() {
            return fromAddress;
        }

        public void setFromAddress(String fromAddress) {
            this.fromAddress = fromAddress;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getToAddress() {
            return toAddress;
        }

        public void setToAddress(String toAddress) {
            this.toAddress = toAddress;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String textContent) {
            this.content = textContent;
        }
    }
    static class EmailAuthenticator extends Authenticator {

        private String userName;
        private String password;

        public EmailAuthenticator(){}

        public EmailAuthenticator(String username, String password) {
            this.userName = username;
            this.password = password;
        }

        @Override
        protected PasswordAuthentication getPasswordAuthentication(){
            return new PasswordAuthentication(userName, password);
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

    }
}
