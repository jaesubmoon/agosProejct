<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>사원 신청 리스트 페이지</title>
		<link  href="<c:url value='/resources/css/userRequestListForm.css' />" rel="stylesheet"  type="text/css" >
	</head>
	<body>
		<div id="container">
			<div id="userRequestForm">
				<div id="linkBtn">
						<button onClick="location.href='/agw/UserAllList'">사원목록</button>
						<button id="userRequestList" style="color: white; background-color: dimgrey;">신청목록</button>
				</div>
				<div id="tableWholeSpace">
					<div class="RequestTableSpace">
						<span class="tableCol"><input type="checkbox" id="allCheck" name="allCheck" ></span>
						<span class="tableCol">이름</span>
						<span class="tableCol">생년월일</span>
						<span class="tableCol">성별</span>
						<span class="tableCol">연락처</span>
						<span class="tableCol">E-mail</span>
						<span class="tableCol">주소</span>
						<span class="tableCol">직급</span>
						<span class="tableCol">권한</span>
					</div>
					<div id="RequestTableWholeContent">
						<c:forEach items="${userList }" var="user">
							<div class="RequestTableContentSpace">
								<span class="tableCol"><input type="checkbox" name="rowCheck" value="${user.usr_idx }"></span>
								<span class="tableCol">${user.usr_nm}</span>
								<span class="tableCol"> <fmt:formatDate value="${user.usr_birth}" type="date" /></span>
								<span class="tableCol">${user.usr_gender}</span>
								<span class="tableCol">${user.usr_phone}</span>
								<span class="tableCol">${user.usr_email}</span>
								<span class="tableCol">${user.usr_address}</span>
								<span class="tableCol">
									<label for="usr_position">
										<select id="usr_position" name="usr_position">
											<option value="사원">사원</option>
											<option value="관리자">관리자</option>
										</select>
									</label>
								</span>
								<span class="tableCol">
									<label for="usr_right">
										<select id="usr_right" name="usr_right">
											<option value="1">일반</option>
											<option value="0">관리자</option>
										</select>
									</label>
								</span>
							</div>
						</c:forEach>
					</div>
				</div>
				<div class="pagination-wrapper clearfix">
                	<ul class="pagination float--right" id="pages">
                 	</ul>
                </div>
				<div id="apprReject">
					<button id="apprBtn">승인</button>
					<button id="rejectBtn">거부</button>
				</div>
			</div>
		
		</div>
	
	</body>
	<script type="text/javascript">
		$(document).ready(function() {
			
			// 페이징
			let totalData = document.getElementsByName("total").length;			// forEach 수  : 반복한 행의 수
			const dataPerPage = 10;															// 한 페이지에 보여줄 목록 수
			const pageCount = 5;																// 하단에 보여줄 페이지 버튼 수
			let currentPage = 1;
			
			console.log("currentPage : " + currentPage);
	        console.log("totalData : " + totalData);
			
			function paging(totalData, currentPage) {
				const totalPage = Math.ceil(totalData / dataPerPage);			// 총 페이지 수
				const pageGroup = Math.ceil(currentPage / pageCount);			// 페이지 그룹 ( 1~5 : 1그룹, 6~10 : 2그룹 개념)
				
				let last = pageGroup * pageCount;		// 화면에 보여질 마지막 페이지 번호
				if(last > totalPage) last = totalPage;	// 마지막 번호가 총 페이지 수보다 큰 경우 방지
				let first = last - (pageCount - 1);		// 화면에 보여질 첫번째 페이지 번호
				const next = last + 1;
				const prev = first - 1;
				
				if(totalPage < 1) first = last;
				
				const pages = document.getElementsById("pages");
				pages.empty();
				
				if (first > 5) {
		            pages.append("<li class=\"pagination-item\">" +
		                "<a onclick=\"GetTarget(" + (prev) + ");\" style='margin-left: 2px'>prev</a></li>");
		        }
		        for (let j = first; j <= last; j++) {
		            if (currentPage === (j)) {
		                pages.append("<li class=\"pagination-item\">" +
		                    "<a class='active' onclick=\"GetTarget(" + (j) + ");\" style='margin-left: 2px'>" + (j) + "</a></li>");
		            } else if (j > 0 ) {
		                pages.append("<li class=\"pagination-item\">" +
		                    "<a onclick=\"GetTarget(" + (j) + ");\" style='margin-left: 2px'>" + (j) + "</a></li>");
		            }
		        }
		        if (next > 5 && next < totalPage) {
		            pages.append("<li class=\"pagination-item\">" +
		                "<a onclick=\"GetTarget(" + (next) + ");\" style='margin-left: 2px'>next</a></li>");
		        }
		    }
			
			
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
			$('#rejectBtn').click(function() {
				event.preventDefault();
				
				var urlDelete = "<c:url value='/userSelectDelete' />"
				
				var chkArr = new Array();								// 체크된 행 배열을 넣을 새로운 배열
				var list = $('input[name="rowCheck"]');			// name이 rowCheck인 모든 input값을 넣는다
				for(var i = 0 ; i < list.length ; i++) {
					if(list[i].checked) {									// 선택되어 있다면 chkArr 배열에 값을 저장
						chkArr.push(list[i].value);						// list에 넣어둔 input의 value를 chkArr에 저장 : value 값이 usr_idx 즉, chkArr = 선택된 usr_idx의 배열
					}
				}
				
				if(chkArr.length == 0) {			// 체크된게 없어서 배열에 아무것도 들어가지 않았을 때
					alert("선택된 항목이 없습니다.");
				} else {
					var chkDelete = confirm("정말로 승인을 거부 하시겠습니까?");
					
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
								alert("승인 요청 삭제 완료");
								location.replace("/agw/userRequestList")			// userRequestList 새로고침
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
			 $('#apprBtn').click(function() {
				 event.preventDefault();
				
				 var urlAppr = "<c:url value='/userSelectApprove' />"
				 
				 var idxArr = new Array();
				 var idxList = $('input[name="rowCheck"]');
				 
				 for(var i=0 ; i < idxList.length ; i++ ) {
					 if(idxList[i].checked) {
						 idxArr.push(idxList[i].value);
					 }
				 }
				 
				 var positionArr = new Array();
				 var positionList = $('select[name="usr_position"]');
				 
				 for(var i=0 ; i < positionList.length ; i++ ) {
					if(idxList[i].checked) {
						 positionArr.push(positionList[i].value);
					}
				 }
				 
				 var rightArr = new Array();
				 var rightList = $('select[name="usr_right"]');
				 
				 for(var i=0 ; i < rightList.length ; i++ ) {
					if(idxList[i].checked) {
						 rightArr.push(rightList[i].value);
					 }
				 }
				 
				 if(idxArr.length == 0) {			// 체크된게 없어서 배열에 아무것도 들어가지 않았을 때
					alert("선택된 항목이 없습니다.");
				 } else {
					 var chkUpdate = confirm("정말로 승인하시겠습니까?");
					 
					 if(chkUpdate) {
					 
					/*  alert(idxArr);
					 alert(positionArr);
					 alert(rightArr); */ // 넘겨주는 값 확인
					 
					 $.ajax({
						 url : urlAppr,
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