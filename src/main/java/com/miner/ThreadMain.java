package com.miner;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.core.JmsTemplate;

public class ThreadMain implements Runnable {

	ConfigurableApplicationContext context;
	public ThreadMain(ConfigurableApplicationContext context){
		this.context = context;
        Thread t = new Thread(this);
        t.start();
	}
	
	@Override
	public void run() {
		JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
		
		
		
		
		
        // Send a message with a POJO - the template reuse the message converter
        System.out.println("Sending an email message.");
        jmsTemplate.convertAndSend("mailbox", new Email("info@example.com", "Hello"));
        
        
	}
	
}
