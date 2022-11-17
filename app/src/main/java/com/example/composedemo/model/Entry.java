package com.example.composedemo.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Entry {

@SerializedName("API")
@Expose
private String api;
@SerializedName("Description")
@Expose
private String description;
@SerializedName("Auth")
@Expose
private String auth;
@SerializedName("HTTPS")
@Expose
private Boolean https;
@SerializedName("Cors")
@Expose
private String cors;
@SerializedName("Link")
@Expose
private String link;
@SerializedName("Category")
@Expose
private String category;

public String getApi() {
return api;
}

public void setApi(String api) {
this.api = api;
}

public String getDescription() {
return description;
}

public void setDescription(String description) {
this.description = description;
}

public String getAuth() {
return auth;
}

public void setAuth(String auth) {
this.auth = auth;
}

public Boolean getHttps() {
return https;
}

public void setHttps(Boolean https) {
this.https = https;
}

public String getCors() {
return cors;
}

public void setCors(String cors) {
this.cors = cors;
}

public String getLink() {
return link;
}

public void setLink(String link) {
this.link = link;
}

public String getCategory() {
return category;
}

public void setCategory(String category) {
this.category = category;
}

}
