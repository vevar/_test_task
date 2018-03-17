package service;

import java.util.Properties;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SenderReport implements Communications {

    private String sender;

    public SenderReport() {
        sender = "С уважением,автоматизированная система мониторинга.";
    }

    public void sendEmail(String email, String text, String subject) {
        if (email == null)
            throw new NullPointerException("email is incorrect");
        if (text == null)
            throw new NullPointerException("text is incorrect");
        if (subject == null)
            throw new NullPointerException("subject is incorrect");


        final String username = "ariasoftTest123@gmail.com";
        final String password = "q2e4t6u8i9o";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username,password);
                    }
                });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("ariasoftTest123@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email));
            message.setSubject(subject);
            message.setContent(text + sender ,"text/plain; charset=UTF-8");

            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
