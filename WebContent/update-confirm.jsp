<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー情報管理 | 更新確認</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<style>
<jsp:include page="/common.css"/>
</style>
</head>
<body>
	<div class="container">
		<div class="header">
			<h2>更新確認</h2>
		</div>
		<div class="header home-link">
			<html:form action="/top">
				<button type="submit" class="btn btn-link m-2">Home</button>
			</html:form>
		</div>
		<p>以下の情報を登録しますか？</p>
		<html:form action="/editComplete">
			<table class='table table-stripe'>
				<tr>
					<td>氏名</td>
					<td>
						<bean:write name="userInfoForm" property="last_name" /> <bean:write name="userInfoForm" property="first_name" />
					</td>
				</tr>
				<tr>
				<td>生年月日</td>
				<td>
					<bean:write name="userInfoForm" property="year" />年
					<bean:write name="userInfoForm" property="month" />月
					<bean:write name="userInfoForm" property="day" />日
				</td>
				</tr>
				<tr>
					<td>職業</td>
					<td><bean:write name="userInfoForm" property="work" /></td>
				</tr>
			</table>
			<button type="submit" class="btn btn-primary m-2">更新</button>
			<html:hidden property="edit-type" value="upd" />
		</html:form>
		<button type="submit" class="btn btn-secondary m-2"
			onclick="history.back()">戻る</button>
	</div>
</body>
</html>