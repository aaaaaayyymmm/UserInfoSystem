package com.userInfoStrutsApp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.userInfoStrutsApp.business.UserInfoDAO;
import com.userInfoStrutsApp.form.UserInfoForm;

public class ShowDetailAction extends Action {

	@Override
	public ActionForward execute(
			ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		UserInfoForm userInfo = (UserInfoForm) form;

		//詳細表示対象のユーザIDを取得
		int paraId = Integer.parseInt(request.getParameter("id"));

		//IDからユーザ詳細を取得
		UserInfoForm findUser = UserInfoDAO.getInstance().findUserById(paraId);

		//存在しないIDや削除済みユーザIDが指定された場合、エラー画面へ遷移
		//その場合、姓の情報はブランクなので(daoで設定)、姓がブランクであることを確認する
		if(findUser.getLast_name().equals("")) return mapping.findForward("error");

		//取得したユーザ詳細を、画面表示用formBeanにセットする
		userInfo.setLast_name(findUser.getLast_name());
		userInfo.setFirst_name(findUser.getFirst_name());
		userInfo.setBirthday(findUser.getBirthday());
		userInfo.setWork(findUser.getWork());
		userInfo.setCreated_at(findUser.getCreated_at());
		userInfo.setUpdated_at(findUser.getUpdated_at());

		//ユーザ詳細画面へ遷移
		return mapping.findForward("showDetail");
	}

}
