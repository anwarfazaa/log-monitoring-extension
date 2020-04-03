/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appdynamics.extensions.logmonitor.snapshot.processor;

import com.appdynamics.extensions.conf.MonitorContextConfiguration;
import com.appdynamics.extensions.logmonitor.snapshot.SMTPEmail;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 *
 * @author Anwar Fazaa
 */
public class SMTPEmailProcessor {
    
    SMTPEmail smtp;
    Map<String,?> globalConfigYml;
    
    public SMTPEmailProcessor(Map<String,?> globalConfigYml) {
        this.globalConfigYml = globalConfigYml;
        smtp = new SMTPEmail(this.globalConfigYml);
    }
    
    public InternetAddress[] prepareReciepientsList() throws AddressException {
       //String[] retrivedReciepientsList = smtp.Recipients.split(",",0);
       String RecipeintsListString = (String) globalConfigYml.get("emailRecipients");
       String[] retrivedReciepientsList = RecipeintsListString.split(",",0);
       System.out.println("*************");
       System.out.println(retrivedReciepientsList[0]);
       InternetAddress[] toAddress = new InternetAddress[retrivedReciepientsList.length];
        
       
       for( int i = 0; i < retrivedReciepientsList.length; i++ ) {
               toAddress[i] = new InternetAddress(retrivedReciepientsList[i]);
                Logger.getLogger(SMTPEmailProcessor.class.getName()).log(Level.SEVERE, null, "Address : retrivedReciepientsList[i] ");
        }
       return toAddress;
    }
    
    public String emailBeautifier(String Body){
        // to be implemented
        // add html style to email
        return Body;
    }
    
    public void executeEmailSender(String log) {
        try {
            smtp.sendEmail(emailBeautifier(log), prepareReciepientsList());
        } catch (AddressException ex) {
            Logger.getLogger(SMTPEmailProcessor.class.getName()).log(Level.SEVERE, "Address : retrivedReciepientsList[i] ", ex);
        }
    }
}
