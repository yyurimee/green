<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MY PETSI&nbsp;&#9786;</title>
<link rel="stylesheet" type="text/css" href="/css/boardWrite.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">  </head>
</head>
<body>
	<header>
	</header>
	
	<main>
		<div id="matching_result">
			<h3 class="title">글 작성</h3>
			<a href="#"><p class="more"></p></a>
			<div class="clear"></div>
		</div>
		<div class="card card-body" id="addForm">
			<form method="post" action="/pet/add" enctype="multipart/form-data">
				<label class="form-label">게시판</label>
				<select name="board_category" >
					<option value="find_pet">Find : Pet</option>
					<option value="find_sitter">Find : Sitter</option>
				</select>
				<label class="form-label">제목</label>
				<input type="text" name="boardTitle" class="form-control">
				<label class="form-label">작성자</label>
				<input type="text" name="boardWriter" class="form-control">
				<!-- 
				<label class="form-label">이미지</label>
				<input type="file" name="boardImg" class="form-control">
				 -->
				<label class="form-label">내용</label>
				<textarea cols="50" rows="5" name="boardContent" class="form-control"></textarea>
				<button type="submit" class="button btnFade btnOrange">저장</button>
			</form>
		</div>
	</main>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>