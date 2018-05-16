package com.fyber.api;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mohsen on 30/09/2016.
 */
public class Information {

    @SerializedName("virtual_currency_sale_enabled")
    private String virtualCurrencySaleEnabled;
    @SerializedName("support_url")
    private String supportUrl;
    @SerializedName("appid")
    private String appId;
    @SerializedName("virtual_currency")
    private String virtualCurrency;
    @SerializedName("language")
    private String language;
    @SerializedName("app_name")
    private String appName;
    @SerializedName("country")
    private String country;

    public String getVirtualCurrencySaleEnabled() {
        return virtualCurrencySaleEnabled;
    }

    public void setVirtualCurrencySaleEnabled(String virtualCurrencySaleEnabled) {
        this.virtualCurrencySaleEnabled = virtualCurrencySaleEnabled;
    }

    public String getSupportUrl() {
        return supportUrl;
    }

    public void setSupportUrl(String supportUrl) {
        this.supportUrl = supportUrl;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getVirtualCurrency() {
        return virtualCurrency;
    }

    public void setVirtualCurrency(String virtualCurrency) {
        this.virtualCurrency = virtualCurrency;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Information{" +
                "virtualCurrencySaleEnabled='" + virtualCurrencySaleEnabled + '\'' +
                ", supportUrl='" + supportUrl + '\'' +
                ", appId='" + appId + '\'' +
                ", virtualCurrency='" + virtualCurrency + '\'' +
                ", language='" + language + '\'' +
                ", appName='" + appName + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

}
