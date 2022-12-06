package pt.isec.gps.team11.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class MailSending {
/*
    public static void sendVoucher(){
        final String fromEmail = "gps2223.team11@gmail.com"; //requires valid gmail id
        final String password = "@gps2023"; // correct password for gmail id
        final String toEmail = "gps2223.team11@gmail.com"; // can be any email id

        System.out.println("Start of the sending process!");
        Properties props = new Properties();

        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.debug", "true");
        props.put("mail.smtp.ss1.protocols", "TLSv1.2"); //enable STARTTLS
        props.put("mail.smtp.ssl.trust", "*");
        props.put("mail.smtp.socketFactory.class",    "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.ssl",                    "true");

        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail,password);
            }
        };
        Session session = Session.getInstance(props,auth);

        EmailUtils.sendEmail(session,toEmail,"Subject","Body");






    }

    */

    public static void sendVoucher() {

            String host="smtp.gmail.com";
            final String user="gps2223.team11@gmail.com"; // can be any email id
            final String password="@gps2023"; // correct password for gmail id



        // Sender's email ID needs to be mentioned
        String from = "gps2223.team11@gmail.com";


        String to="gps2223.team11@gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(user, password);

            }

        });
        //session.setDebug(true);
        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("This is the Subject Line!");

            Multipart multipart = new MimeMultipart();

            MimeBodyPart attachmentPart = new MimeBodyPart();

            MimeBodyPart textPart = new MimeBodyPart();

            try {

                File f =new File("H:\\pepipost_tutorials\\javaemail1.PNG");

                attachmentPart.attachFile(f);
                textPart.setText("This is text");
                multipart.addBodyPart(textPart);
                multipart.addBodyPart(attachmentPart);

            } catch (IOException e) {

                e.printStackTrace();

            }

            message.setContent(multipart);

            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }

}



/*
public class MailSending {
    public static void sendVoucher() throws Exception {
        System.out.println("Preparing to send email");
        Properties properties = new Properties();
        String recepient = "quimicster@gmail.com";
        //Enable authentication
        properties.put("mail.smtp.auth", "true");
        //Set TLS encryption enabled
        properties.put("mail.smtp.starttls.enable", "true");
        //Set SMTP host
        properties.put("mail.smtp.host", "smtp.gmail.com");
        //Set smtp port
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.ssl.trust", "*");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        //properties.put("mail.smtp.ss1.protocols", "TLSv1.2"); //enable STARTTLS




        //Your gmail address
        String myAccountEmail = "gps2223.team11@gmail.com";
        //Your gmail password
        String password = "@gps2023";

        //Create a session with account credentials
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });
        session.getProperties().put("mail.smtp.ssl.trust", "smtp.gmail.com");
        session.getProperties().put("mail.smtp.starttls.enable", "true");
        //Prepare email message
        Message message = prepareMessage(session, myAccountEmail, recepient);

        //Send mail
        Transport.send(message);
        System.out.println("Message sent successfully");
    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recepient) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Hello guys");
            message.setContent(message, "text/html; charset=UTF-8");
            return message;
        } catch (Exception ex) {

        }
        return null;
    }

}*/