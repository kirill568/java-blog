<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="titleErrors" value="${errors.get('title')}"/>
<c:set var="slugErrors" value="${errors.get('slug')}"/>
<div class="container blog-tag-create">
	<h1 class="original">Создание нового тега</h1>
	<form action="" method="post">
		<div class="form-group">
			<label for="slugTitle">Загловок</label>
			<input type="text" class="form-control" name="title" value="${title}" id="slugTitle">
			<c:if test="${titleErrors.size() > 0 }">
				<div class="alert alert-danger">
					<c:forEach var="titleError" items="${titleErrors}" varStatus="counter">
						${titleError}
					</c:forEach>
                </div>
			</c:if>
		</div>
		<div class="form-group">
			<label for="slugSlug">URL</label>
			<input type="text" class="form-control" name="slug" value="${slug}" id="slugSlug">
			<c:if test="${slugErrors.size() > 0 }">
				<div class="alert alert-danger">
					<c:forEach var="slugError" items="${slugErrors}" varStatus="counter">
						${slugError}
					</c:forEach>
                </div>
			</c:if>
		</div>
		<button type="submit" class="btn btn-primary">Создать тег</button>
	</form>
</div>