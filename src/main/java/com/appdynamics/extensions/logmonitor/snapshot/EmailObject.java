package com.appdynamics.extensions.logmonitor.snapshot;

import com.appdynamics.extensions.conf.MonitorContextConfiguration;
import com.google.common.collect.Maps;
import java.util.Map;

/**
 *
 * @author Anwar Fazaa
 */
public class EmailObject {
    
    private Map<String,?> configYml;
    private Map<String, String> SMTPServer;
    private Map<String, String> emailControl;
    
    
  public EmailObject(MonitorContextConfiguration monitorContextConfiguration){
      //emailConfiguration = new EmailConfiguration(monitorContextConfiguration);
      //configYml = Maps.newHashMap();
      //configYml = emailConfiguration.Values;
      //emailConfiguration = new EmailConfiguration(monitorContextConfiguration);
      SMTPServer = (Map<String,String>) monitorContextConfiguration.getConfigYml().get("smtpEmailAccount");
      emailControl = (Map<String,String>) monitorContextConfiguration.getConfigYml().get("emailRecipients");
  } 
 
  
  
  public Map<String,String> SMTPServerInformation() {
      return this.SMTPServer;
  }
  
  public Map<String,String> emailControlInformaiton () {
      return this.emailControl;
  }
    
}
