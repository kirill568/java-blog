<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="container blog-tags-list">
    <h1 class="mb-5 mt-4">Теги:</h1>
    <c:forEach var="tag" items="${tags}">
        <h5>
        	<a href="/tag/detail?tag=${tag.getSlug()}" class="active">${tag.getTitle()}</a> 
        	<a href="/tag/delete?tag=${tag.getSlug()}"><img src="../../../static/img/trash.svg"></a> 
        	<a href="/tag/update?tag=${tag.getSlug()}"><img src="../../../static/img/refresh-ccw.svg"></a>
        </h5>
    </c:forEach>
    <jsp:include page="../pagination.jsp" flush="true"/>
</div>