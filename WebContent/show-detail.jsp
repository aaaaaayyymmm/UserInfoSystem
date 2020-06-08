<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%
	String paraid = request.getParameter("id");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー情報管理 | ユーザー詳細</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<style>
<jsp:include page="/common.css"/>
</style>
<script type="text/javascript">
	function delete_msg() {
		var result = confirm('本当に削除しますか？');
		if (result) return true;
		return false;
	}
</script>
</head>
<body>
	<div class="container">
		<div class="header">
		<h2>ユーザ情報詳細</h2>
		</div>
		<div class="header home-link">
			<html:form action="/top">
				<button type="submit" class="btn btn-link m-2">Home</button>
			</html:form>
		</div>
		<html:form action="/showInfo">
			<button type="submit" class="btn btn-primary m-2" onclick="history.back()">一覧へ</button>
		</html:form>
		<html:form action="/updateInput">
			<button type="submit" class="btn btn-warning m-2">更新</button>
		</html:form>
		<table class='table table-stripe'>
			<tr>
				<td>ID</td>
				<td><bean:write name="userInfoForm" property="id" /></td>
			</tr>
			<tr>
				<td>氏名</td>
				<td><bean:write name="userInfoForm" property="last_name" /> <bean:write
						name="userInfoForm" property="first_name" /></td>
			</tr>
			<tr>
				<td>生年月日</td>
				<td><bean:write name="userInfoForm" property="birthday" /></td>
			</tr>
			<tr>
				<td>職業</td>
				<td><bean:write name="userInfoForm" property="work" /></td>
			</tr>
			<tr>
				<td>登録日時</td>
				<td><bean:write name="userInfoForm" property="created_at" /></td>
			</tr>
			<tr>
				<td>更新日時</td>
				<td><bean:write name="userInfoForm" property="updated_at" /></td>
			</tr>
		</table>
		<html:form action="/delete">
			<button type="submit" class="btn btn-secondary m-2"
				onclick="return delete_msg()">削除</button>
			<input type="hidden" name="delete_id" value="<%=paraid%>" />
		</html:form>
	</div>
</body>
</html>