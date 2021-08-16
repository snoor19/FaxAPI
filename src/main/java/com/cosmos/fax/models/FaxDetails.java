package com.cosmos.fax.models;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * 
 * @author snoor
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "user_id", "subaccount_id", "list_id", "message_id", "to", "from", "carrier", "country",
		"custom_string", "schedule", "message_pages", "message_price", "status", "status_code", "status_text",
		"date_added", "from_email", "first_name", "last_name", "_file_url", "date", "_api_username" })
@Generated("jsonschema2pojo")
public class FaxDetails {

	@JsonProperty("user_id")
	private Integer userId;
	@JsonProperty("subaccount_id")
	private Integer subaccountId;
	@JsonProperty("list_id")
	private Integer listId;
	@JsonProperty("message_id")
	private String messageId;
	@JsonProperty("to")
	private String to;
	@JsonProperty("from")
	private String from;
	@JsonProperty("carrier")
	private String carrier;
	@JsonProperty("country")
	private String country;
	@JsonProperty("custom_string")
	private String customString;
	@JsonProperty("schedule")
	private String schedule;
	@JsonProperty("message_pages")
	private String messagePages;
	@JsonProperty("message_price")
	private String messagePrice;
	@JsonProperty("status")
	private String status;
	@JsonProperty("status_code")
	private String statusCode;
	@JsonProperty("status_text")
	private String statusText;
	@JsonProperty("date_added")
	private Integer dateAdded;
	@JsonProperty("from_email")
	private Object fromEmail;
	@JsonProperty("first_name")
	private Object firstName;
	@JsonProperty("last_name")
	private Object lastName;
	@JsonProperty("_file_url")
	private String fileUrl;
	@JsonProperty("date")
	private Object date;
	@JsonProperty("_api_username")
	private String apiUsername;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("user_id")
	public Integer getUserId() {
		return userId;
	}

	@JsonProperty("user_id")
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@JsonProperty("subaccount_id")
	public Integer getSubaccountId() {
		return subaccountId;
	}

	@JsonProperty("subaccount_id")
	public void setSubaccountId(Integer subaccountId) {
		this.subaccountId = subaccountId;
	}

	@JsonProperty("list_id")
	public Integer getListId() {
		return listId;
	}

	@JsonProperty("list_id")
	public void setListId(Integer listId) {
		this.listId = listId;
	}

	@JsonProperty("message_id")
	public String getMessageId() {
		return messageId;
	}

	@JsonProperty("message_id")
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	@JsonProperty("to")
	public String getTo() {
		return to;
	}

	@JsonProperty("to")
	public void setTo(String to) {
		this.to = to;
	}

	@JsonProperty("from")
	public String getFrom() {
		return from;
	}

	@JsonProperty("from")
	public void setFrom(String from) {
		this.from = from;
	}

	@JsonProperty("carrier")
	public String getCarrier() {
		return carrier;
	}

	@JsonProperty("carrier")
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	@JsonProperty("country")
	public String getCountry() {
		return country;
	}

	@JsonProperty("country")
	public void setCountry(String country) {
		this.country = country;
	}

	@JsonProperty("custom_string")
	public String getCustomString() {
		return customString;
	}

	@JsonProperty("custom_string")
	public void setCustomString(String customString) {
		this.customString = customString;
	}

	@JsonProperty("schedule")
	public String getSchedule() {
		return schedule;
	}

	@JsonProperty("schedule")
	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	@JsonProperty("message_pages")
	public String getMessagePages() {
		return messagePages;
	}

	@JsonProperty("message_pages")
	public void setMessagePages(String messagePages) {
		this.messagePages = messagePages;
	}

	@JsonProperty("message_price")
	public String getMessagePrice() {
		return messagePrice;
	}

	@JsonProperty("message_price")
	public void setMessagePrice(String messagePrice) {
		this.messagePrice = messagePrice;
	}

	@JsonProperty("status")
	public String getStatus() {
		return status;
	}

	@JsonProperty("status")
	public void setStatus(String status) {
		this.status = status;
	}

	@JsonProperty("status_code")
	public String getStatusCode() {
		return statusCode;
	}

	@JsonProperty("status_code")
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	@JsonProperty("status_text")
	public String getStatusText() {
		return statusText;
	}

	@JsonProperty("status_text")
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}

	@JsonProperty("date_added")
	public Integer getDateAdded() {
		return dateAdded;
	}

	@JsonProperty("date_added")
	public void setDateAdded(Integer dateAdded) {
		this.dateAdded = dateAdded;
	}

	@JsonProperty("from_email")
	public Object getFromEmail() {
		return fromEmail;
	}

	@JsonProperty("from_email")
	public void setFromEmail(Object fromEmail) {
		this.fromEmail = fromEmail;
	}

	@JsonProperty("first_name")
	public Object getFirstName() {
		return firstName;
	}

	@JsonProperty("first_name")
	public void setFirstName(Object firstName) {
		this.firstName = firstName;
	}

	@JsonProperty("last_name")
	public Object getLastName() {
		return lastName;
	}

	@JsonProperty("last_name")
	public void setLastName(Object lastName) {
		this.lastName = lastName;
	}

	@JsonProperty("_file_url")
	public String getFileUrl() {
		return fileUrl;
	}

	@JsonProperty("_file_url")
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	@JsonProperty("date")
	public Object getDate() {
		return date;
	}

	@JsonProperty("date")
	public void setDate(Object date) {
		this.date = date;
	}

	@JsonProperty("_api_username")
	public String getApiUsername() {
		return apiUsername;
	}

	@JsonProperty("_api_username")
	public void setApiUsername(String apiUsername) {
		this.apiUsername = apiUsername;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	@Override
	public String toString() {
		return "FaxDetails [userId=" + userId + ", subaccountId=" + subaccountId + ", listId=" + listId + ", messageId="
				+ messageId + ", to=" + to + ", from=" + from + ", carrier=" + carrier + ", country=" + country
				+ ", customString=" + customString + ", schedule=" + schedule + ", messagePages=" + messagePages
				+ ", messagePrice=" + messagePrice + ", status=" + status + ", statusCode=" + statusCode
				+ ", statusText=" + statusText + ", dateAdded=" + dateAdded + ", fromEmail=" + fromEmail
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", fileUrl=" + fileUrl + ", date=" + date
				+ ", apiUsername=" + apiUsername + ", additionalProperties=" + additionalProperties + "]";
	}
}
