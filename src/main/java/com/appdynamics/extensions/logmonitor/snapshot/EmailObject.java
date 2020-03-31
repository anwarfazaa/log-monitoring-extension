/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appdynamics.extensions.logmonitor.snapshot;

import com.appdynamics.extensions.logmonitor.snapshot.config.EmailConfiguration;
import com.google.common.collect.Maps;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Anwar
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
