package com.rate.service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="rate")
public class RateDetails {
	@Id
	private int rateId;
	private float price;
	private float extensionPrice;
	
	public RateDetails() {
		super();
	}
	public RateDetails(int rateId, float price, float extensionPrice) {
		super();
		this.rateId = rateId;
		this.price = price;
		this.extensionPrice = extensionPrice;
	}
	public int getRateId() {
		return rateId;
	}
	public void setRateId(int rateId) {
		this.rateId = rateId;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public float getExtensionPrice() {
		return extensionPrice;
	}
	public void setExtensionPrice(float extensionPrice) {
		this.extensionPrice = extensionPrice;
	}
	@Override
	public String toString() {
		return "RateDetails [rateId=" + rateId + ", price=" + price + ", extensionPrice=" + extensionPrice + "]";
	}
	
	

}
