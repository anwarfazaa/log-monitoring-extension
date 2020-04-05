/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appdynamics.extensions.logmonitor.snapshot.processor;

import com.appdynamics.extensions.logmonitor.snapshot.EmailStyle;
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
    String emailContent;
    String Body;
    EmailStyle emailStyle;
    
    public SMTPEmailProcessor(Map<String,?> globalConfigYml) {
        this.globalConfigYml = globalConfigYml;
        smtp = new SMTPEmail(this.globalConfigYml);
        emailStyle = new EmailStyle();
        emailContent = "";
    }
    
    public InternetAddress[] prepareReciepientsList() throws AddressException {
       String RecipeintsListString = (String) globalConfigYml.get("emailRecipients");
       String[] retrivedReciepientsList = RecipeintsListString.split(",",0);
       InternetAddress[] toAddress = new InternetAddress[retrivedReciepientsList.length];
        
       
       for( int i = 0; i < retrivedReciepientsList.length; i++ ) {
               toAddress[i] = new InternetAddress(retrivedReciepientsList[i]);
                Logger.getLogger(SMTPEmailProcessor.class.getName()).log(Level.SEVERE, null, "Address : retrivedReciepientsList[i] ");
        }
       return toAddress;
    }
    
    public String emailBeautifier(){
        Body = "" +
        "<html><body><table border=\"1\"> " +
        emailStyle.ExtensionHeader +        
        this.emailContent +        
        "</table></body></table>";
        
        return Body;
    }
    
    public void addEmailContent(String logname,String content) {
        this.emailContent+= "<tr><td>" + logname + "</td><td>" + content + "<td></td>";
    }
    
    public void executeEmailSender() {
        try {
            if (!"".equals(this.emailContent)) {
                smtp.sendEmail(emailBeautifier(), prepareReciepientsList());
            }
            this.emailContent = "";
        } catch (AddressException ex) {
            Logger.getLogger(SMTPEmailProcessor.class.getName()).log(Level.SEVERE, "Address : retrivedReciepientsList[i] ", ex);
        }
    }
}
