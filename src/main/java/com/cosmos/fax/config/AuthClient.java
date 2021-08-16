package com.cosmos.fax.config;

import java.io.Serializable;

import ClickSend.ApiClient;
import ClickSend.Configuration;
import ClickSend.auth.HttpBasicAuth;

/**
 * 
 * @author snoor
 *
 */
public class AuthClient implements Serializable {
	
	private static final long serialVersionUID = 2021610456638148529L;
	private static AuthClient instance = null;
	public static ApiClient apiClient = null;

	private AuthClient() {
	}

	public static AuthClient getAuthInstance() {
		if (instance == null) {
			synchronized (AuthClient.class) {
				instance = new AuthClient();
				createAuthClient();
			}
		}
		return instance;
	}

	private static void createAuthClient() {
		if (apiClient == null) {
			apiClient = Configuration.getDefaultApiClient();
			// Configure HTTP basic authorization: BasicAuth
			HttpBasicAuth BasicAuth = (HttpBasicAuth) apiClient.getAuthentication("BasicAuth");
			BasicAuth.setUsername("purnacc@cosmosclinical.com");
			BasicAuth.setPassword("5EE1947E-178E-E0E6-0540-E237EC965D68");
		}
	}
}