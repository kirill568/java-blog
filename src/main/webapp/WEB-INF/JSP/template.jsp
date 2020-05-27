<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
	integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
	integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
	crossorigin="anonymous"></script>
<script src="//cdn.ckeditor.com/4.14.0/standard/ckeditor.js"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="/static/css/style.css">
<title>Blog</title>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<span class="navbar-brand">Navbar</span>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="nav-link" href="/posts">Посты</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="/tags">Теги</a>
				</li>
				<li class="nav-item"><a class="nav-link"
					href="/post/create">создать новый пост</a></li>
				<li class="nav-item"><a class="nav-link"
					href="/tag/create">создать новый тег</a></li>
			</ul>
			<form class="form-inline my-2 my-lg-0" action="#">
				<input class="form-control mr-sm-2" type="search"
					placeholder="Search" aria-label="Search" name="search">
				<button class="btn btn-outline-success my-2 my-sm-0" type="submit">поиск</button>
			</form>
		</div>
	</nav>
	<jsp:include page="${currentPage}" flush="true"/>
	<footer>
		<div class="navbar navbar-light bg-light">
			<a class="navbar-brand" href="https://github.com/kirill568">Разработчик</a>
		</div>
	</footer>
	<script type="text/javascript" src="static/js/hello.js"></script>
</body>
</html>
