package com.prestonsproductions.alexandra.service;

/**
 * @author Preston Frazier
 *
 */
public interface HttpRequestService {

	public int sendGet(String base, String query, String authToken, StringBuffer response) throws Exception;

	public int sendPost(String base, String query, String data, String authToken, StringBuffer response) throws Exception;

}
