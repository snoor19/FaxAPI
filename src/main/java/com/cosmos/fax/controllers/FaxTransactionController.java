package com.cosmos.fax.controllers;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cosmos.fax.config.AuthClient;
import com.cosmos.fax.models.FaxDetails;
import com.cosmos.fax.models.FaxHistoryData;
import com.cosmos.fax.models.FaxRequest;
import com.cosmos.fax.models.ResponseData;
import com.cosmos.fax.models.UploadPojo;
import com.cosmos.fax.services.StorageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ClickSend.ApiClient;
import ClickSend.ApiException;
import ClickSend.Api.FaxApi;
import ClickSend.Api.UploadApi;
import ClickSend.Model.FaxMessage;
import ClickSend.Model.FaxMessageCollection;
import ClickSend.Model.UploadFile;

@RestController
@RequestMapping("fax")
public class FaxTransactionController {

	Properties properties = null;
	public static Map<String, String> faxLocations = new HashMap<>();

	@Autowired
	StorageService storageService;

	public FaxTransactionController() {
		init();
	}

	private void init() {
		properties = new Properties();
		try {
			File file = ResourceUtils.getFile("classpath:config.properties");
			InputStream in = new FileInputStream(file);
			properties.load(in);
			System.out.println("Properties::" + properties);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	@PostMapping("/send")
	public ResponseData sendFax(@RequestBody FaxRequest faxRequest) {
		ResponseData response = null;
		FaxApi apiInstance = new FaxApi(AuthClient.getAuthInstance().apiClient);
		FaxMessage fax_message = new FaxMessage();
		fax_message.source("sdk");
		System.out.println("To::" + faxRequest.getTo() + ",From::" + faxRequest.getFrom() + ",country::"
				+ faxRequest.getCountry() + ",CustomString::" + faxRequest.getCustomString() + ", From Email::"
				+ faxRequest.getFromEmail());
		fax_message.to(faxRequest.getTo());
		fax_message.from(faxRequest.getFrom());
		fax_message.customString(faxRequest.getCustomString());
//		fax_message.country(faxRequest.getCountry());
		fax_message.fromEmail(faxRequest.getFromEmail());
		List<FaxMessage> faxMessageList = Arrays.asList(fax_message);
		// FaxMessageCollection | FaxMessageCollection model
		FaxMessageCollection faxMessage = new FaxMessageCollection();
		faxMessage.messages(faxMessageList);

		String fileUrl = uploadFile(faxRequest.getFileContent());
		System.out.println("fileURL::" + fileUrl);
		if (fileUrl == null || fileUrl.isEmpty()) {
			response = new ResponseData();
			response.setResponseCode("Failed");
			response.setHttpCode(200);
			response.setData("Failed to upload the fax content");
			return response;
		}

//		faxMessage.fileUrl("https://clicksend-api-downloads.s3.ap-southeast-2.amazonaws.com/_private/61AA2D47-A1A8-4ADC-AAC0-875C4DA11914.pdf?X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAWKMIV4OKLNEYMPU5%2F20210723%2Fap-southeast-2%2Fs3%2Faws4_request&X-Amz-Date=20210723T161436Z&X-Amz-SignedHeaders=host&X-Amz-Expires=1800&X-Amz-Signature=2c60fbf014e1277771aa7327661f1baac4eb3b5f11439827d283a83978ec713f");

		faxMessage.fileUrl(fileUrl);

		try {
			String result = apiInstance.faxSendPost(faxMessage);
			System.out.println(result);
			JSONObject jsonObject = new JSONObject(result);
			ObjectMapper mapper = new ObjectMapper();
			FaxDetails faxDetails = mapper.readValue(jsonObject.get("data").toString(), FaxDetails.class);
			System.out.println("Data of History::" + faxDetails);
			response = new ResponseData();
			response.setResponseCode("Success");
			response.setHttpCode(200);
			response.setData(faxDetails);
			return response;
		} catch (ApiException | JsonProcessingException | JSONException e) {
			System.err.println("Exception when calling FaxApi#faxSendPost::" + e.getMessage());
			e.printStackTrace();
			response = new ResponseData();
			response.setResponseCode(e.getMessage());
			response.setHttpCode(500);
		}
		return response;
	}

	@PostMapping("/viewhistory")
	public ResponseData getHistory(@RequestBody FaxRequest faxRequest) {
		ResponseData response = null;
		FaxApi apiInstance = new FaxApi(AuthClient.getAuthInstance().apiClient);
		Integer dateFrom = faxRequest.getStartDate() != null ? faxRequest.getStartDate() : null;
		Integer dateTo = faxRequest.getEndDate() != null ? faxRequest.getEndDate() : null;
		String q = faxRequest.getStatus() != null ? "status:" + faxRequest.getStatus() : null;
		String order = faxRequest.isDateDesc() ? "date_added:desc" : "date_added:asc"; // String | Order result by
																						// Example:
																						// date_added:desc,list_id:desc.
		Integer limit = faxRequest.getNoOfRecords() != null ? faxRequest.getNoOfRecords() : null; // Integer | Number of
																									// records per page
		System.out.println("Data :: datefrom::" + dateFrom + ", dateTo::" + dateTo + ", q::" + q + ", order::" + order
				+ ", noOfrecords::" + limit);
		try {
			String result = apiInstance.faxHistoryGet(dateFrom, dateTo, q, order, 1, limit);
			JSONObject jsonObject = new JSONObject(result);
			ObjectMapper mapper = new ObjectMapper();
			try {
				FaxHistoryData historyData = mapper.readValue(jsonObject.get("data").toString(), FaxHistoryData.class);
				System.out.println("Data of History::" + historyData);
				response = new ResponseData();
				response.setResponseCode("Success");
				response.setHttpCode(200);
				response.setData(historyData);
				return response;
			} catch (Exception e) {
				e.printStackTrace();
				response = new ResponseData();
				response.setResponseCode("SUCCESS");
				response.setHttpCode(500);
			}
			System.out.println(result);
		} catch (ApiException e) {
			System.err.println("Exception when calling FaxApi#faxHistoryGet");
			e.printStackTrace();
			response = new ResponseData();
			response.setResponseCode(e.getMessage());
			response.setHttpCode(500);
		}
		return response;
	}

	@GetMapping("/view/{message_id}")
	public ResponseData getFaxReceipt(@PathVariable String message_id) {
		FaxApi apiInstance = new FaxApi(AuthClient.getAuthInstance().apiClient);
		ResponseData response = null;
		// String | ID of the message receipt to retrieve
		try {
			String result = apiInstance.faxReceiptsByMessageIdGet(message_id);
			System.out.println(result);
			JSONObject jsonObject = new JSONObject(result);
			ObjectMapper mapper = new ObjectMapper();
			FaxDetails faxDetails = mapper.readValue(jsonObject.get("data").toString(), FaxDetails.class);
			System.out.println("Data of History::" + faxDetails);
			response = new ResponseData();
			response.setResponseCode("Success");
			response.setHttpCode(200);
			response.setData(faxDetails);
			return response;
		} catch (ApiException | JsonProcessingException | JSONException e) {
			System.err.println("Exception when calling FaxApi#faxReceiptsByMessageIdGet");
			e.printStackTrace();
			response = new ResponseData();
			response.setResponseCode(e.getMessage());
			response.setHttpCode(500);
		}
		return response;
	}

	public String uploadFile(String content) {
		UploadApi apiInstance = new UploadApi(AuthClient.getAuthInstance().apiClient);
		UploadFile uploadFile = new UploadFile(); // UploadFile | Your file to be uploaded
		uploadFile.content(content);
		String convert = "fax"; // String |
		String result = null;
		ResponseData response = null;
		try {
			result = apiInstance.uploadsPost(uploadFile, convert);
			JSONObject jsonObject = new JSONObject(result);
			ObjectMapper mapper = new ObjectMapper();
			UploadPojo uploadData = mapper.readValue(jsonObject.get("data").toString(), UploadPojo.class);
			System.out.println("Data of History::" + uploadData);
			response = new ResponseData();
			response.setResponseCode("Success");
			response.setData(uploadData);
			System.out.println(result);
			return uploadData.getUrl();
		} catch (ApiException | JsonProcessingException | JSONException e) {
			System.err.println("Exception when calling UploadApi#uploadsPost::" + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	@Scheduled(initialDelay = 1000, fixedRate = 60000)
	public void run() {
		try {
			System.out.println("Current time is :: " + Calendar.getInstance().getTime());
			FaxRequest faxRequest = new FaxRequest();
			faxRequest.setStatus("Received");
			faxRequest.setEndDate((int) (System.currentTimeMillis() / 1000));
			System.out.println("Start Time ::" + properties.getProperty("history.starttime"));
			faxRequest.setStartDate(Integer.valueOf(properties.getProperty("history.starttime")));
			faxRequest.setDateDesc(true);
			ResponseData data = getHistory(faxRequest);
			System.out.println("Response Data::" + data);
			properties.setProperty("history.starttime", faxRequest.getEndDate() + "");
			File file = ResourceUtils.getFile("classpath:config.properties");
			FileOutputStream output = new FileOutputStream(file);
			properties.store(output, "Configuration File");
			output.close();
			if (data != null && data.getData() != null) {
				FaxHistoryData faxHistoryData = (FaxHistoryData) data.getData();
				if (faxHistoryData.getData().size() > 0) {
					faxHistoryData.getData().forEach(fax -> {
						String faxNumber = fax.getTo();
						System.out.println("FaxNumber::" + faxNumber);
						String fileUrl = fax.getFileUrl();
						try (BufferedInputStream in = new BufferedInputStream(new URL(fileUrl).openStream()); 
								FileOutputStream fileOutputStream = new FileOutputStream("Test")) {   
							byte dataBuffer[] = new byte[1024];    
							int bytesRead;    
							while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {       
								fileOutputStream.write(dataBuffer, 0, bytesRead);    
								}
							} catch (IOException e) {
						}
						String path = null;
						if (!faxLocations.containsKey(faxNumber)) {
							final String uri = properties.getProperty("fax.getLocationAPI") + faxNumber.substring(1);
							RestTemplate restTemplate = new RestTemplate();
							String result = restTemplate.getForObject(uri, String.class);
							JSONObject jsonObject = new JSONObject(result);
							path = jsonObject.getString("path");
							System.out.println("Fax Location::" + path);
							faxLocations.put(faxNumber, path);
						} else {
							path = faxLocations.get(faxNumber);
							System.out.println("Checking FaxLocation from Map::" + path);
						}
						if (path != null && !path.isEmpty()) {
							String bucketName = path.split("/")[0];
							int startIndex = path.indexOf("/");
							String sitePath = path.substring(startIndex);
							System.out.println("bucketName::" + bucketName + ", sitePath::" + sitePath);
						}
					});
					File uploadFile = ResourceUtils.getFile("classpath:91FEFFDB-CF6A-471B-87ED-1058F86BF3E7.pdf");
					System.out.println("ClassPath URL::"+ResourceUtils.CLASSPATH_URL_PREFIX);
//					storageService.upload("cosmos-dev-s3", "cosmos-dev/Site_1646f8de8a4b4d9290b7e76811c4f455/Inbox/91FEFFDB-CF6A-471B-87ED-1058F86BF3E7.pdf", uploadFile);
				}
			}
			System.out.println("Start Time After Reqest::" + properties.getProperty("history.starttime"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	private String dowmloadFile(String url, String fileName) {
		try {
			String tmp_location = properties.getProperty("fax.tmpLocation");
			InputStream inputStream = new URL(url).openStream();
			Files.copy(inputStream, Paths.get(tmp_location+"\\"+fileName),
					StandardCopyOption.REPLACE_EXISTING);
			return tmp_location+"\\"+fileName;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * private void uploadFileTos3bucket(String fileName, File file) {
	 * s3client.putObject(new PutObjectRequest(bucketName, fileName, file)
	 * .withCannedAcl(CannedAccessControlList.PublicRead)); }
	 */

	
	public static void main(String[] args) {

		FaxTransactionController controller = new FaxTransactionController();
		String url = "https://clicksend-api-downloads.s3.ap-southeast-2.amazonaws.com/_private/D5FFECE9-1856-4F94-9430-76AE378F99B2.pdf?X-Amz-Content-Sha256=UNSIGNED-PAYLOAD&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAWKMIV4OKLNEYMPU5%2F20210728%2Fap-southeast-2%2Fs3%2Faws4_request&X-Amz-Date=20210728T072936Z&X-Amz-SignedHeaders=host&X-Amz-Expires=1800&X-Amz-Signature=b01e2762ba6f8b5fc18cf25345242c86f63947c5f7497e21af816067f48a731b";
		String fileName = url.substring(url.indexOf("_private/"),url.indexOf('?'));
		/* String result = controller.dowmloadFile(url,"test.pdf"); */
		
		System.out.println("Result ::" + fileName);
		
	}
	 

}
