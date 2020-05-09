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
    private Map<String,?> emailConfig;
    private String emailSubj;
    private Boolean enableAuth;

    
    public SMTPEmail(Map < String, ? > globalYamlConfig) {
        emailConfig = (Map<String,?>) globalYamlConfig.get("smtpEmailAccount");
        from = (String) emailConfig.get("from");
        username = (String) emailConfig.get("username");
        password = (String) emailConfig.get("password");
        emailSubj = (String) globalYamlConfig.get("emailSubject");
        emailProps = System.getProperties();
        enableAuth = Boolean.parseBoolean(emailConfig.get("enableAuth").toString());
        
        emailProps.put("mail.smtp.host", emailConfig.get("host"));
        emailProps.put("mail.smtp.auth", emailConfig.get("enableAuth"));
        emailProps.put("mail.smtp.ssl.trust","*");
        emailProps.put("mail.smtp.port", emailConfig.get("port"));
        emailProps.put("mail.smtp.starttls.enable", emailConfig.get("enableTls"));
        
        
    }
    
    public void sendEmail(String Body,InternetAddress[] recipients) throws Exception {
    
    
            
        // check if login is enabled or not
          Session session;
          if (enableAuth) {
              session = Session.getInstance(emailProps,
                    new javax.mail.Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });
          } else {
          session = Session.getDefaultInstance(emailProps);
          }
          
            // Create a default MimeMessage object
            Message message;
            
            
            message = new MimeMessage(session);
            
            // the email account will be sending the email
            message.setFrom(new InternetAddress(from));

            // Set Message recipients
            message.setRecipients(Message.RecipientType.TO, recipients);
            
            
            // Set Subject
            message.setSubject(emailSubj);

            // Put the content of your message with the type
            message.setContent(Body,"text/html");

            // Send message
            Transport.send(message);
            
           

        
    }
    
}
