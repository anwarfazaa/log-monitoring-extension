package com.appdynamics.extensions.logmonitor.snapshot;

import com.appdynamics.extensions.conf.MonitorContextConfiguration;
import com.appdynamics.extensions.logging.ExtensionsLoggerFactory;
import com.appdynamics.extensions.logmonitor.LogMonitor;
import java.util.Map;
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
    private String from;
    private String username;
    private String password;
    Properties emailProps;
    public String Recipients;
    private Map<String,String> emailConfig;
    private String emailSubj;
    
    public SMTPEmail(Map < String, ? > globalYamlConfig) {
        emailConfig = (Map<String,String>) globalYamlConfig.get("smtpEmailAccount");
        from = emailConfig.get("from");
        username = emailConfig.get("username");
        password = emailConfig.get("password");
        emailSubj = (String) globalYamlConfig.get("emailSubject");
        emailProps = System.getProperties();
        
        
        emailProps.put("mail.smtp.host", emailConfig.get("host"));
        emailProps.put("mail.smtp.auth", "false");
        emailProps.put("mail.smtp.ssl.trust","*");
        emailProps.put("mail.smtp.port", emailConfig.get("port"));
        emailProps.put("mail.smtp.starttls.enable", "true");
        
        
    }
    
    public void sendEmail(String Body,InternetAddress[] recipients) throws Exception {
    
    // Get the Session object
            
          Session session = Session.getDefaultInstance(emailProps);
   
            // Create a default MimeMessage object
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));

            for (InternetAddress rec : recipients) {
                message.addRecipient(Message.RecipientType.TO,rec);
                LOGGER.info("Email will be sent to: " + rec.getAddress());
            }
            
            
            // Set Subject
            message.setSubject(emailSubj);

            // Put the content of your message
            message.setContent(Body,"text/html");

            // Send message
            Transport.send(message);
            
           

        
    }
    
}
