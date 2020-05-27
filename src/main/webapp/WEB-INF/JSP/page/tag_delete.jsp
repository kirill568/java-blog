<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="container blog-tag-delete">
	<form action="#" method="POST">

		<h3>Этот тег будет удален - <b>${title}</b></h3>
		<div class="buttons-delete">
			<a href="/tags" class="btn btn-primary">Вернуться назад</a>
			<form action="" method="post">
				<input type="submit" name='button' class="btn btn-danger" value="Удалить тег">
			</form>
		</div>
	</form>
</div>