package com.currencyAdviser.model;

import java.util.HashMap;
import java.util.Map;
/*
  
 * 
 */
public class CurrencyClass {
	private boolean success;
	private String terms;
	private String privacy;
	private int timestamp;
	private String source;
    private Map<String, String> quotes= new HashMap();
    
    
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getTerms() {
		return terms;
	}
	public void setTerms(String terms) {
		this.terms = terms;
	}
	public String getPrivacy() {
		return privacy;
	}
	public void setPrivacy(String privacy) {
		this.privacy = privacy;
	}
	public int getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(int timestamp) {
		this.timestamp = timestamp;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public Map<String, String> getQuotes() {
		return quotes;
	}
	public void setQuotes(Map<String, String> quotes) {
		this.quotes = quotes;
	}
    
    
}
