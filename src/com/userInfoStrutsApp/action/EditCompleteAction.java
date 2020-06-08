package com.userInfoStrutsApp.action;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;

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

public class EditCompleteAction extends Action {

	final static String EDITTYPE_REGISTRATION ="reg";
	final static String EDITTYPE_UPDATE ="upd";

	@Override
	public ActionForward execute(
			ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws IOException, ServletException {
		HttpSession hs = request.getSession();
		//ユーザ情報テーブルの編集タイプを取得（登録or編集）
		String editType = request.getParameter("edit-type");
		//規定値以外のeditTypeの場合エラー画面へ遷移（基本起きない）
		if(!editType.equals(EDITTYPE_REGISTRATION) && !editType.equals(EDITTYPE_UPDATE)) return mapping.findForward("error");

		//formBeanからユーザ情報を取得
		UserInfoForm userInfo = (UserInfoForm)form;
		String last_name = userInfo.getLast_name();
		String first_name = userInfo.getFirst_name();
		Date birthday = setupBirthday(userInfo.getYear(), userInfo.getMonth(), userInfo.getDay());
		String work = userInfo.getWork();
		int id = userInfo.getId()!=0 ? userInfo.getId() : 0;

		try {
			//新規登録
			if(editType.equals(EDITTYPE_REGISTRATION)) {
				//登録画面での初期入力値を削除する
				hs.removeAttribute("editValue");
				hs.invalidate();

				int insertId = UserInfoDAO.getInstance().insertUser(last_name, first_name, birthday, work);
				updateShowingData(userInfo, insertId);
				return mapping.findForward("showDetail");
			}

			//登録内容の編集
			if(editType.equals(EDITTYPE_UPDATE)) {
				UserInfoDAO.getInstance().updateUser(last_name, first_name, birthday, work, id);
				updateShowingData(userInfo, id);
				//編集後のユーザ詳細画面へ遷移
				return mapping.findForward("showDetail");
			}

		} catch (Exception e) {
			e.printStackTrace();
			//エラー発生の場合、エラー画面へ遷移
			return mapping.findForward("error");
		}
		//後始末
		return mapping.findForward("error");
	}

	/*
	 * 登録および編集後の画面表示のために、id、生年月日、登録日時、変更日時をBeanに詰め直す
	 * @param userInfo 画面から入力された情報が入っているBean
	 * @param id ユーザID
	 */
	private void updateShowingData(UserInfoForm userInfo, int id) {
		try {
			UserInfoForm findUser = UserInfoDAO.getInstance().findUserById(id);
			userInfo.setId(findUser.getId());
			userInfo.setBirthday(findUser.getBirthday());
			userInfo.setCreated_at(findUser.getCreated_at());
			userInfo.setUpdated_at(findUser.getUpdated_at());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * 生年月日を組み立てる
	 * @param year
	 * @param month
	 * @param day
	 * @return birthday
	 */
	private Date setupBirthday (String year, String month, String day) {
		//utilとsql型の区別をわかりやすくするために明示的に書いた
		java.util.Date tmp = new java.util.Date();
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, Integer.parseInt(year));
		calendar.set(Calendar.MONTH, Integer.parseInt(month) -1);
		calendar.set(Calendar.DATE, Integer.parseInt(day));
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		tmp = calendar.getTime();
		java.sql.Date birthday = new java.sql.Date(tmp.getTime());
		return birthday;
	}
}
