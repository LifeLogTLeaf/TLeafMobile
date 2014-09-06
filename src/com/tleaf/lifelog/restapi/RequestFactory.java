package com.tleaf.lifelog.restapi;
/**
 * 2014.8.28
 * @author susu
 * Abstract Factory. Makes Requests
 */
public interface RequestFactory {
	public abstract Request createRequest( RequestType requestType );
}