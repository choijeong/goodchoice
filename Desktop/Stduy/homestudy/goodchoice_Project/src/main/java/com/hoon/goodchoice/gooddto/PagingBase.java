package com.hoon.goodchoice.gooddto;

import org.springframework.web.util.UriComponentsBuilder;

//페이징 처리과정에 사용될 객체들 // 페이지번호와 페이지당 출력할 게시글 수를 관리한다
public class PagingBase {
	//페이징
	private int userPage; // 보여줄 페이지// 현재 페이지
	private int pageRec; // 페이지당 보여줄 레코드 수

	//검색
	private String searchType;
	private String keyword;

	//order by 글 정렬순
	private String goodorder;
	
	//게시글 타입별 목록부르기
	private int type;
	
	// 최초 default 생성자로 기본 객체 생성시 초기값을 지정해주었다// 1페이지, 5개씩 보여줌
	// 초기값 지정 아주 중요 **
	public PagingBase() {
		this.userPage = 1; // 사용자가 세팅하지 않으면 기본으로 1의 값을 갖는다
		this.pageRec = 5; // 페이지당 보여줄 기본 레코드 수는 5개 이다
		this.searchType = null;
		this.keyword = null;
		/*this.goodorder= ""; 기본설정 해두어서 안해도 된다*/
	}

	// limit 구문에서 시작부분에 필요한 값을 반환 함
	public int getPageStart() {
		System.out.println("PagingBase==userPage=" + userPage);
		return (this.userPage - 1) * this.pageRec;

	}
	/*
	 * public PagingBase(int userPage, int pageRec) { super(); this.userPage =
	 * userPage; this.pageRec = pageRec; }
	 */

	// getter setter
	
	
	public String getGoodorder() {
		return goodorder;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public void setGoodorder(String goodorder) {
		this.goodorder = goodorder;
	}
	
	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public int getUserPage() {
		return userPage;
	}

	public void setUserPage(int userPage) {
		if (userPage <= 0) {
			// 1페이지가 가장 최초값이므로 0보다 작거나 같으면 1로 설정
			this.userPage = 1;
		} else {
			this.userPage = userPage;
		}
	}

	public int getPageRec() {
		return pageRec;
	}

	public void setPageRec(int pageRec) {
		if (pageRec <= 0 || pageRec > 10) {
			// 보여줄 레코드수가 0보다 작거나 같을 경우 ||또는 5보다 크면 5개로 고정한다
			this.pageRec = 5;
		} else {
			this.pageRec = pageRec;
		}
	}

	public String makeQuery() {
		UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.newInstance()
				.queryParam("userPage", userPage)
				.queryParam("pageRec", this.pageRec)
				.queryParam("goodorder", this.goodorder);
				
		// 서치타입이 있으면 서치타입,키워드도 추가해서 보낸다 검색하지않았으면 페이징처리만 보낸다
		if (searchType != null) {
			uriComponentsBuilder.queryParam("searchType", this.searchType)
			.queryParam("keyword", this.keyword);
		}
		
		return uriComponentsBuilder.build().encode().toString();
	}

	@Override
	public String toString() {
		// userPage 값과 pageRec값을 문자열로 리턴해서 확인한다
		return "PagingBase [userPage=" + userPage + ", pageRec=" + pageRec + ", goodorder=" + goodorder + ", "
				+ "searchType=" + searchType + ", keyword=" + keyword + ", type="+ type+"]";
	}

}
