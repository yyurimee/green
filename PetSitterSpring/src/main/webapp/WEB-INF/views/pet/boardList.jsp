<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MY PETSI&nbsp;&#9786;</title>
<link rel="stylesheet" type="text/css" href="/css/boardList.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">  </head>
</head>
<body>
	<header>
	</header>
	
	<main>
		<div id="matching_result">
			<h3 class="title">new 매칭!</h3>
			<a href="#"><p class="more">&raquo;</p></a>
			<div class="clear"></div>
		</div>
		<div id="carouselExampleCaptions" class="carousel slide" data-bs-ride="carousel">
		  <div class="carousel-inner">
		    <div class="carousel-item active">
		      <img src="/img/profile1.jpg" class="d-block w-100" >
		      <div class="carousel-caption d-none d-md-block">
		        <h5><b>ID: 펫시터 아이디</b></h5>
		        <p><b>지역: <br>날짜: </b></p>
		      </div>
		    </div>
		    <div class="carousel-item">
		      <img src="/img/profile2.jpg" class="d-block w-100">
		      <div class="carousel-caption d-none d-md-block">
		        <h5><b>ID: 펫시터 아이디</b></h5>
		        <p><b>지역: <br>날짜: </b></p>
		      </div>
		    </div>
		    <div class="carousel-item">
		      <img src="/img/profile3.jpg" class="d-block w-100">
		      <div class="carousel-caption d-none d-md-block">
		      	<h5><b>ID: 펫시터 아이디</b></h5>
		        <p><b>지역: <br>날짜: </b></p>
		      </div>
		    </div>
		  </div>
		  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
		    <span class="carousel-control-prev" aria-hidden="true">&lsaquo;</span>
		    <span class="visually-hidden">Previous</span>
		  </button>
		  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
		    <span class="carousel-control-next" aria-hidden="true">&rsaquo;</span>
		    <span class="visually-hidden">Next</span>
		  </button>
		</div>
		<div class="clear"></div>
		
		<article id="board">
			<h2 class="title">Find : Pet Sitter</h2>
			<a href="/pet/write" title="Button fade orange" id="write" class="button btnFade btnOrange">글쓰기</a>
			<div class="clear"></div>
				<ul>
					<c:forEach var="boards" items="${boardslist}" varStatus="status">
					<a href="boards.nhn?action=getBoards&board_id=${boards.boardId}" class="text-decoration-none"><li>
						<div class="subject">[${status.count}] ${boards.boardTitle}</div>
						<div class="writer">${boards.boardWriter}</div>
						<div class="clear"></div>
						<div class="date">${boards.boardCreatedDate}</div></a>
					</li>
					<hr>
					</c:forEach>
				 	<a href="" title="Button fade orange" id="plus" class="button btnFade btnOrange">더보기</a>
				</ul>
				<c:if test="${error != null}">
					<div class="alert alert-danger alert-dismissible fade show mt-3">
							에러 발생: ${error}
						<button type="button" class="btn-close" data-bs-dismiss="alert"></button>
					</div>
				</c:if>
			<div class="clear"></div>
		</article>
		
		
	</main>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
	
</body>
</html>