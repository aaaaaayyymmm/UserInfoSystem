package com.userInfoStrutsApp.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class TopAction extends Action{

	@Override
	public ActionForward execute(
			ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws IOException, ServletException {

		//登録画面から遷移してきた時用
		//登録画面での初期値を削除する
		HttpSession hs = request.getSession();
		hs.removeAttribute("editValue");
		hs.invalidate();

		//top画面へ遷移
		return mapping.findForward("top");
	}
}

