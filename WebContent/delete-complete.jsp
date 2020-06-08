<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー情報管理 | 削除完了</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<style>
<jsp:include page="/common.css"/>
</style>
</head>
<body>
	<div class="container">
		<h2>削除しました</h2>
		<html:form action="/showInfo">
			<button type="submit" class="btn btn-link m-2">一覧へ</button>
		</html:form>
	</div>
</body>
</html>