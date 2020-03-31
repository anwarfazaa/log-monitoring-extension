package com.appdynamics.extensions.logmonitor.snapshot;

import com.appdynamics.extensions.logmonitor.snapshot.config.EmailConfiguration;
import com.google.common.collect.Maps;
import java.util.Map;

/**
 *
 * @author Anwar Fazaa
 */
public class EmailObject {
    
    private Map<String,?> configYml;
    private EmailConfiguration emailConfiguration; 
    private Map<String, String> SMTPServer;
    private Map<String, String> emailControl;
    
    
  public EmailObject(){
      emailConfiguration = new EmailConfiguration();
      configYml = Maps.newHashMap();
      configYml = emailConfiguration.Values;
      emailConfiguration = new EmailConfiguration();
      SMTPServer = (Map<String,String>) configYml.get("smtpEmailAccount");
      emailControl = (Map<String,String>) configYml.get("emailRecipients");
  } 
 
  
  
  public Map<String,String> SMTPServerInformation() {
      return this.SMTPServer;
  }
  
  public Map<String,String> emailControlInformaiton () {
      return this.emailControl;
  }
    
}
