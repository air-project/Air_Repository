package com.spring.maven.test01.jms;

import javax.jms.Destination;

public interface ProducerService {
	 public void sendMessage(Destination destination, final String message);
}
