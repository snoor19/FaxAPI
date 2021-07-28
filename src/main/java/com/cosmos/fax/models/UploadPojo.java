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
@JsonPropertyOrder({
"upload_id",
"file_name",
"date_added",
"date_delete",
"user_id",
"_url",
"pages"
})
@Generated("jsonschema2pojo")
public class UploadPojo {

@JsonProperty("upload_id")
private Integer uploadId;
@JsonProperty("file_name")
private String fileName;
@JsonProperty("date_added")
private Integer dateAdded;
@JsonProperty("date_delete")
private Integer dateDelete;
@JsonProperty("user_id")
private Integer userId;
@JsonProperty("_url")
private String url;
@JsonProperty("pages")
private Object pages;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("upload_id")
public Integer getUploadId() {
return uploadId;
}

@JsonProperty("upload_id")
public void setUploadId(Integer uploadId) {
this.uploadId = uploadId;
}

@JsonProperty("file_name")
public String getFileName() {
return fileName;
}

@JsonProperty("file_name")
public void setFileName(String fileName) {
this.fileName = fileName;
}

@JsonProperty("date_added")
public Integer getDateAdded() {
return dateAdded;
}

@JsonProperty("date_added")
public void setDateAdded(Integer dateAdded) {
this.dateAdded = dateAdded;
}

@JsonProperty("date_delete")
public Integer getDateDelete() {
return dateDelete;
}

@JsonProperty("date_delete")
public void setDateDelete(Integer dateDelete) {
this.dateDelete = dateDelete;
}

@JsonProperty("user_id")
public Integer getUserId() {
return userId;
}

@JsonProperty("user_id")
public void setUserId(Integer userId) {
this.userId = userId;
}

@JsonProperty("_url")
public String getUrl() {
return url;
}

@JsonProperty("_url")
public void setUrl(String url) {
this.url = url;
}

@JsonProperty("pages")
public Object getPages() {
return pages;
}

@JsonProperty("pages")
public void setPages(Object pages) {
this.pages = pages;
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
