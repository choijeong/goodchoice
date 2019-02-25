package com.hoon.goodchoice.gooddto;

import java.sql.Timestamp;

public class GoodReplyDTO {

	private int indexkey;
	private int good_indexkey;
	private String member_nickname;
	private int hit_count;
	private int reply_origin;
	private int reply_group;
	private int reply_step;
	private String contents;
	private Timestamp reg_dttm;
	private Timestamp update_dttm;
	
	public GoodReplyDTO() {
		super();
	}
	
	
	
	public GoodReplyDTO(int good_indexkey, String member_nickname) {
		super();
		this.good_indexkey = good_indexkey;
		this.member_nickname = member_nickname;
	}



	public GoodReplyDTO(int good_indexkey, int reply_origin, int reply_group) {
		super();
		this.good_indexkey = good_indexkey;
		this.reply_origin = reply_origin;
		this.reply_group = reply_group;
	}



	public int getIndexkey() {
		return indexkey;
	}

	public void setIndexkey(int indexkey) {
		this.indexkey = indexkey;
	}

	public int getGood_indexkey() {
		return good_indexkey;
	}

	public void setGood_indexkey(int good_indexkey) {
		this.good_indexkey = good_indexkey;
	}

	public String getMember_nickname() {
		return member_nickname;
	}

	public void setMember_nickname(String member_nickname) {
		this.member_nickname = member_nickname;
	}

	public int getHit_count() {
		return hit_count;
	}

	public void setHit_count(int hit_count) {
		this.hit_count = hit_count;
	}

	public int getReply_origin() {
		return reply_origin;
	}

	public void setReply_origin(int reply_origin) {
		this.reply_origin = reply_origin;
	}

	public int getReply_group() {
		return reply_group;
	}

	public void setReply_group(int reply_group) {
		this.reply_group = reply_group;
	}

	public int getReply_step() {
		return reply_step;
	}

	public void setReply_step(int reply_step) {
		this.reply_step = reply_step;
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

	public Timestamp getUpdate_dttm() {
		return update_dttm;
	}

	public void setUpdate_dttm(Timestamp update_dttm) {
		this.update_dttm = update_dttm;
	}

	@Override
	public String toString() {
		return "GoodReplyDTO [indexkey="+indexkey
				+ ", good_indexkey=" +good_indexkey
				+ ", member_nickname = "+member_nickname
				+", hit_count = "+hit_count
				+", reply_origin = "+reply_origin
				+", reply_group = "+reply_group
				+", reply_step = "+reply_step
				+", contents = "+contents
				+", reg_dttm = "+reg_dttm
				+", update_dttm = "+update_dttm+"]";
	}
				
	
	
}
