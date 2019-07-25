package com.email;

import java.util.Date;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author cck
 */
@Slf4j
@Service
public class EmailUtil {
    
    private final static String CHARSET = "UTF-8";
    private final static String CONTENT_TYPE = "text/html;charset=UTF-8";
    private String content = "<table border='0' cellspacing='0' cellpadding='0' width='552' style='border-radius:4px;border:1px solid #dedede;margin:0 auto;background-color:#ffffff'> <tbody> <tr> <td align='center' style='padding:20px 25px 20px;background-color:#34495E;color:white;border-radius:4px 4px 0 0'> <div style='color:white'> <a style='color:#ffffff;text-decoration: none;' href='https://wuzhi.me' target='_blank'><span style='margin:0;font-size:22px;'>所愿 - 记录生活点滴</span></a> </div> </td> </tr> <tr> <td style='padding:20px 25px 0px; font-size:16px;line-height:2em;'> <p> </p><div style='margin-bottom: 10px;'>亲爱的 %s：</div> 感谢您注册所愿，期待在未来的日子里您和我们一起记录生活点滴。</td> </tr> <tr> <td style='padding:20px 25px 20px'> <table width='100%%' cellspacing='0' cellpadding='0' border='0' style='margin: 0; padding: 0; border-top: 1px solid #ccc; border-right: 0 !important; border-left: 0 !important; border-bottom: 0 !important;'> <tbody> <tr> <td> <p style='text-align: center;font-size: 12px; line-height: 16px; color: #7e7d7e; margin: 14px 0 10px 0; padding: 0;'> <a style='text-decoration: none; color: #7e7d7e' href='localhost:8080' target='_blank'>所愿 © 2010-2018</a> &nbsp;|</p> <p style='text-align: center;font-size: 12px; line-height: 11px; color: #7e7d7e;margin:0px;'> 如有使用问题或意见反馈，欢迎随时联系我们</p> </td> </tr> </tbody> </table> </td> </tr> </tbody> </table>";
    
    public void send(UserInfo userInfo) throws Exception {
        
        if (userInfo.getMailAddress() == null
                || !userInfo.getMailAddress().contains("@")) {
            log.info("error email []", userInfo);
            return ;
        }
        
        String format = String.format(content, userInfo.getName());
        
        Email email = Email.builder()
                .senderAccount("mainem@163.com")
                .senderName("xxx")
                .senderPassword("xxx")
                .emailSMTPHost("smtp.163.com")
                .receiverName("")
                .tile("所愿")
                .receiverAccount(userInfo.getMailAddress())
                .sendTime(new Date())
                .content(format)
                .build();
        sendEmail(email);
    }
    
    public void sendEmail(Email email) throws Exception {
        
        Properties props = new Properties();
        
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.host", email.getEmailSMTPHost());  
        props.setProperty("mail.smtp.auth", "true");
        
        Session session = Session.getInstance(props);
        session.setDebug(true);
        MimeMessage message = createMineMessage(session, email);
        
        Transport transport = session.getTransport();
        transport.connect(email.getSenderAccount(), email.getSenderPassword());
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }

    private MimeMessage createMineMessage(Session session, Email email) throws MessagingException, Exception {
        
        MimeMessage message = new MimeMessage(session);
        
        // 发信人
        message.setFrom(getAddress(email.getSenderAccount(), email.getSenderName()));
        // 收信人
        message.setRecipient(MimeMessage.RecipientType.TO, 
                getAddress(email.getReceiverAccount(), email.getReceiverName()));
        
        // 抄送给自己, 使用网易发送邮件时,可能会报 554 DT:SPM, 只要抄送一份给自己就不会了
//        message.setRecipient(MimeMessage.RecipientType.CC, 
//                getAddress(email.getSenderAccount(), email.getSenderName()));
        
        message.setSubject(email.getTile(), CHARSET);
        message.setContent(email.getContent(), CONTENT_TYPE);
        
        message.setSentDate(email.getSendTime());
        message.saveChanges();
        return message;
    }

    private InternetAddress getAddress(String address, String name) throws Exception {
        return new InternetAddress(address, name, CHARSET);
    }
        
}
