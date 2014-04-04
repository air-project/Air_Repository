package com.spring.maven.test01;

import javax.jms.Destination;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.spring.maven.test01.jms.ProducerService;
    
public class ProducerConsumerTest extends BaseSpringTestCase{   
    
    @Autowired  
    private ProducerService producerService;   
    @Autowired  
    @Qualifier("queueDestination")   
    private Destination destination;   
       
    @Test  
    public void testSend() {   
        for (int i=0; i<2; i++) {   
            producerService.sendMessage(destination, "你好，生产者！这是消息：" + (i+1));   
        }   
    }   
       
}  
