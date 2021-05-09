package com.bookinventorymanagementapp.model;

public class GBItemsWrapper {
	private String id;
	private GBVolumeInfoWrapper volumeInfo;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public GBVolumeInfoWrapper getVolumeInfo() {
		return volumeInfo;
	}

	public void setVolumeInfo(GBVolumeInfoWrapper volumeInfo) {
		this.volumeInfo = volumeInfo;
	}
}
