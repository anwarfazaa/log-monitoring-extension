package com.appdynamics.extensions.logmonitor.snapshot;

import com.appdynamics.extensions.logging.ExtensionsLoggerFactory;
import com.appdynamics.extensions.logmonitor.LogMonitor;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.slf4j.Logger;

/**
 *
 * @author Anwar Fazaa
 */
public class SMTPEmail {

    private static Logger LOGGER = ExtensionsLoggerFactory.getLogger(SMTPEmail.class);
    EmailObject emailObj;
    String from;
    String username;
    String password;
    
    public SMTPEmail() {
        emailObj = new EmailObject();
        from = emailObj.SMTPServerInformation().get("from");
        username = emailObj.SMTPServerInformation().get("username");
        password = emailObj.SMTPServerInformation().get("password");
    }
    
    public void send(String Body,String recipient) {
    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    // to be implemented - enable support for tls or ssl
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", emailObj.SMTPServerInformation().get("host"));
    props.put("mail.smtp.port", emailObj.SMTPServerInformation().get("port"));

    // Get the Session object
    Session session = Session.getInstance(props,
    new javax.mail.Authenticator() {
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Create a default MimeMessage object
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));

            message.setRecipients(Message.RecipientType.TO,
            InternetAddress.parse(recipient));

            // Set Subject
            message.setSubject(emailObj.emailControlInformaiton().get("emailSubject"));

            // Put the content of your message
            message.setText(Body);

            // Send message
            Transport.send(message);

        } catch (MessagingException e) {
            LOGGER.info("Email exception: " + e.toString());
        }
    }
    
}
