/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appdynamics.extensions.logmonitor.snapshot;

import com.appdynamics.extensions.logmonitor.snapshot.config.EmailConfiguration;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Anwar
 */
public class EmailObject {
    
    private Map<String,String> configurationSet;
    private EmailConfiguration emailConfiguration; 
    
  public EmailObject(){
      emailConfiguration = new EmailConfiguration();
      configurationSet = new HashMap<>();
      configurationSet.put("to", emailConfiguration.Values.get("to").toString());
      configurationSet.put("cc", emailConfiguration.Values.get("cc").toString());
      configurationSet.put("bcc",emailConfiguration.Values.get("bcc").toString());
      configurationSet.put("body","");
  } 
  
  public void setEmailBody(String emailBody) {
      this.configurationSet.put("body", emailBody);
  }
  
  public Map<String,String> EmailConfiguration() {
      return this.EmailConfiguration();
  }
    
}
