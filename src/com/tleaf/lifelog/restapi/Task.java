package com.tleaf.lifelog.restapi;

/**
 * 2014.8.28
 * 
 * @author susu An abstract Superclass for Task ( Intended to use Factory
 *         Patterns in Tasks ) 1. Remote CouchDb 2. Rest Server : ApiTask 3.
 *         Mobile TouchDb Callback Method to Receive Response needed
 */
public abstract class Task {
	RequestFactory requestFactory;

	public Task(RequestFactory requestFactory) {
		this.requestFactory = requestFactory;
	}

	public abstract void createRequest(RequestType requestType);

	public abstract void executeRequest();
}
