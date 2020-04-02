/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appdynamics.extensions.logmonitor.snapshot.processor;

import com.appdynamics.extensions.logmonitor.snapshot.SMTPEmail;
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
    
    
    public SMTPEmailProcessor() {
        smtp = new SMTPEmail();
    }
    
    public InternetAddress[] prepareReciepientsList() throws AddressException {
       String[] retrivedReciepientsList = smtp.Recipients.split(",",0);
       
       InternetAddress[] toAddress = new InternetAddress[retrivedReciepientsList.length];
        
       
       for( int i = 0; i < retrivedReciepientsList.length; i++ ) {
               toAddress[i] = new InternetAddress(retrivedReciepientsList[i]);
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
            Logger.getLogger(SMTPEmailProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
