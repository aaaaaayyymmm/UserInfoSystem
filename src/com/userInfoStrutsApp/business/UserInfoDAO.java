package com.userInfoStrutsApp.business;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.userInfoStrutsApp.form.UserInfoForm;

public class UserInfoDAO {
	private Connection con = null;
	private PreparedStatement st = null;

	public static UserInfoDAO getInstance(){
		return new UserInfoDAO();
	}

	/*
	 * ユーザ情報一覧を取得する
	 * @return ユーザ情報一覧
	 */
	public List<UserInfoForm> selectAll() throws Exception{
		//Connection con = null;
		List<UserInfoForm> userList = new ArrayList<UserInfoForm>();
		try {
			con = DBManager.getConnection();
			System.out.println("con: " + con);

			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery("select * from userinfo_t where deleted_flg=0;");
			while(result.next()) {
				UserInfoForm user = new UserInfoForm();
				user.setId(result.getInt("id"));
				user.setLast_name(result.getString("last_name"));
				user.setFirst_name(result.getString("first_name"));
				user.setBirthday(result.getDate("birthday"));
				user.setWork(result.getString("work"));
				user.setCreated_at(result.getTimestamp("created_at"));
				user.setUpdated_at(result.getTimestamp("updated_at"));
				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return userList;
	}

	/*
	 * ユーザ情報の登録
	 * @param last_name 姓
	 * @param first_name 名
	 * @param birthday 生年月日
	 * @param work 職業
	 * @return insertId insertしたレコードのID
	 */
	public int insertUser(String last_name, String first_name, Date birthday, String work) throws Exception{
		//戻り値のinsertしたレコードのIDを取得するために定義
		ResultSet result = null;
		int insertId = 0;
		try {
			con = DBManager.getConnection();
			System.out.println("con: " + con);

			String sql = "insert into userinfo_t (last_name, first_name, birthday, work, created_at, updated_at) value(?,?,?,?,?,?);";
			st =  con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			st.setString(1, last_name);
			st.setString(2, first_name);
			st.setDate(3, birthday);
			st.setString(4, work);
			st.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
			st.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
			st.executeUpdate();

			result = st.getGeneratedKeys();
			if (result.next()) {
				insertId = result.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return insertId;
	}


	/*
	 * idから詳細を取得する
	 * @param id ユーザID
	 * @return ユーザの詳細情報
	 */
	public UserInfoForm findUserById(int id) throws Exception{
		UserInfoForm userInfo = new UserInfoForm();
		try {
			con = DBManager.getConnection();
			System.out.println("con: " + con);
			String sql = "select * from userinfo_t where id=? and deleted_flg=0;";
			st =  con.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet result = st.executeQuery();
			result.next();
			userInfo.setId(result.getInt("id"));
			userInfo.setLast_name(result.getString("last_name"));
			userInfo.setFirst_name(result.getString("first_name"));
			userInfo.setBirthday(result.getDate("birthday"));
			userInfo.setWork(result.getString("work"));
			userInfo.setCreated_at(result.getTimestamp("created_at"));
			userInfo.setUpdated_at(result.getTimestamp("updated_at"));
		} catch (SQLException e) {
			e.printStackTrace();
			//例外発生の場合、姓をブランクで返す。Actionクラスにて、姓がブランクの場合エラー画面へ遷移させる
			userInfo.setLast_name("");
			return userInfo;
		} finally {
			con.close();
		}
		return userInfo;
	}


	/*
	 * ユーザ情報の更新
	 * @param last_name 姓
	 * @param first_name 名
	 * @param birthday 生年月日
	 * @param work 職業
	 * @param id ユーザID
	 */
	public void updateUser(String last_name, String first_name, Date birthday, String work, int id) throws Exception{
		try {
			con = DBManager.getConnection();
			System.out.println("con: " + con);

			String sql = "update userinfo_t set last_name=?, first_name=?, birthday=?, work=?, updated_at=? where id=?;";
			st =  con.prepareStatement(sql);
			st.setString(1, last_name);
			st.setString(2, first_name);
			st.setDate(3, birthday);
			st.setString(4, work);
			st.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
			st.setInt(6, id);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
	}


	/*
	 * ユーザの削除
	 * 削除フラグを1にして削除したことにする
	 * @param id ユーザID
	 */
	public void deleteUser(int id) throws Exception {
		try {
			con = DBManager.getConnection();
			System.out.println("con: " + con);
			String sql = "update userinfo_t set updated_at=?, deleted_flg=? where id=?;";
			st =  con.prepareStatement(sql);
			st.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
			st.setInt(2, 1);
			st.setInt(3, id);
			st.executeUpdate();
			System.out.println("Delete Complete!!");
		} catch (SQLException e){
			e.printStackTrace();
		} finally {
			con.close();
		}
	}
}
