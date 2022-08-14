package web;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

class Email {

    static String senderName = "Voragun Supawong";
    static String senderAddress = "voragun2543@gmail.com";
    static String senderPassword = "i@mv0r4gun";
    static String baseDomain = "http://127.0.0.1:8864";

    void sendActivationCode(String target, String secret, int code) {
        String content = "Welcome to " + senderName + ". ";
        content += "Please click this link to activate your account. ";
        content += "<a href='" + baseDomain + "/member-activate";
        content += "?secret=" + secret + "&code=" + code;
        content += "'>Activate Your Account</a>";

        EmailSender sender = new EmailSender(target,
                "Member Activation", content);
        sender.start();
    }
}

class EmailSender extends Thread {
    EmailSender(String t, String s, String c){ 
        target = t;
        subject = s;
        content = c;
    }
    
    String target;
    String subject;
    String content;
    
    @Override
    public void run(){
        send(target, subject, content);
    }
    
    void send(String target, String subject, String content){
        try {
            Properties p = new Properties();
            p.put("mail.smtp.auth", "true");
            p.put("mail.smtp.starttls.enable", "true");
            p.put("mail.smtp.host", "mail.privateemail.com");
            p.put("mail.smtp.port", "587");
            p.put("mail.smtp.ssl.protocols","TLSv1.2");
            
            Session session = Session.getInstance(p, new Detail());
            Message message = new MimeMessage(session);
            String sender = Email.senderName + "<" + Email.senderAddress + ">";
            message.setFrom(new InternetAddress(sender));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(target));
            message.setSubject(subject);
            
            MimeBodyPart body = new MimeBodyPart();
            body.setContent(content, "text/html; charset=utf-8");
            Multipart part = new MimeMultipart();
            part.addBodyPart(body);
            
            message.setContent(part);
            Transport.send(message);            
        } catch (Exception e) {
            // Nothing 
        }
    }
}

class Detail extends Authenticator {
    @Override
    protected PasswordAuthentication getPasswordAuthentication(){
        return new PasswordAuthentication(Email.senderAddress, Email.senderPassword);
    }
}
