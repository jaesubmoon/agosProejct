<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>사원 조회 페이지</title>
		<script src="././resources/js/jquery-3.6.0.min.js"></script>
		<style type="text/css">
		
			html {
			  /* 가로 스크롤 */
				overflow: auto;
				white-space: nowrap;
			}
			
			#title {
				text-align: center;
			}
		
			#wrap {
				display: flex;
				
			}
			
			#userListForm {
				margin: 0 auto;
				width: 1440px;
			}
			
			#searchPlace{
				float:  right;
			}
			
			#tableWholeSpace {
			    border: 1px solid;
			    height: 621px;
			    margin-top: 10px;
			}
			
			.tableSpace {
				border: 2px solid black;
			    background: gainsboro;
			    padding-top: 8px;
			    padding-bottom: 8px;
			}
			
			.tableContentSpace {
				border-bottom: 1px solid black;
			    padding-top: 5px;
			    padding-bottom: 5px;
			}
			
			.tableCol {
				display: inline-block;
			    padding-right: 15px;
			    padding-left: 15px;
			    width: 10%;
			}
			
			#tableChkBox {
				width: 5%;
			}
			
			.tableSpace :nth-child(7n+1) {
				margin: 0%;
    			margin-right: -70px;
			}
			
			.tableSpace :nth-child(7n+2) {
				width: 5%;
				margin-right: 50px;
			}
			
			.tableSpace :nth-child(7n+3) {
				width: 11%;
			}
			
			.tableSpace :nth-child(7n+4) {
				width: 12%;
				margin-right: 3%;
			}
			
			.tableSpace :nth-child(7n+5) {
				width: 13%;
			}
			
			.tableSpace :nth-child(7n+6) {
				width: 16%;
			}
			
			.tableSpace :nth-child(7n+7) {
				width: 10%;
			}
			
			.tableContentSpace :nth-child(7n+1) {
				margin: 0%;
    			margin-right: -70px;
			}
			
			.tableContentSpace :nth-child(7n+2) {
				width: 5%;
				margin-right: 50px;
			}
			
			.tableContentSpace :nth-child(7n+3) {
				width: 11%;
			}
			
			.tableContentSpace :nth-child(7n+4) {
				width: 12%;
				margin-right: 3%;
			}
			
			.tableContentSpace :nth-child(7n+5) {
				width: 13%;
			}
			
			.tableContentSpace :nth-child(7n+6) {
				width: 16%;
			}
			
			.tableContentSpace :nth-child(7n+7) {
				width: 10%;
			}
			
			#userCheck {
				width: 13px !important;
			}
			
			div#updateDelete {
			    float: right;
			    margin-top: 460px;
			}
			
			#updateBtn {
			    margin-right: 20px;
			    padding: 5px 10px 5px 10px;
			    border-radius: 10px;
			}
			
			#deleteBtn {
			    padding: 5px 10px 5px 10px;
			    border-radius: 10px;
			}
		</style>
	</head>
	<body>
		<div id="title">
			<h3>사원 정보 관리 페이지</h3>
			<br>
			<hr>
			<br>
		</div>
		<div id="wrap">
			<div id="userListForm">
				<div>
					<span>
						<button>사원관리</button>
						<button id="userRequestList">사원신청</button>
					</span>
					<span id="searchPlace">
						<input type="text" name="userSearchBox" placeholder="id 또는 이름을 입력해주세요.">
						<button name="searchBtn">검색</button>
					</span>
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
							
						<div id="updateDelete">
							<input type="submit" id="updateBtn" value="수정">
							<button id="deleteBtn">삭제</button>
						</div>
					</form>
				</div>
				<%-- ${userList } --%>
			</div>
		</div>
	</body>
	<script type="text/javascript">
		$(document).ready(function() {
			
			
			// 사원 신청 목록 페이지 불러오기
			$('#userRequestList').click(function() {
				event.preventDefault();  // 새로고침 막기
				
				$.ajax({
					type: "post",
					url: "/agw/userRequestList",
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
					// alert(chkArr); 	배열에 입력된 값 확인
					$.ajax({
						url : "/agw/userSelectDelete",		// 컨트롤러에서 삭제로 이동
						type : 'post',
						traditional : true, // ajax의 배열값을 java단으로 넘길 때  traditional : true
						data : {
							chkArr : chkArr	 // 배열 값을 data로 java단으로 넘김
						},
						success : function(jdata) {
							if(jdata = 1) {
								alert("삭제 완료");
								location.replace("UserAllList")			// userListForm 새로고침
							} else {
								alert("삭제 오류");
							}
						}
						
					})
				}
				
				
			});
			
			/* // 수정 버튼 클릭시 (보류)
			function updateSubmit() {
				event.updateSubmit();
				
				List<VO> chkArr = new ArrayList<VO>();
				var list = $('input[name="rowCheck"]');
				for(var i = 0 ; i < list.length ; i++ ) {
					if(list[i].checked) {
						chkArr.push(${userList}[i]);				//VO값을 제대로 저장하고 있는지 확인이 필요함;;
					}
				}
				
				if(chkArr.length == 0) {
					alert("선택된 항목이 없습니다.");
					
					$.ajax({
						url : "/agw/approveUser",
						type : 'post',
						traditional : true,
						data : {
							chkArr : chkArr
						},
						success : function(jdata) {
							if(jdata = 1) {
								alert("수정 완료");
								location.replace("UserAllList");
							} else {
								alert("수정 오류");
							}
						}
					});
				}
				
			} */
			
		});
	
	</script>
</html>