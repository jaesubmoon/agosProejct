<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>사용자 정보 수정 페이지</title>
	</head>
	<body>
		<div class="tableSpace">
			<span class="tableCol"><input type="checkbox" id="allCheck" name="allCheck" ></span>
			<span class="tableCol">이름</span>
			<span class="tableCol">직급</span>
			<span class="tableCol">권한</span>
			<span class="tableCol">성별</span>
			<span class="tableCol">ID</span>
			<span class="tableCol">E-mail</span>
		</div>
		<div class="tableContentSpace">
			<span class="tableCol"><input type="checkbox" name="rowCheck" value="${user.usr_idx }"></span>
			<span class="tableCol">${user.usr_nm}</span>
			<span class="tableCol">
				<label for="usr_position">
					<select id=usr_position name="rowCheck">
						<option value="${user.usr_position}">${user.usr_position}</option>
						<option value="관리자">관리자</option>
						<option value="사원">사원</option>
					</select>
				</label>
			</span>
			<span class="tableCol">
				<label for="usr_right">
					<select id="usr_right" name="rowCheck">
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
	</body>
</html>