package com.tleaf.lifelog.restapi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.message.BasicNameValuePair;

/**
 * 2014.8.28
 * 
 * @author susu Contains Every Information Needed for Sending REST API Requests
 *         Works as an Command Object
 */
public class ApiRequest extends Request {

	private String url = null;
	private String requestMethod = null;
	
	// Values that are going to be written in URL in case using GET Method
	private List<BasicNameValuePair> basicNameValuePairs = new ArrayList<BasicNameValuePair>();
	
	// Values that are going to be written in Request Body using POST Method
	private Map<String,Object> keyValueMap = new HashMap<String,Object>();

	public Map<String, Object> getKeyValueMap() {
		return keyValueMap;
	}

	public void addKeyValue(String key, Object value) {
		this.keyValueMap.put(key, value);
	}

	public String getRequestMethod() {
		return requestMethod;
	}

	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void addBasicNameValuePair(BasicNameValuePair basicNameValuePair) {
		this.basicNameValuePairs.add(basicNameValuePair);
	}

	public List<BasicNameValuePair> getNameValuePairs() {
		return basicNameValuePairs;
	}

}
