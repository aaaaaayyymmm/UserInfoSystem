package com.userInfoStrutsApp.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.userInfoStrutsApp.business.UserInfoDAO;
import com.userInfoStrutsApp.form.UserInfoForm;

public class ShowInfoAction extends Action {

	@Override
	public ActionForward execute(
			ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws IOException, ServletException {

		//登録画面での初期値を削除する
		HttpSession hs = request.getSession();
		hs.removeAttribute("editValue");
		hs.invalidate();

		try {
			//ユーザ一覧を取得し、リクエストスコープに格納
			List<UserInfoForm> userList = UserInfoDAO.getInstance().selectAll();
			request.setAttribute("userList", userList);

		} catch (Exception e) {
			e.printStackTrace();
			//エラー発生の場合、エラー画面へ遷移
			return mapping.findForward("error");
		}
		//ユーザ一覧表示画面へ遷移
		return mapping.findForward("showInfo");
	}

}

