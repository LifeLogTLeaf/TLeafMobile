package com.tleaf.lifelog.restapi;

import java.util.List;

import org.apache.http.message.BasicNameValuePair;

/**
 * 2014.8.28
 * 
 * @author susu Task for Rest Server API Requests
 */
public class ApiTask extends Task {

	ApiRequest apiRequest;

	public ApiTask(RequestFactory requestFactory) {
		super(requestFactory);
	}

	@Override
	public void createRequest(RequestType requestType) {
		apiRequest = (ApiRequest) super.requestFactory
				.createRequest(requestType);
	}

	@Override
	public void executeRequest() {
		ExecuteApiRequest executeApiRequest = new ExecuteApiRequest();
		executeApiRequest.execute(apiRequest);
	}

	// Manually Setting Values. A Factory Cannot Do this Job
	public void addBasicNameValuePairs(
			List<BasicNameValuePair> basicNameValuePairs) {
		for (BasicNameValuePair i : basicNameValuePairs)
			apiRequest.addBasicNameValuePair(i);
	}

	public void addBasicNameValuePair(BasicNameValuePair basicNameValuePair) {
		apiRequest.addBasicNameValuePair(basicNameValuePair);
	}

	public void addKeyValue(String key, Object value) {
		apiRequest.addKeyValue(key, value);
	}

}
