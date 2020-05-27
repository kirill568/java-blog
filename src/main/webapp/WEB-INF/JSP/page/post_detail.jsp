<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="container blog-post">
	<h1 class="mt-4 m blog-post-title">
		${post.getTitle()}
		<a href="/post/delete?post=${post.getSlug()}"><img src="../../../static/img/trash.svg"></a>
		<a href="/post/update?post=${post.getSlug()}"><img src="../../../static/img/refresh-ccw.svg"></a>
	</h1>
	<p class="blog-post-meta">
		${post.getDatePub().substring(0, 10)}
		<a href="https://github.com/kirill568">Kirill</a>
	</p>
	<p>
		${post.getBody()}
	</p>
</div>