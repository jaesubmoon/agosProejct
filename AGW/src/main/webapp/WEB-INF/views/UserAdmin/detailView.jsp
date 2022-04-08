<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>사용자 상세 정보</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
		<style type="text/css">
		
			html {
			  /* 가로 스크롤 */
				overflow: auto;
				white-space: nowrap;
			}
			
			h3 {
			    margin-top: 20px;
			    font-size: larger;
			    font-weight: bold;
			}
			
			h4 {
			    display: inline-block;
			}
			
			a {
				text-decoration : none;
			}
			
			label {
			    padding-right: 40px;
			}
			
			button {
			    border-radius: 5px;
			}
			div#container {
			    margin: 0 auto;
			    display: grid;
			}
		</style>
	</head>
	<body>
		<div id="container">
			<div>
			<c:forEach items="${userList }" var="user" >
			<div id="detailTable">
			<table class="table">
				<thead>
					<tr>
						<th rowspan="2"scope="col" style="border-top: 0px; border-bottom: 0px; text-align: center; padding: 20px;">사원 상세 정보</th>
					</tr>
				</thead>
				<tbody>
				<tr><th scope="row">이름</th><td>${user.usr_nm}</td></tr>
				<tr><th scope="row">ID</th><td>${user.usr_id}</td></tr>
				<tr><th scope="row">직급</th><td>${user.usr_position}</td></tr>
				<tr><th scope="row">성별</th><td>${user.usr_gender}</td></tr>
				<tr><th scope="row">생년월일</th><td><fmt:formatDate value="${user.usr_birth}" type="date" /></td></tr>
				<tr><th scope="row">연락처</th><td>${user.usr_phone}</td></tr>
				<tr><th scope="row">Email</th><td>${user.usr_email}</td></tr>
				<tr><th scope="row">주소</th><td>${user.usr_address}</td></tr>
				</tbody>
			</table>
			</div>
			</c:forEach>
			</div>
			<div id="AllBtn" style="margin: 0 auto;'">
				<button style="display: block;" onClick="location.href='/agw/UserAllList'">전체목록</button>
			</div>
		</div>
	</body>
</html>