<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="titleErrors" value="${errors.get('title')}"/>
<c:set var="slugErrors" value="${errors.get('slug')}"/>
<c:set var="bodyErrors" value="${errors.get('body')}"/>
<div class="container blog-post-update">
	<h1 class="original">Update post</h1>
	<form action="" method="post">
		<div class="form-group">
			<label for="postTitle">Title</label>
			<input type="text" name="title" class="form-control" id="postTitle" value="${title}">
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
			<input type="text" name="slug" class="form-control" id="postSlug" value="${slug}">
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
			<textarea class="form-control" name="body" id="postBody" rows="3">${body}</textarea>
			<script type="text/javascript">CKEDITOR.replace('postBody')</script>
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
				<c:forEach var="tag" items="${mapAllTags}">
					<c:if test="${mapLaterSelectTags.containsKey(tag.getKey())}">
						<option selected="" value="${tag.getKey()}">${tag.getValue()}</option>
					</c:if>
					<c:if test="${! mapLaterSelectTags.containsKey(tag.getKey())}">
						<option value="${tag.getKey()}">${tag.getValue()}</option>
					</c:if>
			 	</c:forEach>
			</select>
		</div>
		<button type="submit" class="btn btn-primary">Post Update</button>
	</form>
</div>