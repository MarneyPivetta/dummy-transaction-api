package com.fiserv.dummy_transaction_api.core.domain;

import com.fiserv.dummy_transaction_api.core.application.transaction.enumeration.FilterDateType;

public class TransactionFilterTO {

	private FilterDateType dateType;
	private String date;
	private String startTime;
	private String endTime;
	private String posNumber;
	private String seClient;
	private String sitefStoreCode;


	public FilterDateType getDateType() {
		return dateType;
	}

	public void setDateType(FilterDateType dateType) {
		this.dateType = dateType;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getPosNumber() {
		return posNumber;
	}

	public void setPosNumber(String posNumber) {
		this.posNumber = posNumber;
	}

	public String getSeClient() {
		return seClient;
	}

	public void setSeClient(String seClient) {
		this.seClient = seClient;
	}

	public String getSitefStoreCode() {
		return sitefStoreCode;
	}

	public void setSitefStoreCode(String sitefStoreCode) {
		this.sitefStoreCode = sitefStoreCode;
	}
}
