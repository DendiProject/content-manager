/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.netckracker.content.manager;

import com.google.common.io.Files;
import com.netckracker.content.manager.listener.NodeListener;
import com.rabbitmq.client.ConnectionFactory;
import java.io.File;
import org.apache.qpid.server.Broker;
import org.apache.qpid.server.BrokerOptions;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 *
 * @author eliza
 */

public class EmbeddedBroker {
    private Broker broker;
    public EmbeddedBroker()
    {
        this.broker = new Broker();
    }
    public void start() throws Exception {

    
    
    BrokerOptions brokerOptions = new BrokerOptions();
    brokerOptions.setConfigProperty("qpid.amqp_port", "5673");
    brokerOptions.setConfigProperty("qpid.broker.defaultPreferenceStoreAttributes", "{\"type\": \"Noop\"}");
    brokerOptions.setConfigProperty("qpid.vhost", "/");
    brokerOptions.setConfigurationStoreType("Memory");
    brokerOptions.setStartupLoggedToSystemOut(false);
    //brokerOptions.setInitialConfigurationLocation("src/test/resources/initial-config.json");
    broker.startup(brokerOptions); 
    }
    
    public void stop()
    {
        broker.shutdown();
    }
}
  

