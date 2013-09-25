package com.williamhill.pds.services;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.integration.Message;
import org.springframework.integration.support.MessageBuilder;

public class FileChangedNotificationService {

    public Message<String> handleFileChangedNotification(File file) {
        System.out.println("handleFileChangedNotification() called for file [" + file.getName() + "]");
        // Extract the OXIREP level from the file name pattern
        // TODO: Review this idea later - it smells wrong
        String[] fileNameSections = file.getName().split("_");
        String oxiReplevel = fileNameSections[1];
       
        // Convert file content to message payload
        String content;
		try {
			content = FileUtils.readFileToString(file);
		} catch (IOException e) {
			content = "Error reading file. Exception Message = [" + e.getMessage() + "]";
		}
		
		// Build message from File Content and decorate headers
		Message<String> message = MessageBuilder
				.withPayload(content)
				.setHeader("file.name", file.getName())
				.setHeader("oxirep.level", oxiReplevel)
				.build();
        return message;
    }
}