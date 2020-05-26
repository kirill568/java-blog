<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="titleErrors" value="${errors.get('title')}"/>
<c:set var="slugErrors" value="${errors.get('slug')}"/>

<div class="container blog-post-create">
	<h1 class="original">Update tag</h1>

	<form action="" method="post">
		<div class="form-group">
			<label for="slugTitle">Title</label>
			<input type="text" class="form-control" id="slugTitle" name="title" value="${title}">
			<c:if test="${titleErrors.size() > 0 }">
				<div class="alert alert-danger">
					<c:forEach var="titleError" items="${titleErrors}" varStatus="counter">
						${titleError}
					</c:forEach>
                </div>
			</c:if>
		</div>
		<div class="form-group">
			<label for="slugSlug">Slug</label>
			<input type="text" class="form-control" id="slugSlug" name="slug" value="${slug}">
			<c:if test="${slugErrors.size() > 0 }">
				<div class="alert alert-danger">
					<c:forEach var="slugError" items="${slugErrors}" varStatus="counter">
						${slugError}
					</c:forEach>
                </div>
			</c:if>
		</div>
		<button type="submit" class="btn btn-primary">Tag update</button>
	</form>
</div>