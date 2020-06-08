<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.*" import="javax.servlet.http.HttpSession"
	import="com.userInfoStrutsApp.form.UserInfoForm"%>
<%
	@SuppressWarnings("unchecked")
	List<UserInfoForm> userList = (List<UserInfoForm>) request.getAttribute("userList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー情報管理 | 一覧</title>
<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/v/dt/dt-1.10.16/datatables.min.css" />
<script type="text/javascript"
	src="https://cdn.datatables.net/v/dt/dt-1.10.16/datatables.min.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<style>
<jsp:include page="/common.css"/>
</style>
</head>
<body>
	<div class="container-fluid">
		<div class="header">
			<h2>ユーザ情報一覧</h2>
		</div>
		<div class="header home-link">
			<html:form action="/top">
				<button type="submit" class="btn btn-link m-2">Home</button>
			</html:form>
		</div>
		<html:form action="/registrationInput">
			<button type="submit" class="btn btn-primary m-2">新規登録</button>
		</html:form>
		<table class='table table-stripe' id="userTable">
			<thead>
				<tr>
					<th>ID</th>
					<th>氏名</th>
					<th>生年月日</th>
					<th>職業</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="userInfo" items="${userList}">

					<tr>
						<td><html:link action="/showDetail" paramId="id"
								paramName="userInfo" paramProperty="id">
								<c:out value="${userInfo.id}" />
							</html:link></td>
						<td><c:out value="${userInfo.last_name}" /> <c:out
								value="${userInfo.first_name}" /></td>
						<td><c:out value="${userInfo.birthday}" /></td>
						<td><c:out value="${userInfo.work}" /></td>
					</tr>
				</c:forEach>
			<tbody>
		</table>
	</div>

	<!-- script------------------------------------------------------- -->
	<script>
		jQuery(function($) {
			$
				.extend(
						$.fn.dataTable.defaults,
						{
							language : {
								url : "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Japanese.json"
							}
						});
			$('#userTable').DataTable({
				"paging" : "true",//デフォルトでtrueなので、なくてもOK
				"pageLength" : 10,//初期表示件数
				"lengthMenu" : [ [ 5, 10, 15, -1 ], [ 5, 10, 15, "全" ] ],//表示件数メニュー
			});
		});
	</script>
</body>
</html>