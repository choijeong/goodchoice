package com.hoon.goodchoice.gooddto;


public class UploadFileDTO {

	private int good_indexkey;
	private String fileName;
	private String reg_dttm;
	public UploadFileDTO() {
		super();
	}
	
	public UploadFileDTO(int good_indexkey, String fileName, String reg_dttm) {		super();
		this.good_indexkey = good_indexkey;
		this.fileName = fileName;
		this.reg_dttm = reg_dttm;
	
	}
	public int getGood_indexkey() {
		return good_indexkey;

	}
	public void setGood_indexkey(int good_indexkey) {
		this.good_indexkey = good_indexkey;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getReg_dttm() {
		return reg_dttm;
	}
	public void setReg_dttm(String reg_dttm) {
		this.reg_dttm = reg_dttm;
	}
	


	

	
	
}
