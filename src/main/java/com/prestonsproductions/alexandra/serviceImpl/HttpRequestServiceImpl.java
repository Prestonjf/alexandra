package com.prestonsproductions.alexandra.serviceImpl;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.prestonsproductions.alexandra.service.HttpRequestService;


/**
 * @author Preston Frazier
 *
 */
@Service("httpRequestService")
public class HttpRequestServiceImpl implements HttpRequestService {
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	final static String USER_AGENT = "Mozilla/5.0";
	
	@Override
	public int sendGet(String base, String query, String authToken, StringBuffer response) throws Exception {
		if (authToken != null) {
			query += "&access_token="+authToken;
		}
		URL url = query == null || query.isEmpty() ? new URL(base) : new URL(base + "?" + query);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", USER_AGENT);
		return send(null, response, con);
	}

	@Override
	public int sendPost(String base, String query, String data, String authToken, StringBuffer response)
			throws Exception {
		if (authToken != null) {
			query += "&access_token="+authToken;
		}
		URL url = query != null ? new URL(base + "?" + query) : new URL(base);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Content-Type", "application/json; charset=utf-8");
		return send(data, response, con);
	}

	protected int send(String data, StringBuffer response, HttpURLConnection con) {
		int responseCode = 0;
		LOGGER.info(con.getRequestMethod()+" Sending request to URL : "+con.getURL().toString() + " "+responseCode);
		try {
			if (data != null) {
				LOGGER.debug(data);
				con.setDoOutput(true);
				DataOutputStream wr = new DataOutputStream(con.getOutputStream());
				wr.writeBytes(data);
				wr.flush();
				wr.close();
			} else
				con.setDoOutput(false);
			responseCode = con.getResponseCode();
			LOGGER.info(con.getRequestMethod()+" Received response from URL : "+con.getURL().toString() + " "+responseCode);
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			readInput(in, response);
		} catch (Exception e) {
			if (con.getErrorStream() != null) {
				BufferedReader in = new BufferedReader(new InputStreamReader(con.getErrorStream()));
				readInput(in, response);
				LOGGER.error(response.toString(), e);
			}
		}
		if (responseCode > 299) LOGGER.error(response.toString());
		return responseCode;
	}
	
	private void readInput(BufferedReader in, StringBuffer response) {
		try {
			String inputLine;
			int i = 0;
			while ((inputLine = in.readLine()) != null) {
				if (i>0) response.append("\r");
				response.append(inputLine);
				i++;
			}
			in.close();
		} catch (Exception e) {
			LOGGER.error("", e);
		}
	}

}
