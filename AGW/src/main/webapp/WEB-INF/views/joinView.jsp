<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>사원 등록 요청 페이지</title>
		<script src="<c:url value='/resources/js/jquery-3.6.0.min.js' />"></script>
		<style type="text/css">
			h4 {
				display : inline-block;
			}
			
			div {
				min-height : 60px;
				height : auto;
			}
		</style>
	</head>
	<body>
		<div id="wrap">
			<div id="title">
				<h3 style="text-align:center">사원 등록 요청</h3>
				<br>
				<hr>
				<br>
			</div>
			<form id="joinForm" name="joinForm" method="post" action="<c:url value='/userJoin' />">
			
				<div id="insertInfo" style="text-align:center">
					<div id="inputId">
						<h4>아이디</h4><input type="text" id="usr_id" name="usr_id" placeholder="ID를 입력해주세요.">
					</div>
					<div id="inputPwd">
						<h4>비밀번호</h4>
						<input type="password" id="usr_pw" name="usr_pw" placeholder="비밀번호를 입력해주세요.">
					</div>
					<div id="inputPwdCheck">
						<h4>비밀번호 확인</h4>
						<input type="password" id="usr_pw_check" name="usr_pw_check" placeholder="비밀번호 확인">
					</div>
					<div id="inputName">
						<h4>이름</h4>
						<input type="text" id="usr_nm" name="usr_nm" placeholder="이름을 입력해주세요.">
					</div>
					<div id="inputEmail">
						<h4>E-MAIL</h4>
						<input type="text" id="usr_email" name="usr_email" placeholder="이메일을 입력해주세요.">@
						<select id="domain" name="domain">
							<option value="gmail.com">gmail.com</option>
						</select>
						<button id="sendMailBtn">인증</button>
					</div>
					<div id="certification">
						<h4>인증 번호</h4>
						<input type="text" id="certificationNum" placeholder="인증번호를 입력해주세요.">
						<h4 id="compareText"></h4>
					</div>
					<div id="inputGender">
						<h4>성별</h4>
						<input type="radio" name="usr_gender" value="남성" checked>남성
						<input type="radio" name="usr_gender" value="여성">여성
					</div>
						<input type="hidden" name="usr_position" value="사원">
						<input type="hidden" name="usr_right" value=1>
						<input type="hidden" name="usr_appr" value=0>
					<div id="inputPhone">
						<h4>휴대전화 번호</h4>
						<input type="text" name="usr_phone" placeholder="ex)01012341234">
					</div>
					<div id="inputAddress">
						<h4>주소</h4>
						<input type="text" name="usr_address" placeholder="ex)서울시 금천구 가산동">
					</div>
					<div id="submitReset">
						<input id="submitBtn" type="submit" value="가입">
						<input type="reset" value="취소">
					</div>
				</div>
			</form>
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
				return false;
			} else {
				mail = mail + "@" + domain;	//이메일 형식으로 변환
				
				$.ajax({
					type : 'post',
					url : mailUrl,
					async:false,
					data : {
						mail : mail	
					},
					success : function(response) {
						key = response;
					},
					error : function(request, status, error) {
						console.log("code:" + request.status + "\n" + "message:" + request.responseText + "\n" + "error:" + error);
					}
					
				});
				alert("작성하신 이메일로 인증번호를 전송했습니다.")
				return false;
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
		
		// 인증 여부 확인 + 유효성 검사
		$('#submitBtn').click(function() {
			if(isCertification == false) {		// 미 인증시
				alert("메일을 통한 본인 인증이 필요합니다.");
				return false;
				
			} else if($('#usr_id').val() == "") {
				alert("아이디를 입력해주세요.")
				$('#usr_id').focus();
				return false;
				
			} else if($('#usr_pw').val() == "") {
				alert("비밀번호를 입력해주세요.")
				$('#usr_pw').focus();
				return false;
				
			} else if($('#usr_pw').val() != $('#usr_pw_check').val()) {
				alert("비밀번호가 일치하지 않습니다.")
				$('#usr_pw_check').focus();
				return false;
				
			} else if($('#usr_nm').val() == "") {
				alert("이름을 입력해주세요.")
				$('#usr_nm').focus();
				return false;
				
			} else if($('#usr_email').val() == "") {
				alert("이메일을 입력해주세요.")
				$('#usr_email').focus();
				return false;
			} 
		});
		
		

		
	});
	</script>
</html>





