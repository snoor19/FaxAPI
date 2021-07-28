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

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "http_code", "response_code", "response_msg", "data" })
@Generated("jsonschema2pojo")
public class ResponseData {

	@JsonProperty("http_code")
	private Integer httpCode;
	@JsonProperty("response_code")
	private String responseCode;
	@JsonProperty("response_msg")
	private String responseMsg;
	@JsonProperty("data")
	private Object data;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("http_code")
	public Integer getHttpCode() {
		return httpCode;
	}

	@JsonProperty("http_code")
	public void setHttpCode(Integer httpCode) {
		this.httpCode = httpCode;
	}

	@JsonProperty("response_code")
	public String getResponseCode() {
		return responseCode;
	}

	@JsonProperty("response_code")
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	@JsonProperty("response_msg")
	public String getResponseMsg() {
		return responseMsg;
	}

	@JsonProperty("response_msg")
	public void setResponseMsg(String responseMsg) {
		this.responseMsg = responseMsg;
	}

	@JsonProperty("data")
	public Object getData() {
		return data;
	}

	@JsonProperty("data")
	public void setData(Object data) {
		this.data = data;
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
		return "ResponseData [httpCode=" + httpCode + ", responseCode=" + responseCode + ", responseMsg=" + responseMsg
				+ ", data=" + data + ", additionalProperties=" + additionalProperties + "]";
	}

}