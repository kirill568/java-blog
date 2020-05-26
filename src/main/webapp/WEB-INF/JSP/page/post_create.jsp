<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="titleErrors" value="${errors.get('title')}"/>
<c:set var="slugErrors" value="${errors.get('slug')}"/>
<c:set var="bodyErrors" value="${errors.get('body')}"/>

<div class="container blog-post-create">
	<h1 class="original">Create new post</h1>

	<form action="/post/create" method="post">
		<div class="form-group">
			<label for="postTitle">Title</label>
			<input type="title" class="form-control" id="postTitle" name="title" value="${title}">
			<c:if test="${titleErrors.size() > 0 }">
				<div class="alert alert-danger">
					<c:forEach var="titleError" items="${titleErrors}" varStatus="counter">
						${titleError}
					</c:forEach>
                </div>
			</c:if>
		</div>
		<div class="form-group">
			<label for="postSlug">Slug</label>
			<input type="title" class="form-control" id="postSlug" name="slug" value="${slug}">
			<c:if test="${slugErrors.size() > 0 }">
				<div class="alert alert-danger">
					<c:forEach var="slugError" items="${slugErrors}" varStatus="counter">
						${slugError}
					</c:forEach>
                </div>
			</c:if>
		</div>
		<div class="form-group">
			<label for="postBody">Body</label>
			<textarea class="form-control" id="postBody" rows="3" name="body">${body}</textarea>
			<script type="text/javascript">CKEDITOR.replace('postBody'); CKEDITOR.config.entities = false; CKEDITOR.config.entities_latin = false;</script>
			<c:if test="${bodyErrors.size() > 0 }">
				<div class="alert alert-danger">
					<c:forEach var="bodyError" items="${bodyErrors}" varStatus="counter">
						${bodyError}
					</c:forEach>
                </div>
			</c:if>
		</div>
		<div class="form-group">
			<label for="postTags">Tags</label>
			<select multiple class="form-control" id="postTags" name="tags">
				<c:forEach var="tag" items="${tags}">
					<option value="${tag.getSlug()}">${tag.getTitle()}</option>
				</c:forEach>
			</select>
		</div>
		<button type="submit" class="btn btn-primary">Post Create</button>
	</form>
</div>