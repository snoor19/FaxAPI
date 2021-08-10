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
@JsonPropertyOrder({ "country", "country_name", "dedicated_number", "price_setup", "price_monthly", "price_total" })
@Generated("jsonschema2pojo")
public class FaxContact {

	@JsonProperty("country")
	private String country;
	@JsonProperty("country_name")
	private String countryName;
	@JsonProperty("dedicated_number")
	private String dedicatedNumber;
	@JsonProperty("price_setup")
	private String priceSetup;
	@JsonProperty("price_monthly")
	private String priceMonthly;
	@JsonProperty("price_total")
	private String priceTotal;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("country")
	public String getCountry() {
		return country;
	}

	@JsonProperty("country")
	public void setCountry(String country) {
		this.country = country;
	}

	@JsonProperty("country_name")
	public String getCountryName() {
		return countryName;
	}

	@JsonProperty("country_name")
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	@JsonProperty("dedicated_number")
	public String getDedicatedNumber() {
		return dedicatedNumber;
	}

	@JsonProperty("dedicated_number")
	public void setDedicatedNumber(String dedicatedNumber) {
		this.dedicatedNumber = dedicatedNumber;
	}

	@JsonProperty("price_setup")
	public String getPriceSetup() {
		return priceSetup;
	}

	@JsonProperty("price_setup")
	public void setPriceSetup(String priceSetup) {
		this.priceSetup = priceSetup;
	}

	@JsonProperty("price_monthly")
	public String getPriceMonthly() {
		return priceMonthly;
	}

	@JsonProperty("price_monthly")
	public void setPriceMonthly(String priceMonthly) {
		this.priceMonthly = priceMonthly;
	}

	@JsonProperty("price_total")
	public String getPriceTotal() {
		return priceTotal;
	}

	@JsonProperty("price_total")
	public void setPriceTotal(String priceTotal) {
		this.priceTotal = priceTotal;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}