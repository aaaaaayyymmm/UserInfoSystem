package com.userInfoStrutsApp.action;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
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

public class RegistrationInputAction extends Action {

	@Override
	public ActionForward execute(
			ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws IOException, ServletException {
		HttpSession hs = request.getSession();
		UserInfoForm editValue = (UserInfoForm) form;

		//他画面から遷移してきた時の初期値を設定する
		if(hs.getAttribute("editValue") == null) {
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(new Date());
			editValue.setLast_name("");
			editValue.setFirst_name("");
			editValue.setYear(Integer.toString(calendar.get(Calendar.YEAR)));
			editValue.setMonth(Integer.toString(calendar.get(Calendar.MONTH) + 1));
			editValue.setDay(Integer.toString(calendar.get(Calendar.DATE)));
			editValue.setWork("");

			hs.setAttribute("editValue", editValue);
			//登録画面へ遷移
			return mapping.findForward("registrationInput");
		}

		//入力エラーある時の画面用に初期値を設定
		editValue.setLast_name(request.getParameter("last_name"));
		editValue.setFirst_name(request.getParameter("first_name"));
		editValue.setYear(request.getParameter("year"));
		editValue.setMonth(request.getParameter("month"));
		editValue.setDay(request.getParameter("day"));
		editValue.setWork(request.getParameter("work"));

		hs.setAttribute("editValue", editValue);

		//登録画面へ遷移する
		return mapping.findForward("registrationInput");
	}

}
