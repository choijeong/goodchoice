package com.hoon.goodchoice.memberdto;

import java.sql.Timestamp;

public class MemberDTO {

	private int indexkey;
	private String id_email;
	private String password;
	private String nickname;
	private String phone;
	private Timestamp reg_dttm;
	private String addr;
	private String addrdetail;
	
	

	public MemberDTO() {
		super();
	}

	
	//아이디체크
	public MemberDTO(String id_email) {
		super();
		this.id_email = id_email;
	}


	//회원정보보여주기
	public MemberDTO(int indexkey, String id_email) {
		super();
		this.indexkey = indexkey;
		this.id_email = id_email;
	}


	//로그인
	public MemberDTO(String id_email, String password) {
		super();
		this.id_email = id_email;
		this.password = password;
	}



	//탈퇴처리
	public MemberDTO(int indexkey, String id_email, String password) {
		super();
		this.indexkey = indexkey;
		this.id_email = id_email;
		this.password = password;
	}
	// 회원 가입, 수정
	public MemberDTO(int indexkey, String id_email, String password, String nickname, String phone, Timestamp reg_dttm,
			String addr, String addrdetail) {
		super();
		this.indexkey = indexkey;
		this.id_email = id_email;
		this.password = password;
		this.nickname = nickname;
		this.phone = phone;
		this.reg_dttm = reg_dttm;
		this.addr = addr;
		this.addrdetail = addrdetail;
	}


	public int getIndexkey() {
		return indexkey;
	}


	public void setIndexkey(int indexkey) {
		this.indexkey = indexkey;
	}


	public String getId_email() {
		return id_email;
	}


	public void setId_email(String id_email) {
		this.id_email = id_email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getNickname() {
		return nickname;
	}


	public void setNickname(String nickname) {
		this.nickname = nickname;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public Timestamp getReg_dttm() {
		return reg_dttm;
	}


	public void setReg_dttm(Timestamp reg_dttm) {
		this.reg_dttm = reg_dttm;
	}
	public String getAddr() {
		return addr;
	}


	public void setAddr(String addr) {
		this.addr = addr;
	}


	public String getAddrdetail() {
		return addrdetail;
	}


	public void setAddrdetail(String addrdetail) {
		this.addrdetail = addrdetail;
	}



	
	

	
}
