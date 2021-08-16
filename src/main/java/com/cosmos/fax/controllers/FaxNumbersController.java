package com.cosmos.fax.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cosmos.fax.config.AuthClient;
import com.cosmos.fax.models.AvailableContactDetails;
import com.cosmos.fax.models.FaxContact;
import com.cosmos.fax.models.FaxDetails;
import com.cosmos.fax.models.FaxHistoryData;
import com.cosmos.fax.models.FaxRequest;
import com.cosmos.fax.models.ResponseData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ClickSend.ApiException;
import ClickSend.Api.NumberApi;

/**
 * 
 * @author snoor
 *
 */
@RestController
@RequestMapping("/numbers")
public class FaxNumbersController {
	
	ConcurrentMap<Long, FaxContact> contacts = new ConcurrentHashMap<>();
	
	@GetMapping("/{id}")
	public FaxContact getContact(@PathVariable long id) {
		return contacts.get(id);
	}
	
	@PostMapping("/getAvailableContacts")
	public Object getAvailableFaxContacts(@RequestBody FaxRequest faxRequest){
		
		if( faxRequest == null || faxRequest.getType() == null || faxRequest.getType().isEmpty() || faxRequest.getCountry() == null || faxRequest.getCountry().isEmpty()) {
			ResponseData response = new ResponseData();
			response.setResponseCode("Mandatory parameters are missing!!!");
			response.setHttpCode(400);
			return response;
		}
		
		if( faxRequest.isNeedDummyResponse()) {
			return createDummyAvailableContactResponse();
		}
		
		String queryString = "?type="+faxRequest.getType();
		 NumberApi apiInstance = new NumberApi(AuthClient.getAuthInstance().apiClient);
		    queryString = queryString + "&country=" + faxRequest.getCountry();
		    Integer page = faxRequest.getPage(); // Integer | Page number
		    if( page != null && page != 0)
		    	queryString = queryString + "&page=" + page;
		    Integer limit = faxRequest.getLimit(); // Integer | Number of records per page
		    if( limit != null && limit != 0)
		    	queryString = queryString + "&limit=" + limit;
		    try {
		    	RestTemplate restTemplate = new RestTemplate();
				HttpHeaders httpHeaders = new HttpHeaders();
				httpHeaders.set("authorization",
						"Basic cHVybmFjY0Bjb3Ntb3NjbGluaWNhbC5jb206NUVFMTk0N0UtMTc4RS1FMEU2LTA1NDAtRTIzN0VDOTY1RDY4");
				httpHeaders.set("Content-Type", "application/json");
				String url = "https://rest.clicksend.com/v3/numbers/search/US"+queryString;
				restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor("purnacc@cosmosclinical.com",
						"5EE1947E-178E-E0E6-0540-E237EC965D68"));
				ResponseEntity response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
				System.out.println("Response Data::" + response.getBody());
				String data = (String) response.getBody();
				System.out.println("Data Value ::"+data);
				JSONObject jsonObject = new JSONObject(data);
				ObjectMapper mapper = new ObjectMapper();
				AvailableContactDetails contactsData = mapper.readValue(jsonObject.get("data").toString(), AvailableContactDetails.class);
				ResponseData responseData = new ResponseData();
				responseData.setResponseCode("Success");
				responseData.setHttpCode(200);
				responseData.setData(contactsData);
				return responseData;
		    } catch (Exception e) {
		        System.err.println("Exception when calling NumberApi#numbersSearchByCountryGet");
		        e.printStackTrace();
		        ResponseData response = new ResponseData();
				response.setResponseCode(e.getMessage());
				response.setHttpCode(500);
				return response;
		    }
	}
	@PostMapping("/purchase/{dedicated_number}")
	public Object purchaseDedicatedNumber(@PathVariable String dedicated_number){
		ResponseData response = null;
		if( dedicated_number == null || dedicated_number.isEmpty()) {
			response = new ResponseData();
			response.setResponseCode("Please provide valid dedicated Number");
			response.setHttpCode(400);
			return response;
		}
		NumberApi apiInstance = new NumberApi(AuthClient.getAuthInstance().apiClient);
	    try {
	        String result = apiInstance.numbersBuyByDedicatedNumberPost(dedicated_number);
	        System.out.println(result);
	        JSONObject jsonObject = new JSONObject(result);
			ObjectMapper mapper = new ObjectMapper();
			FaxContact contact = mapper.readValue(jsonObject.get("data").toString(), FaxContact.class);
			System.out.println("Data of History::" + contact);
			response = new ResponseData();
			response.setResponseCode("Success");
			response.setHttpCode(200);
			response.setData(contact);
			return response;
	    } catch (ApiException | JsonProcessingException | JSONException e) {
	        System.err.println("Exception when calling NumberApi#numbersBuyByDedicatedNumberPost");
	        e.printStackTrace();
			response = new ResponseData();
			response.setResponseCode(e.getMessage());
			response.setHttpCode(500);
			return response;
	    }
	}
	
	
	private List<FaxContact> createDummyAvailableContactResponse() {
		return null;
	}

	public static void main(String[] args) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("authorization",
				"Basic cHVybmFjY0Bjb3Ntb3NjbGluaWNhbC5jb206NUVFMTk0N0UtMTc4RS1FMEU2LTA1NDAtRTIzN0VDOTY1RDY4");
		httpHeaders.set("Content-Type", "application/json");
		String url = "https://rest.clicksend.com/v3/numbers/search/US?type=fax";
		restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor("purnacc@cosmosclinical.com",
				"5EE1947E-178E-E0E6-0540-E237EC965D68"));
		ResponseEntity response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
		System.out.println("Response Data::" + response.getBody());
	}
}
