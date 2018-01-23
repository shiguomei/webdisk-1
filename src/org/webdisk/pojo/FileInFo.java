package org.webdisk.pojo;

import java.util.Date;

public class FileInFo {
	private String userName;
	private Integer id;
	private String fileName;
	private Long fileSize;
	private Date filaUploadDate;
	private String filePath;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Long getFileSize() {
		return fileSize;
	}
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
	public Date getFilaUploadDate() {
		return filaUploadDate;
	}
	public void setFilaUploadDate(Date filaUploadDate) {
		this.filaUploadDate = filaUploadDate;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}




}
