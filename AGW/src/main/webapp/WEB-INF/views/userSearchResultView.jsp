<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>사원 정보 관리 페이지</title>
		<style type="text/css">
		
		</style>
	</head>
	<body>
			<div id="userListForm">
				<div id="linkSearchBox">
					<div>
						<button>사원관리</button>
						<button id="userRequestList">사원신청</button>
					</div>
					<div id="searchPlace">
						<form  name="searchForm" id="searchForm">	<!-- action에 공백으로 주면 현재 페이지 주소까지 넣은것과 같다. -->
						  <input type="hidden" name="nowPage" value="1">
						  <input type="text" name="searchKeyword" id="searchKeyword" placeholder="검색어를 입력해주세요" >
						  <input type="submit" value="검색">
					  	</form>	
					</div>
				</div>
				
				<div id="tableWholeSpace">
					<div class="tableSpace">
						<span class="tableCol"><input type="checkbox" id="allCheck" name="allCheck" ></span>
						<span class="tableCol">이름</span>
						<span class="tableCol">직급</span>
						<span class="tableCol">권한</span>
						<span class="tableCol">성별</span>
						<span class="tableCol">ID</span>
						<span class="tableCol">E-mail</span>
					</div>
						<form method="post" onsubmit="updateSubmit()">
							<c:forEach items="${userList }" var="user">
								<div class="tableContentSpace">
									<span class="tableCol"><input type="checkbox" name="rowCheck" value="${user.usr_idx }"></span>
									<span class="tableCol">${user.usr_nm}</span>
									<span class="tableCol">
										<label for="usr_position">
											<select id=usr_position name="usr_position">
												<option value="${user.usr_position}">${user.usr_position}</option>
												<option value="관리자">관리자</option>
												<option value="사원">사원</option>
											</select>
										</label>
									</span>
									<span class="tableCol">
										<label for="usr_right">
											<select id="usr_right" name="usr_right">
												<option value="${user.usr_right}">${user.usr_right}</option>
												<option value="0">관리자</option>
												<option value="1">일반</option>
											</select>
										</label>
									</span>
									<span class="tableCol">${user.usr_gender}</span>
									<span class="tableCol">${user.usr_id}</span>
									<span class="tableCol">${user.usr_email}</span>
								</div>
							</c:forEach>
							
						<div id="pageUpdateDelete">
							<!-- 페이지 넘기기 -->
							<div class="pagelist">
								<c:if test="${paging.startPage != 1 }">
									<a href="javascript:callAjax(${paging.startPage - 1 }, ${paging.cntPerPage});">‹</a>
								</c:if>
								<c:forEach begin="${paging.startPage }" end="${paging.endPage }" var="p">
									<c:choose>
										<c:when test="${p == paging.nowPage }">
											<b>${p }&emsp;</b>
										</c:when>
										<c:when test="${p != paging.nowPage }">
											<a href="javascript:callAjax(${p }, ${paging.cntPerPage});">${p }&emsp;</a>
										</c:when>
									</c:choose>
								</c:forEach>
								<c:if test="${paging.endPage != paging.lastPage }">
									<a href="javascript:callAjax(${paging.startPage + 1 }, ${paging.cntPerPage});">›</a>
								</c:if>
							</div>
							<div id="updateDelete">
								<input type="submit" id="updateBtn" value="수정">
								<button id="deleteBtn">삭제</button>
							</div>
						</div>
					</form>
				</div>
				<%-- ${userList } --%>
			</div>
	</body>
	<script type="text/javascript">
		function callAjax(nowPage, cntPerPage) {
			
			var urlSearch = "<c:url value='/userSearch' />"
			var searchType = $('#searchType').val();
			var searchKeyword = $('#searchKeyword').val();		
			$.ajax({
				type:"post",
				url: urlSearch,
				//data:formData,
				async: false,
				data:{
					'searchType':searchType,
					'searchKeyword':searchKeyword,
					'nowPage':nowPage,
					'cntPerPage':cntPerPage
				},				
				success:function(result) {			
					$("#userListForm").html(result);		
				},
				error:function(data, textStatus) {
					alert("전송 실패!");
				}
			});
			}
		

		var urlRequest = "<c:url value='/userRequestList' />"
	
		// 사원 신청 목록 페이지 불러오기
		$('#userRequestList').click(function() {
			event.preventDefault();  // 새로고침 막기
			
			$.ajax({
				type: "post",
				url: urlRequest,
				async: false,
				data: {
				},
				success:function(result) {
					$("#wrap").html(result);
				},
				error:function(data, textStatus) {
					alert("전송 실패!");
				}
			})
		});
		
		// 검색
		$('#searchForm').on('submit', function(){
			event.preventDefault();
		
			var urlSearch = "<c:url value='/userSearch' />"
			
			var searchType = $('#searchType').val();
			var searchKeyword = $('#searchKeyword').val();
			var nowPage = 1;
			var cntPerPage = 20;  		
			
			//alert(searchType);
			 $.ajax({
				type: "post",
				url: urlSearch,
				//data:formData,	
				data: {
					'searchType':searchType,
					'searchKeyword':searchKeyword,
					'nowPage':nowPage,
					'cntPerPage':cntPerPage
				},				
				success:function(result) {			
					$("#userListForm").html(result);
					
				},
				error:function(data, textStatus) {
					alert("전송 실패!");
				}
			});		
		});
	</script>
</html>