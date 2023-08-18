<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>게시판 삭제</h1>
	<form method="post" action="/board/removeBoard">
		<table>
			<tr>
				<th>아이디입력</th>
			</tr>
			<tr>
				<th><input type="text" name="memberId"></th>
			</tr>
		</table>
		<button type="submit">삭제</button>
	</form>
</body>
</html>