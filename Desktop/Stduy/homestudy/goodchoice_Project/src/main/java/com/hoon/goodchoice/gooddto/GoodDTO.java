package com.hoon.goodchoice.gooddto;

import java.sql.Timestamp;

public class GoodDTO {

	private int indexkey;
	private int member_indexkey;
	private String member_nickname;
	private int view_count;
	private int hit_count;
	private int type;
	private String title;
	private String contents;
	private Timestamp reg_dttm;

	//파일 업로드 할때 다수의 첨부파일 이름을 저장하기 위해 String[]로 만들었다.
	private String[] files;
	private int file_count;
	public GoodDTO() {
		super();
	}
	public String[] getFiles() {
		return files;
	}
	//file_count 따로 get 하지 않아도 된다 
	public void setFiles(String[] files) {
		this.files = files;
		setFile_count(files.length);
	}
	public void setFile_count(int file_count) {
		this.file_count = file_count;
	}
	public int getFile_count() {
		return file_count;
	}
	public int getIndexkey() {
		return indexkey;
	}

	public void setIndexkey(int indexkey) {
		this.indexkey = indexkey;
	}


	public int getMember_indexkey() {
		return member_indexkey;
	}


	public void setMember_indexkey(int member_indexkey) {
		this.member_indexkey = member_indexkey;
	}


	public String getMember_nickname() {
		return member_nickname;
	}


	public void setMember_nickname(String member_nickname) {
		this.member_nickname = member_nickname;
	}


	public int getView_count() {
		return view_count;
	}


	public void setView_count(int view_count) {
		this.view_count = view_count;
	}


	public int getHit_count() {
		return hit_count;
	}


	public void setHit_count(int hit_count) {
		this.hit_count = hit_count;
	}


	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getContents() {
		return contents;
	}


	public void setContents(String contents) {
		this.contents = contents;
	}


	public Timestamp getReg_dttm() {
		return reg_dttm;
	}


	public void setReg_dttm(Timestamp reg_dttm) {
		this.reg_dttm = reg_dttm;
	}
	
	@Override
	public String toString() {
		// userPage 값과 pageRec값을 문자열로 리턴해서 확인한다
		return "PagingBase [indexkey=" + indexkey + ", member_indexkey=" + member_indexkey + ", member_nickname=" + member_nickname + ", "
				+ "view_count=" + view_count + ", type=" + type+ ", title=" + title+ ", contents=" + contents + ", reg_dttm=" + reg_dttm + "]";
	}
	
}
