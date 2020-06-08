package com.userInfoStrutsApp.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.userInfoStrutsApp.business.UserInfoDAO;
import com.userInfoStrutsApp.form.UserInfoForm;

public class DeleteAction extends Action {

	@Override
	public ActionForward execute(
			ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws IOException, ServletException {
		//削除対象のユーザIDを取得
		UserInfoForm userInfo = (UserInfoForm) form;
		int id = userInfo.getId();

		try {
			//ユーザ削除
			UserInfoDAO.getInstance().deleteUser(id);

		} catch (Exception e) {
			e.printStackTrace();
			//エラー発生の場合、エラー画面へ遷移
			return mapping.findForward("error");
		}
		//削除完了画面へ遷移
		return mapping.findForward("deleteComplete");
	}
}