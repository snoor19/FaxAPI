package com.cosmos.fax;

import ClickSend.ApiClient;
import ClickSend.ApiException;
import ClickSend.Configuration;
import ClickSend.Api.AccountApi;
import ClickSend.Api.FaxApi;
import ClickSend.auth.HttpBasicAuth;

public class AccountApiExample {

	public static void main(String[] args) {
		ApiClient defaultClient = Configuration.getDefaultApiClient();

		// Configure HTTP basic authorization: BasicAuth
		HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
		BasicAuth.setUsername("purnacc@cosmosclinical.com");
		BasicAuth.setPassword("5EE1947E-178E-E0E6-0540-E237EC965D68");

		/*
		 * AccountApi apiInstance = new AccountApi(); try { String result =
		 * apiInstance.accountGet(); System.out.println(result); } catch (ApiException
		 * e) { System.err.println("Exception when calling AccountApi#accountGet");
		 * e.printStackTrace(); }
		 */
		
		FaxApi apiInstance = new FaxApi(defaultClient);
        Integer dateFrom = 1546913229; // Integer | Customize result by setting from date (timestamp) Example: 1457572619.
        long currentTime = System.currentTimeMillis();
        Integer dateTo = (int) (currentTime/1000); // Integer | Customize result by setting to date (timestamp) Example: 1457573000.
        System.out.println("Date to::"+dateTo);
        String q = "status:Sent"; // String | Custom query Example: status:Sent,status_code:201.
        String order = "date_added:desc"; // String | Order result by Example: date_added:desc,list_id:desc.
        Integer page = 1; // Integer | Page number
        Integer limit = 10; // Integer | Number of records per page
        try {
            String result = apiInstance.faxHistoryGet(dateFrom, dateTo, q, order, page, limit);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling FaxApi#faxHistoryGet");
            e.printStackTrace();
        }
	}

}
