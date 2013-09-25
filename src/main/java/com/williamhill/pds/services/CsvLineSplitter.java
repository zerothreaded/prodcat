package com.williamhill.pds.services;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.springframework.integration.Message;

public class CsvLineSplitter {

	public List<String> splitCsvRecords(Message<?> message) {
		System.out.println("splitCsvRecords() called with [" + message + "]");
		List<String> outMessages = new ArrayList<String>();
		String csvContent = (String)message.getPayload();		
		String delimiter = "\n";
		StringTokenizer tokenizer = new StringTokenizer(csvContent, delimiter);
		while (tokenizer.hasMoreTokens()) {
			outMessages.add(tokenizer.nextToken());
		}
		return outMessages;
	}
}
