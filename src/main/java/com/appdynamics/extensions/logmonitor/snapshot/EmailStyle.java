/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appdynamics.extensions.logmonitor.snapshot;

/**
 *
 * @author Anwar Fazaa
 * 
 * This class contains only email style related strings
 */
public class EmailStyle {
    
    public String ExtensionHeader(String filefullname) {
        return "<tr><td align=\"center\" colspan=\"2\">AppDynamics Log Monitoring extension Alert<br>The below Lines were matched in "+ filefullname + "</td></tr>";
    }
    public final String ContentTableSettings = "";
    
    
}
