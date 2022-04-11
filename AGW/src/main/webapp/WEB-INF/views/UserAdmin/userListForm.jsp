<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
     
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>사원 조회 페이지</title>
		<link  href="<c:url value='/resources/css/userListForm.css' />" rel="stylesheet"  type="text/css" >
		<script src="<c:url value='/resources/js/jquery-3.6.0.min.js' />"></script>
	</head>
	<body>
		<div id="title">
			<h3>사원 관리 페이지</h3>
			<br>
			<hr>
			<br>
		</div>
		<div id="wrap">
			<div id="userListForm">
				<div id="linkSearchBox">
					<div>
						<button onClick="location.href='/agw/UserAllList'" style="color: white; background-color: dimgrey;">사원목록</button>
						<button id="userRequestList">신청목록</button>
						<h4>&nbsp;&nbsp;총 사원수 : ${total }</h4>
						<button id="excelBtn">엑셀 파일 생성</button>
					</div>
					<div id="searchPlace">
						<form  name="searchForm" id="searchForm">	<!-- action에 공백으로 주면 현재 페이지 주소까지 넣은것과 같다. -->
						  <select name="searchType" id="searchType">
						      <option value="search_id">ID</option>
						      <option value="search_name">이름</option>
						      <option value="search_id_name">ID + 이름</option>
						  </select>
						  <input type="hidden" name="nowPage" value="1">
						  <input type="text" name="searchKeyword" id="searchKeyword" placeholder="검색 키워드를 입력해주세요." >
						  <input type="submit" value="검색">
					  	</form>	
					</div>
				</div>
					<div id="tableWholeSpace">
						<div class="tableSpace">
							<label class="large-label" for="allCheck">
								<span class="tableCol"><input type="checkbox" id="allCheck" name="allCheck" ></span>
							</label>
							<span class="tableCol">이름</span>
							<span class="tableCol">직급</span>
							<span class="tableCol">권한</span>
							<span class="tableCol">성별</span>
							<span class="tableCol">ID</span>
							<span class="tableCol">E-mail</span>
						</div>
						<div id="tableWholeContent">
							<c:forEach items="${userList }" var="user" varStatus="status">
								<div class="tableContentSpace">
									<label for="rowCheck">
										<span class="tableCol"><input type="checkbox" name="rowCheck" value="${user.usr_idx }"></span>
									</label>
									<span class="tableCol"><a href="javascript:void(0);" onClick="return userDetail(${user.usr_idx});">${user.usr_nm}</a></span>
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
												<c:if test="${user.usr_right eq '관리자'}" >
													<option value="0">${user.usr_right}</option>
												</c:if>
												<c:if test="${user.usr_right eq '일반'}" >
													<option value="1">${user.usr_right}</option>
												</c:if>
												<option value="0">관리자</option>
												<option value="1">일반</option>
											</select>
										</label>
									</span>
									<span class="tableCol">${user.usr_gender}</span>
									<span class="tableCol">${user.usr_id}</span>
									<span class="tableCol">${user.usr_email}</span>
									<input type="hidden" name="total" value="${status.count}">
								</div>
							</c:forEach>
						</div>
					<div id="pageUpdateDelete">
						<!-- 페이지 넘기기 -->
						<div class="pagelist">
							<c:if test="${paging.startPage != 1 }">
								<a href="<c:url value='/UserAllList/?nowPage=${paging.startPage - 1 }&cntPerPage=${paging.cntPerPage}'/>">‹</a>
							</c:if>
							<c:forEach begin="${paging.startPage }" end="${paging.endPage }" var="p">
								<c:choose>
									<c:when test="${p == paging.nowPage }">
										<b>${p }&emsp;</b>
									</c:when>
									<c:when test="${p != paging.nowPage }">
										<a href="<c:url value='/UserAllList/?nowPage=${p }&cntPerPage=${paging.cntPerPage}'/>">${p }&emsp;</a>
									</c:when>
								</c:choose>
							</c:forEach>
							<c:if test="${paging.endPage != paging.lastPage }">
								<a href="<c:url value='/UserAllList/?nowPage=${paging.endPage + 1 }&cntPerPage=${paging.cntPerPage}'/>">›</a>
							</c:if>
						</div>
						<div id="updateDelete">
							<button id="updateBtn">수정</button>
							<button id="deleteBtn">삭제</button>
						</div>
					</div>
				</div>
				<%-- ${userList } --%>
			</div>
		</div>
		
	</body>
	<script type="text/javascript">
	
	// 사원 상세 정보
	function userDetail(usr_idx) {
		
		const urlDetail = "<c:url value='/userDetail' />" ;
		
		$.ajax({
			type:"post",
			url : urlDetail,
			data : {
				usr_idx : usr_idx
			},
			success: function(result) {
				$("#wrap").html(result);
			},
			error:function(data, testStatus) {
				alert("상세 정보 조회 실패");
			}
		})
	}
	
		$(document).ready(function() {
			
			const urlRequest = "<c:url value='/userRequestList' />"
			
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
			
			// 엑셀 파일 생성
			$('#excelBtn').click(function() {
				let searchType = $('#searchType').val();
				let searchKeyword = $('#searchKeyword').val();
				
				location.href = '/agw/excel/'+searchType+'/'+searchKeyword;
				
			});
			
			// 검색
			$('#searchForm').on('submit', function(){
				event.preventDefault();
				
				const urlSearch = "<c:url value='/userSearch' />"
				
				let searchType = $('#searchType').val();
				let searchKeyword = $('#searchKeyword').val();
				let nowPage = 1;
				const cntPerPage = 20;  		
				
				//alert(searchType);
				 $.ajax({
					type: "post",
					url: urlSearch,
					async: false,
					//data:formData,	
					data: {
						'searchType':searchType,
						'searchKeyword':searchKeyword,
						'nowPage':nowPage,
						'cntPerPage':cntPerPage
					},				
					success:function(result) {			
						$("#tableWholeSpace").html(result);
		
					},
					error:function(data, textStatus) {
						alert("전송 실패!");
					}
				});		
			});
			
			// 체크박스 설정
			var chkObj = document.getElementsByName("rowCheck");
			var chkCnt = chkObj.length;
			
			// 전체 선택
			$('input[name="allCheck"]').click(function() {
				var chk_listArr = $('input[name="rowCheck"]');
				for (var i = 0 ; i < chk_listArr.length; i++) {
					chk_listArr[i].checked = this.checked;
				}
			});
			
			// 체크된 수 = 전체 체크박스 수 일때만 allCheck가 체크 되도록 설정 (즉, 하나라도 체크박스가 꺼지면 allCheck도 꺼진다.)
			$('input[name="rowCheck"]').click(function() {
				if($('input[name="rowCheck"]:checked').length == chkCnt) {
					$('input[name="allCheck"]')[0].checked = true;
				} else {
					$('input[name="allCheck"]')[0].checked = false;
				}
			})
			
			// 삭제 버튼 클릭 시
			$('#deleteBtn').click(function() {
				event.preventDefault();
				
				var urlDelete = "<c:url value='/userSelectDelete' />";
				
				var chkArr = new Array();							// 체크된 행 배열을 넣을 새로운 배열
				var list = $('input[name="rowCheck"]');			// name이 rowCheck인 모든 input값을 넣는다
				for(var i = 0 ; i < list.length ; i++) {
					if(list[i].checked) {									// 선택되어 있다면 chkArr 배열에 값을 저장
						chkArr.push(list[i].value);						// list에 넣어둔 input의 value를 chkArr에 저장
					}
				}
				
				if(chkArr.length == 0) {			// 체크된게 없어서 배열에 아무것도 들어가지 않았을 때
					alert("선택된 항목이 없습니다.");
				} else {
					var chkDelete = confirm("정말로 삭제하시겠습니까?");
					
					if(chkDelete) {
					// alert(chkArr); 	배열에 입력된 값 확인
					$.ajax({
						url : urlDelete,		// 컨트롤러에서 삭제로 이동
						type : 'post',
						traditional : true, // ajax의 배열값을 java단으로 넘길 때  traditional : true
						data : {
							chkArr : chkArr	 // 배열 값을 data로 java단으로 넘김
						},
						success : function(jdata) {
							if(jdata = 1) {
								alert("삭제 완료");
								location.replace("")			// userListForm 새로고침
							} else {
								alert("삭제 오류");
							}
						}
					})
					} else {
						
					}
				}
			});
			
			 // 수정 버튼 클릭시_다중선택 업데이트 (선택 삭제 기능과 로직이 같다)
			 $('#updateBtn').click(function() {
				
				 const urlUpdate = "<c:url value='/userSelectUpdate' />"
				 
				 let idxArr = new Array();
				 let idxList = $('input[name="rowCheck"]');
				 
				 for(var i=0 ; i < idxList.length ; i++ ) {
					 if(idxList[i].checked) {
						 idxArr.push(idxList[i].value);
					 }
				 }
				 
				 let positionArr = new Array();
				 let positionList = $('select[name="usr_position"]');
				 
				 for(var i=0 ; i < positionList.length ; i++ ) {
					if(idxList[i].checked) {
						 positionArr.push(positionList[i].value);
					}
				 }
				 
				 let rightArr = new Array();
				 let rightList = $('select[name="usr_right"]');
				 
				 for(var i=0 ; i < rightList.length ; i++ ) {
					if(idxList[i].checked) {
						 rightArr.push(rightList[i].value);
					 }
				 }
				 
				 if(idxArr.length == 0) {			// 체크된게 없어서 배열에 아무것도 들어가지 않았을 때
					alert("선택된 항목이 없습니다.");
				 } else {
					 let chkUpdate = confirm("정말로 수정하시겠습니까?");
					 
					 if(chkUpdate) {
					 
					 /*  alert(idxArr);
					 alert(positionArr);
					 alert(rightArr);  // 넘겨주는 값 확인 */
					 
					 $.ajax({
						 url : urlUpdate,
						 type : 'post',
						 traditional : true,
						 data : {
							 idxArr : idxArr,
							 positionArr : positionArr,	
							 rightArr : rightArr,
						  },
						  success : function(jdata) {
							  if(jdata = 1) {
								  alert("수정 완료");
								  location.replace("")
							  } else {
								  alert("수정 오류");
							  }
						  }
							  
						  });
					 }
					 }
				 
			 });
	
		});  //$(document).ready
	
	</script>
</html>