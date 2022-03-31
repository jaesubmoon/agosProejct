<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>사원 신청 리스트 페이지</title>
		<style type="text/css">
			#title {
				text-align: center;
			}
		
			#container {
				display: flex;
				width: 100%;
			}
			
			#userRequestForm {
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
			
			.RequestTableSpace {
				border: 1px solid black;
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
			
			.RequestTableSpace :nth-child(10n+1) {
				margin: 0%;
    			margin-right: -8%;
			}
			
			.RequestTableSpace :nth-child(10n+2) {
				width: 5%;
			}
			
			.RequestTableSpace :nth-child(10n+3) {
				width: 7%;
			}
			
			.RequestTableSpace :nth-child(10n+4) {
				width: 5%;
				text-align: center;
			}
			
			.RequestTableSpace :nth-child(10n+5) {
				width: 10%;
			}
			
			.RequestTableSpace :nth-child(10n+6) {
				width: 12%;
			}
			
			.RequestTableSpace :nth-child(10n+7) {
				width: 10%;
			}
			
			.RequestTableSpace :nth-child(10n+8) {
				width: 13%;
			}
			
			.RequestTableSpace :nth-child(10n+9) {
				width: 6%;
				text-align: center;
			}
			
			.RequestTableSpace :nth-child(10n+10) {
				width: 6%;
				text-align: center;
			}
			
			#userCheck {
				width: 13px !important;
			}
			
			div#apprReject {
			    float: right;
			    margin-top: 10px;
			}
			
			#apprBtn {
			    margin-right: 20px;
			    padding: 5px 10px 5px 10px;
			    border-radius: 10px;
			}
			
			#rejectBtn {
			    padding: 5px 10px 5px 10px;
			    border-radius: 10px;
			}
		</style>
	</head>
	<body>
		<div id="container">
			<div id="userRequestForm">
				<div>
						<button onClick="location.href='/agw/UserAllList'">사원관리</button>
						<button id="userRequestList">사원신청</button>
				</div>
				<form action="">
				<div id="tableWholeSpace">
					<div class="RequestTableSpace">
						<span class="tableCol"><input type="checkbox" id="userCheck" name="userCheck"></span>
						<span class="tableCol">이름</span>
						<span class="tableCol">생년월일</span>
						<span class="tableCol">성별</span>
						<span class="tableCol">연락처</span>
						<span class="tableCol">E-mail</span>
						<span class="tableCol">비상 연락처</span>
						<span class="tableCol">주소</span>
						<span class="tableCol">직급</span>
						<span class="tableCol">권한</span>
					</div>
					
					<c:forEach items="${userList }" var="user">
						<div class="RequestTableSpace">
							<span class="tableCol"><input type="checkbox" id="userCheck" name="userCheck"></span>
							<span class="tableCol">${user.usr_nm}</span>
							<span class="tableCol">1994-01-01</span>
							<span class="tableCol">${user.usr_gender}</span>
							<span class="tableCol">010-1234-1234</span>
							<span class="tableCol">${user.usr_email}</span>
							<span class="tableCol">010-2345-2345</span>
							<span class="tableCol">서울시 금천구 가산동</span>
							<span class="tableCol">
								<label for="usr_position">
									<select id="usr_position" name="usr_position">
										<option value="">직급 선택</option>
										<option value="관리자">관리자</option>
										<option value="사원">사원</option>
									</select>
								</label>
							</span>
							<span class="tableCol">${user.usr_right}</span>
						</div>
					</c:forEach> 
				</div>
				<div id="apprReject">
					<button id="apprBtn">승인</button>
					<button id="rejectBtn">거부</button>
				</div>
				</form>
			</div>
		
		</div>
	
	</body>
</html>