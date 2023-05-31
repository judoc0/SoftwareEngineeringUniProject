package Business;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.util.Properties;
// BRIDGE PATTERN
// Concrete Implementation
public class MailJavaxAPI implements MailAPI{
    @Override
    public void inviaEmailAllegato(File tempFile, String indirizzo, String oggetto, String testo) {

        // Recipient's email ID needs to be mentioned.
        String to = indirizzo; //indirizzo

        // Sender's email ID needs to be mentioned
        String from = "jsanziapis@gmail.com";

        // Assuming you are sending email from localhost
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the default Session object.
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("jsanziapis@gmail.com", "boojjuizpjrajdkc");

            }

        });

        // Used to debug SMTP issues
        //session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session); //activation

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject(oggetto);  //oggettoEmail

            Multipart multipart = new MimeMultipart();

            MimeBodyPart attachmentPart = new MimeBodyPart();

            MimeBodyPart textPart = new MimeBodyPart();

            try {


                assert tempFile != null;
                File f =new File(tempFile.getAbsolutePath());

                attachmentPart.attachFile(f);
                textPart.setText(testo);  //testoEmail
                multipart.addBodyPart(textPart);
                multipart.addBodyPart(attachmentPart);

            } catch (IOException e) {

                e.printStackTrace();

            }

            message.setContent(multipart);  //EmailCompleta


            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    @Override
    public void inviaEmail(String indirizzo, String oggetto, String testo) {
        // Recipient's email ID needs to be mentioned.

        String to = indirizzo; //indirizzo

        // Sender's email ID needs to be mentioned
        String from = "jsanziapis@gmail.com";

        // Assuming you are sending email from localhost
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the default Session object.
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("jsanziapis@gmail.com", "boojjuizpjrajdkc");

            }

        });

        // Used to debug SMTP issues
        //session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session); //activation

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject(oggetto);

            // Now set the actual message
            message.setText(testo);


            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

}
