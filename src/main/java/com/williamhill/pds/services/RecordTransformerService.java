package com.williamhill.pds.services;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.integration.Message;
import org.springframework.integration.support.MessageBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class RecordTransformerService {

	public Message<?> transfomRawRecord(Message<?> message) {
		System.out.println("transfomRawRecord() called with message [" + message + "]");
		
		String xmlDocument = (String) message.getPayload();
		String csvContent = extractLevelNode(xmlDocument);
			
		// Build message from File Content
		Message<String> outGoingmessage = MessageBuilder.withPayload(csvContent).build();
		
		// TODO: Parse the OXIREP xml headers and insert into out message headers
        return outGoingmessage;
	}
	
	private String extractLevelNode(String xmlDocument) {
		try {
		    DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
	        documentFactory.setNamespaceAware(true);
	        DocumentBuilder builder = documentFactory.newDocumentBuilder();
			InputStream xmlStream = new ByteArrayInputStream(xmlDocument.getBytes("UTF-8"));
			Document document = builder.parse(xmlStream);
			NodeList nList = document.getElementsByTagName("level");
			// System.out.println("<level> Node List Length = " + nList.getLength());
			// There should only be 1 level so just access it
			Node nNode = nList.item(0);
			String content = nNode.getTextContent();
			// System.out.println("parseContentInCsvFormat() returning = [" + content + "]");
			return content;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
}
