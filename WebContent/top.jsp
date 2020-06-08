<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー情報管理</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<style>
<jsp:include page="/common.css"/>
</style>
<body>
	<div class="top container">
		<h2 class="el-center">ユーザー情報管理システム</h2>
		<p class="el-center">made in Struts1.3</p>
		<html:form action="/showInfo">
			<button type="submit" class="btn btn-primary el-center" >管理画面へ</button>
		</html:form>
	</div>
</body>
</html>