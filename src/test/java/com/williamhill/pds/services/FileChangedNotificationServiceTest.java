package com.williamhill.pds.services;


import java.io.File;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.integration.Message;

public class FileChangedNotificationServiceTest {

	private FileChangedNotificationService fileChangedNotificationService;
	
	@Before
	public void setup() {
		fileChangedNotificationService = new FileChangedNotificationService();
	}
	
	@Test
	public void testAdaptorReturnsMessageWithFileContentInPayload() throws Exception {
		// Test Inputs
		String dirName = "C:\\sandbox\\workspaces\\dev1\\pds\\src\\test\\resources\\oxirep\\sample";
		String fileName = "getLevelData_CATEGORY_CSV.xml";
		File file = new File(dirName + "\\" + fileName);
		
		Message<?> message = fileChangedNotificationService.handleFileChangedNotification(file);
		
		// Verify message ALWAYS returned 
		Assert.assertNotNull(message);
		
		// Verify message header has file name
		Assert.assertEquals(fileName, message.getHeaders().get("file.name"));
		
		// Verify file contents in pay load
		Assert.assertEquals(FileUtils.readFileToString(file), message.getPayload());
	}
	
	@Test
	public void testAdaptorReturnsMessageEvenWhenFileNotPresent() throws Exception {
		// Test Inputs
		String dirName = "C:\\sandbox\\workspaces\\dev1\\pds\\src\\test\\resources\\oxirep\\sample";
		String fileName = "MISSING_FILE";
		File file = new File(dirName + "\\" + fileName);
		
		Message<?> message = fileChangedNotificationService.handleFileChangedNotification(file);
		
		// Verify message ALWAYS returned 
		Assert.assertNotNull(message);
		
		// Verify message header has file name
		Assert.assertEquals(fileName, message.getHeaders().get("file.name"));

		// Verify error message in pay load
		String actualContent = (String) message.getPayload();
		Assert.assertEquals(true, actualContent.contains("Error reading file. Exception Message"));
	}
}