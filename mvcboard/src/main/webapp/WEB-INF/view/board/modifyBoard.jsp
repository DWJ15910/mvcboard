<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>게시물 수정</h1>
	<form method="post" action="/board/modifyBoard">
		<table>
			<tr>
				<th>지역명</th>
				<th><input type="text" name="localName"></th>
			</tr>
			<tr>
				<th>제목</th>
				<th><input type="text" name="boardTitle"></th>
			</tr>
			<tr>
				<th>내용</th>
				<th>
					<textarea rows="3" cols="50" name="boardContent"></textarea>
				</th>
			</tr>
		</table>
		<input type="hidden" name="boardNo" value="${boardNo}">
		<input type="hidden" name="memberId" value="${memberId}">
		<button type="submit">수정하기</button>
	</form>
</body>
</html>