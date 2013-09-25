package com.williamhill.pds.services;

import org.springframework.integration.Message;

public class MessageSink {

	public void sinkMessage(Message<?> message) {
		System.out.println("sinkMessage() called with [" + message + "]");
	}
}
