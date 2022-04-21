<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>사원 등록 요청 페이지</title>
		<script src="<c:url value='/resources/js/jquery-3.6.0.min.js' />"></script>
	</head>
	<body>
		<div id="wrap">
			<div id="title">
				<h3>사원 등록 요청</h3>
				<br>
				<hr>
				<br>
			</div>
			<div id="insertInfo">
				<div id="inputId">
					<input type="text" id="usr_id" name="usr_id" placeholder="ID를 입력해주세요.">
				</div>
				<div id="inputPwd">
					<input type="password" id="usr_pwd" name="usr_pwd" placeholder="비밀번호를 입력해주세요.">
				</div>
				<div id="inputPwdCheck">
					<input type="password" id="usr_pwd_check" name="usr_pwd_check" placeholder="비밀번호 확인">
				</div>
				<div id="inputName">
					<input type="text" id="usr_nm" name="usr_nm" placeholder="이름을 입력해주세요.">
				</div>
				<div id="inputEmail">
					<input type="text" id="usr_email" name="usr_email" placeholder="이메일을 입력해주세요.">@
					<select id="domain" name="domain">
						<option value="gmail.com">gmail.com</option>
					</select>
					<button id="sendMailBtn">인증</button>
				</div>
				<div id="certification">
					<input type="text" id="certificationNum"><h4 id="compareText"></h4>
				</div>
				<div id="inputGender">
					<input type="radio" name="usrGender" value="남성" checked>남성
					<input type="radio" name="usrGender" value="여성">여성
				</div>
				<div id="submitReset">
					<input id="submitBtn" type="submit" value="가입">
					<input type="reset" value="취소">
				</div>
			</div>
		</div>
	</body>
	<script type="text/javascript">
	
	$(document).ready(function() {
		let key;
		let isCertification = false;
		
		// 메일 유효성 검사
		// 인증 버튼 클릭 시 인증번호를 이메일로 전송
		$('#sendMailBtn').click(function() {
			
			const mailUrl = "<c:url value='/checkMail' />"
			
			let mail = $("#usr_email").val();	//사용자의 이메일 입력 값
			
			let domain = $("#domain").val();
		
			if(mail == "") {
				alert("메일 주소를 입력해주세요.")
			} else {
				mail = mail + "@" + domain;	//이메일 형식으로 변환
				
				$.ajax({
					type : 'post',
					url : mailUrl,
					async:false,
					data : {
						mail : mail	
					},
					/* dataType : 'json', */
					success : function(response) {
						key = response;
					},
					error : function(request, status, error) {
						console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
					}
					
				});
				alert("작성하신 이메일로 인증번호를 전송했습니다.")
			}
			
		});
		
		// 인증 번호 확인
		$("#certificationNum").on("propertychange change keyup paste input", function() {
		
			if ($("#certificationNum").val() == key) {   //인증 키 값을 비교를 위해 텍스트인풋과 벨류를 비교
				$("#compareText").text("인증 성공!").css("color", "black");
				isCertification = true;  //인증 성공여부 check
			} else {
				$("#compareText").text("불일치!").css("color", "red");
				isCertification = false; //인증 실패
			}
		});
		
		// 인증 여부 확인
		$('#submitBtn').click(function() {
			if(isCertification == false) {		// 미 인증시
				alert("메일을 통한 본인 인증이 필요합니다.");
				return false;
			} else true;
		});
		
	});
	</script>
</html>





