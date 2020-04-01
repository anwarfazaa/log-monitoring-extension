/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.appdynamics.extensions.logmonitor.snapshot.config;

import com.appdynamics.extensions.conf.MonitorContextConfiguration;
import com.google.common.collect.Maps;
import java.util.Map;

/**
 *
 * @author Anwar
 */
public class EmailConfiguration {
   public Map<String, ?> Values;
   private MonitorContextConfiguration monitorContextConfiguration;

    public EmailConfiguration() {
        this.Values = Maps.newHashMap();
        Values = monitorContextConfiguration.getConfigYml();
    }
   
   
   
}
