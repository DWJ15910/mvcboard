<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>게시물 상세보기</h1>
	<table>
		<tr>
			<th>보드번호</th>
			<th>${boardOne.boardNo}</th>
		</tr>
		<tr>
			<th>지역명</th>
			<th>${boardOne.localName}</th>
		</tr>
		<tr>
			<th>제목</th>
			<th>${boardOne.boardTitle}</th>
		</tr>
		<tr>
			<th>내용</th>
			<th>${boardOne.boardContent}</th>
		</tr>
		<tr>
			<th>작성자 아이디</th>
			<th>${boardOne.memberId}</th>
		</tr>
		<tr>
			<th>파일목록</th>
			<th>${boardOne.originfileName}</th>
		</tr>
		<tr>
			<th>작성일</th>
			<th>${boardOne.createdate}</th>
		</tr>
		<tr>
			<th>수정일</th>
			<th>${boardOne.updatedate}</th>
		</tr>
	</table>
</body>
</html>