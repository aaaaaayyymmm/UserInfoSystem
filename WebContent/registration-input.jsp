<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.*" import="javax.servlet.http.HttpSession"
	import="com.userInfoStrutsApp.form.UserInfoForm"%>
<%
	HttpSession hs = request.getSession();
	UserInfoForm editValue = (UserInfoForm) hs.getAttribute("editValue");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザー情報管理 | 新規登録</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<style>
<jsp:include page="/common.css"/>
</style>
</head>
<body>
	<div class="container">
		<div class="header">
			<h2>新規登録</h2>
		</div>
		<div class="header home-link">
			<html:form action="/top">
				<button type="submit" class="btn btn-link m-2">Home</button>
			</html:form>
		</div>
		<p>全ての項目に入力してください</p>
		<html:form action="/registrationConfirm">
			<table class='table table-stripe'>
				<tr>
					<td>氏名</td>
					<td>
						<div class="input-name">
							<input type="text" name="last_name" value="<c:out value='${editValue.last_name}' />" placeholder="姓" /><br/>
							<html:errors property="last_name" />
						</div>
						<div class="input-name">
							<input type="text" name="first_name" value="<c:out value='${editValue.first_name}' />" placeholder="名" /><br/>
							<html:errors property="first_name" />
						</div>
					</td>
				</tr>
				<tr>
					<td>生年月日</td>
					<td>
						<select name="year" id="id_year"></select>年
						<select name="month" id="id_month"></select>月
						<select name="day" id="id_day"></select>日
					</td>
				</tr>
				<tr>
					<td>職業</td>
					<td>
						<input type="text" name="work" value="<c:out value='${editValue.work}' />"  /><br/>
						<html:errors property="work" />
					</td>
				</tr>
			</table>
			<button type="submit" class="btn btn-primary m-2">確認画面へ</button>
			<html:hidden property="edit-type" value="reg" />
		</html:form>
		<html:form action="/showInfo">
			<button type="submit" class="btn btn-link m-2" onclick="history.back()">一覧へ戻る</button>
		</html:form>
	</div>

	<script>
		(function() {
			'use strict';
			//日付データの初期値を変数todayに格納
			var optionLoop, this_day, this_month, this_year, today;
			today = new Date();
			this_year = <%=Integer.parseInt(editValue.getYear())%>;
			this_month = <%=Integer.parseInt(editValue.getMonth())%>;
			this_day = <%=Integer.parseInt(editValue.getDay())%>;

			//ループ処理（スタート数字、終了数字、表示id名、デフォルト数字）
			optionLoop = function(start, end, id, this_day) {
				var i, opt;
				opt = null;
				for (i = start; i <= end; i++) {
					if (i === this_day) {
						opt += "<option value='" + i + "' selected>" + i
								+ "</option>";
					} else {
						opt += "<option value='" + i + "'>" + i + "</option>";
					}
				}
				return document.getElementById(id).innerHTML = opt;
			};

			//関数設定（スタート数字[必須]、終了数字[必須]、表示id名[省略可能]、デフォルト数字[省略可能]）
			optionLoop(1950, this_year, 'id_year', this_year);
			optionLoop(1, 12, 'id_month', this_month);
			optionLoop(1, 31, 'id_day', this_day);
		})();
	</script>
</body>
</html>