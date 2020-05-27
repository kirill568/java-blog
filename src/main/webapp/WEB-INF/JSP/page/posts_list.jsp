<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="container container-post-list">
    <div class="row">
        <div class="col-6 offset-md-3">
            <h1 class="mb-5 mt-4">Посты:</h1>
            <c:forEach var="post" items="${posts}" varStatus="counter">
	            <div class="card mb-4 item">
				    <h5 class="card-header">${post.getDatePub().substring(0, 10)}</h5>
				    <div class="card-body">
				        <h5 class="card-title">${post.getTitle()}</h5>
				        <p class="card-text">
				        	<c:if test="${post.getBody().length() > 250}">
				        		${post.getBody().substring(0, 250).concat("...")}
				        	</c:if>

				        	<c:if test="${post.getBody().length() < 250}">
				        		${post.getBody()}
				        	</c:if>
				    	</p>
				        <a href="/post/detail?post=${post.getSlug()}" class="btn btn-primary">Читать</a>
				    </div>
				    <div class="card-footer text-muted">
				        Теги:
				        	<c:set var="tagsForPost" value="${tags.get(counter.count - 1)}"/>
				        	<c:forEach var="subTags" items="${tagsForPost}">
				            	<a href="/tag/detail?tag=${subTags.getSlug()}">${subTags.getTitle()}</a>
				            </c:forEach>
				    </div>
				</div>
			</c:forEach>
            <jsp:include page="../pagination.jsp" flush="true"/>
        </div>
    </div>
</div>
