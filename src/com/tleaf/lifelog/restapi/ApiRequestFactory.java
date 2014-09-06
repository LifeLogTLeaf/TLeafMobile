package com.tleaf.lifelog.restapi;


public class ApiRequestFactory implements RequestFactory {

	@Override
	public ApiRequest createRequest(RequestType requestType) {
		ApiRequest apiRequest = new ApiRequest();
		
		String url = "https://192.168.0.7:8080/api/";
		

		switch (requestType) {

		case POST_USER_SIGNUP:
			
			apiRequest.setRequestMethod("POST");
			apiRequest.setUrl( url + "user/" );

			break;

		default:
			break;

		}
		return apiRequest;
	}
}
