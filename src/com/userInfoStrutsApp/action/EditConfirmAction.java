package com.userInfoStrutsApp.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.userInfoStrutsApp.form.UserInfoForm;

public class EditConfirmAction extends Action {

	final private String EDITTYPE_REGISTRATION ="reg";
	final private String EDITTYPE_UPDATE ="upd";
	final private String REGISTRATIONCONFIRM = "registrationConfirm";
	final private String UPDATECONFIRM = "updateConfirm";


	@Override
	public ActionForward execute(
			ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws IOException, ServletException {
		UserInfoForm userInfo = (UserInfoForm) form;

		//ユーザ情報テーブルの編集タイプを取得（登録or編集）
		String editType = request.getParameter("edit-type");

		//規定値以外のeditTypeの場合エラー画面へ遷移（基本起きない）
		if(!editType.equals(EDITTYPE_REGISTRATION) && !editType.equals(EDITTYPE_UPDATE)) return mapping.findForward("error");

		//editTypeの値からforwardPathを設定
		String forwardPath = editType.equals(EDITTYPE_REGISTRATION)? REGISTRATIONCONFIRM : UPDATECONFIRM;

		//登録or編集内容で表示する内容を、画面表示用にセットする
		userInfo.setLast_name(request.getParameter("last_name"));
		userInfo.setFirst_name(request.getParameter("first_name"));
		userInfo.setYear(request.getParameter("year"));
		userInfo.setMonth(request.getParameter("month"));
		userInfo.setDay(request.getParameter("day"));
		userInfo.setWork(request.getParameter("work"));

		//登録or編集内容の確認画面へ遷移
		return mapping.findForward(forwardPath);
	}

}
