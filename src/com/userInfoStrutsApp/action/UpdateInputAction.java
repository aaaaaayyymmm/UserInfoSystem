package com.userInfoStrutsApp.action;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.userInfoStrutsApp.form.UserInfoForm;

public class UpdateInputAction extends Action {

	@Override
	public ActionForward execute(
			ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws IOException, ServletException {
		UserInfoForm userInfo = (UserInfoForm) form;

		//編集画面のselectbox表示用に、生年月日を分解する
		java.sql.Date birthday = userInfo.getBirthday();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(birthday);
		userInfo.setYear(Integer.toString(calendar.get(Calendar.YEAR)));
		userInfo.setMonth(Integer.toString(calendar.get(Calendar.MONTH) + 1));
		userInfo.setDay(Integer.toString(calendar.get(Calendar.DATE)));

		//編集画面の初期値をセッションに格納する
		HttpSession hs = request.getSession();
		hs.setAttribute("userInfo", userInfo);

		//編集画面へ遷移
		return mapping.findForward("updateInput");
	}

}
