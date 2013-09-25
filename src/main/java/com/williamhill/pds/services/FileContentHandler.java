package com.williamhill.pds.services;

import java.util.Date;

import org.springframework.integration.Message;
import org.springframework.integration.message.GenericMessage;

import com.williamhill.pds.domain.ExtractedContent;
import com.williamhill.pds.domain.ExtractedEntity;

public class FileContentHandler {

	public Message<ExtractedContent> processFile(Message<?> message) {
		System.out.println("processFile() called with message " + message);
		
		String payload = (String) message.getPayload();	
		ExtractedContent extractedContent = new ExtractedContent();
		extractedContent.setContent(payload);
		
		GenericMessage<ExtractedContent> saveMessage = new GenericMessage<ExtractedContent>(extractedContent);
		
		// Create extracted entity
		ExtractedEntity extractedEntity = new ExtractedEntity();
		extractedEntity.setName((String)message.getHeaders().get("file.name"));
		extractedEntity.setSourceSystem("SOURCE_SYSTEM_OXIREP");
		extractedEntity.setExtractedOn(new Date());
		extractedEntity.setSourceId("file");
		extractedEntity.setContentId(extractedContent.getId());
		
		return saveMessage;
	}
}
