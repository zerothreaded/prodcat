package com.williamhill.pds.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public abstract class OxiRepEntity {

	@Id
	protected String id;
	
	protected Map<String, Object> values = new HashMap<String, Object>();
	
	public Map<String, Object> getValues() {
		return values;
	}

	public void setValues(Map<String, Object> values) {
		this.values = values;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public List<String> splitRecord(String record) {
		List<String> result = new ArrayList<String>();
		int position = 0;
		while (position < record.length() - 1) {
			String field = "";
			if (record.charAt(position) == '"') {
				// Start of String sequence
				field += record.charAt(position++);
				while (record.charAt(position) != '"') {
					field += record.charAt(position++);
				}
				// Add the last quote too
				field += record.charAt(position++);
			} else {
				// Not a string field 
				while (record.charAt(position) != ','  && ((position < record.length() - 1))) {
					field += record.charAt(position++);
				}
				position++;
			}
			result.add(field);
			// System.out.println("Index " + result.size() + " Field = [" + field + "] Position = [" + position + "]");
		}
		return result;
	}
}
