package com.hoon.goodchoice.gooddto;

import org.springframework.web.util.UriComponentsBuilder;

public class PageClass {

	private int page_block = 5; // 보여지는 페이지의 갯수 // 하단 페이지 12345
	private int totalRec;// 전체 게시글 수 레코드 수
	private int page_startNum; // 보여지는 페이지 블럭의 첫 페이지번호
	private int page_endNum; // 보여지는 페이지 블럭의 마지막 페이지 번호
	private boolean prev; // 이전 버튼 유무 결정
	private boolean next; // 다음
	private boolean needSearch;
	private boolean needOrder;
	private int needType;

	private PagingBase pBase; // userPage와 pageRec가 있는 클래스

	private int pageAll;// 끝페이지 값// 전체페이지

	public PageClass(PagingBase pBase) {
		this.pBase = pBase;
	}

	public void setTotalRec(int totalRec) {
		this.totalRec = totalRec;
		calcData(); // 전체 필드 변수를 한번에 세팅 // 전체 게시글 수의 setter가 호출될 때 전체가 세팅
	}

	private void calcData() { // 전체 필드 변수 값들을 계산하는 메서드
		// Math.ceil() 함수는 주어진 숫자보다 크거나 같은 숫자 중 가장 작은 숫자를 integer 로 반환한다
		// 마지막 페이지번호 = 정수로 // 현재페이지 / 보여지는페이지갯수 *보여지는 페이지갯수 // ex) 3/5*5 =
		this.page_endNum = (int) (Math.ceil(pBase.getUserPage() / (double) page_block) * page_block);
		// page_endNum = ((pBase.getUserPage()-1)/page_block*page_block)+page_block;

		this.page_startNum = (page_endNum - page_block) + 1;
		// page_startNum=((pBase.getUserPage()-1)/page_block*page_block)+1;

		// 더블값을 사용해야 소숫점이 생겨 올림가능
		int pageAll = (int) (Math.ceil(totalRec / (double) pBase.getPageRec()));
		System.out.println("pageClass=pageAll==" + pageAll);
		System.out.println("pageClass=pBase.getPageRec()==" + pBase.getPageRec());
		System.out.println("pageClass=totalRec==" + totalRec);
		this.pageAll = pageAll;

		if (this.page_endNum > pageAll) {
			this.page_endNum = pageAll;
		}
		// 1페이지면 이전을 누를수 없다 // 노출 X/*//조건이 맞으면 false
		prev = page_startNum == 1 ? false : true;
		// 보여지는페이지끝번호 * 보여줄레코드수 >= 총 레코드수 맞으면 안보여줌 틀리면 보여줌
		next = page_endNum * pBase.getPageRec() >= totalRec ? false : true;
	}

	public String makeQuery(Integer userPage, boolean needSearch ,boolean needOrder, int needType) {
		UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.newInstance()
				.queryParam("userPage", userPage)
				.queryParam("pageRec", this.pBase.getPageRec());
				
		
		if(this.pBase.getGoodorder() != null) {
			uriComponentsBuilder.queryParam("goodorder", this.pBase.getGoodorder());
		}
		
		if (this.pBase.getSearchType() != null) {
			uriComponentsBuilder.queryParam("searchType", this.pBase.getSearchType())
			.queryParam("keyword",this.pBase.getKeyword());
		}
		
		if(this.pBase.getType() != 0) {
			uriComponentsBuilder.queryParam("type", this.pBase.getType());
		}
		// encode 한글 특문 입력가능
		return uriComponentsBuilder.build().encode().toString();
	}

	// getter setter

	public int getNeedType() {
		return needType;
	}
	public void setNeedType(int needType) {
		this.needType = needType;
	}
	public boolean isNeedOrder() {
		return needOrder;
	}
	public void setNeedOrder(boolean needOrder) {
		this.needOrder = needOrder;
	}
	
	public PagingBase getpBase() {
		return pBase;
	}

	public void setpBase(PagingBase pBase) {
		this.pBase = pBase;
	}

	public int getTotalRec() {
		return totalRec;
	}

	public int getPage_startNum() {
		return page_startNum;
	}

	public void setPage_startNum(int page_startNum) {
		this.page_startNum = page_startNum;
	}

	public int getPage_endNum() {
		return page_endNum;
	}

	public void setPage_endNum(int page_endNum) {
		this.page_endNum = page_endNum;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getPage_block() {
		return page_block;
	}

	public void setPage_block(int page_block) {
		this.page_block = page_block;
	}

	public int getPageAll() {
		return pageAll;
	}

	public void setPageAll(int pageAll) {
		this.pageAll = pageAll;
	}

	public PageClass() {
		super();
	}

	public boolean isNeedSearch() {
		return needSearch;
	}

	public void setNeedSearch(boolean needSearch) {
		this.needSearch = needSearch;
	}

	
}

/*
 * public String pagingQuery(int userPage) { UriComponents uriComponents =
 * UriComponentsBuilder.newInstance().queryParam("userPage", userPage)
 * .queryParam("pageRec", pBase.getPageRec()).build(); return
 * uriComponents.toUriString(); }
 * 
 * public String searchQuery(int searchType) { UriComponents uriComponents =
 * UriComponentsBuilder.newInstance() .queryParam("searchType", searchType)
 * .queryParam("keyword", pBase.getKeyword()).build(); return
 * uriComponents.toUriString();
 * 
 * }
 */
