<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="container container-post-list">
    <div class="row">
        <div class="col-6 offset-md-3">
            <h1 class="mb-5 mt-4">Posts:</h1>
            
            <jsp:include page="../pagination.jsp" flush="true"/>
        </div>
    </div>
</div>
