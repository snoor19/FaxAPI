package com.cosmos.fax.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "total", "per_page", "current_page", "last_page", "next_page_url", "prev_page_url", "from", "to",
		"contactsInfo" })
@Generated("jsonschema2pojo")
public class AvailableContactDetails {

	@JsonProperty("total")
	private Integer total;
	@JsonProperty("per_page")
	private Integer perPage;
	@JsonProperty("current_page")
	private Integer currentPage;
	@JsonProperty("last_page")
	private Integer lastPage;
	@JsonProperty("next_page_url")
	private Object nextPageUrl;
	@JsonProperty("prev_page_url")
	private Object prevPageUrl;
	@JsonProperty("from")
	private Integer from;
	@JsonProperty("to")
	private Integer to;
	@JsonProperty("contactsInfo")
	private List<FaxContact> contactsInfo = null;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("total")
	public Integer getTotal() {
		return total;
	}

	@JsonProperty("total")
	public void setTotal(Integer total) {
		this.total = total;
	}

	@JsonProperty("per_page")
	public Integer getPerPage() {
		return perPage;
	}

	@JsonProperty("per_page")
	public void setPerPage(Integer perPage) {
		this.perPage = perPage;
	}

	@JsonProperty("current_page")
	public Integer getCurrentPage() {
		return currentPage;
	}

	@JsonProperty("current_page")
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	@JsonProperty("last_page")
	public Integer getLastPage() {
		return lastPage;
	}

	@JsonProperty("last_page")
	public void setLastPage(Integer lastPage) {
		this.lastPage = lastPage;
	}

	@JsonProperty("next_page_url")
	public Object getNextPageUrl() {
		return nextPageUrl;
	}

	@JsonProperty("next_page_url")
	public void setNextPageUrl(Object nextPageUrl) {
		this.nextPageUrl = nextPageUrl;
	}

	@JsonProperty("prev_page_url")
	public Object getPrevPageUrl() {
		return prevPageUrl;
	}

	@JsonProperty("prev_page_url")
	public void setPrevPageUrl(Object prevPageUrl) {
		this.prevPageUrl = prevPageUrl;
	}

	@JsonProperty("from")
	public Integer getFrom() {
		return from;
	}

	@JsonProperty("from")
	public void setFrom(Integer from) {
		this.from = from;
	}

	@JsonProperty("to")
	public Integer getTo() {
		return to;
	}

	@JsonProperty("to")
	public void setTo(Integer to) {
		this.to = to;
	}

	@JsonProperty("contactsInfo")
	public List<FaxContact> getcontactsInfo() {
		return contactsInfo;
	}

	@JsonProperty("contactsInfo")
	public void setContactsInfo(List<FaxContact> contactsInfo) {
		this.contactsInfo = contactsInfo;
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
		return "AvailableContactDetails [total=" + total + ", perPage=" + perPage + ", currentPage=" + currentPage
				+ ", lastPage=" + lastPage + ", nextPageUrl=" + nextPageUrl + ", prevPageUrl=" + prevPageUrl + ", from="
				+ from + ", to=" + to + ", contactsInfo=" + contactsInfo + ", additionalProperties=" + additionalProperties + "]";
	}
}
