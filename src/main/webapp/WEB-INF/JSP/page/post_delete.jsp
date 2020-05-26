<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="container blog-post-delete">
	<form action="#" method="POST">
		<h3>Are you sure you wnt to delete Post - <b>${title}</b></h3>
		<div class="buttons-delete">
			<a href="/post/detail?post=${slug}" class="btn btn-primary">Cancel</a>
			<form action="post/delete" method="post">
				<input type="submit" name='button' class="btn btn-danger" value="Delete">
			</form>
		</div>
	</form>
</div>