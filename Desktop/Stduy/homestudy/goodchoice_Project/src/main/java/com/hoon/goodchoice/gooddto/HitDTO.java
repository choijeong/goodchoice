package com.hoon.goodchoice.gooddto;

public class HitDTO {

	private int indexkey;
	private int member_indexkey; 
	private int good_indexkey; 
	private int hit_check;
	
	public HitDTO() {
		super();
	}
	public HitDTO(int member_indexkey, int good_indexkey) {
		super();
		this.member_indexkey = member_indexkey;
		this.good_indexkey = good_indexkey;
	}
	public HitDTO(int indexkey, int member_indexkey, int good_indexkey, int hit_check) {
		super();
		this.indexkey = indexkey;
		this.member_indexkey = member_indexkey;
		this.good_indexkey = good_indexkey;
		this.hit_check = hit_check;
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
	public int getGood_indexkey() {
		return good_indexkey;
	}
	public void setGood_indexkey(int good_indexkey) {
		this.good_indexkey = good_indexkey;
	}
	public int getHit_check() {
		return hit_check;
	}
	public void setHit_check(int hit_check) {
		this.hit_check = hit_check;
	} 
	
	
}
