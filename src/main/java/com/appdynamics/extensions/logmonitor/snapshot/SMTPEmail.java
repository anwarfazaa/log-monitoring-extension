package com.appdynamics.extensions.logmonitor.snapshot;

import com.appdynamics.extensions.conf.MonitorContextConfiguration;
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
    private EmailObject emailObj;
    private String from;
    private String username;
    private String password;
    Properties emailProps;
    public String Recipients;
    
    public SMTPEmail(MonitorContextConfiguration monitorContextConfiguration) {
        emailObj = new EmailObject(monitorContextConfiguration);
        from = emailObj.SMTPServerInformation().get("from");
        username = emailObj.SMTPServerInformation().get("username");
        password = emailObj.SMTPServerInformation().get("password");
        Recipients = emailObj.SMTPServerInformation().get("emailRecipients");
        emailProps = new Properties();
        
        emailProps.put("mail.smtp.auth", "true");
        // to be implemented - enable support for tls or ssl
        emailProps.put("mail.smtp.starttls.enable", "true");
        //emailProps.put("mail.smtp.host", emailObj.SMTPServerInformation().get("host"));
        emailProps.put("mail.smtp.ssl.trust", emailObj.SMTPServerInformation().get("host"));
        emailProps.put("mail.smtp.port", emailObj.SMTPServerInformation().get("port"));
    }
    
    public void sendEmail(String Body,InternetAddress[] recipients) {
    
    // Get the Session object
    Session session = Session.getInstance(emailProps,
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

            message.setRecipients(Message.RecipientType.TO,recipients);
            
            
            // Set Subject
            message.setSubject(emailObj.emailControlInformaiton().get("emailSubject"));

            // Put the content of your message
            message.setContent(Body,"text/html");

            // Send message
            Transport.send(message);

        } catch (MessagingException e) {
            LOGGER.warn("Email sending exception: " + e.toString());
        }
    }
    
}
