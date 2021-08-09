package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.domain.Member;

/**
 * MembersテーブルのRepositoryクラス
 * 
 * @author inada
 *
 */
@Repository
public class MemberRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final String TABLENAME = "members";

	private static final RowMapper<Member> MEMBER_ROW_MAPPER = (rs, i) -> {
		Member member = new Member();
		member.setId(rs.getInt("id"));
		member.setName(rs.getString("name"));
		member.setEmail(rs.getString("email"));
		member.setPassword(rs.getString("password"));
		member.setAdminFlag(rs.getBoolean("admin_flag"));
		return member;
	};

	/**
	 * 登録人物を一人検索する.
	 * 
	 * @param id 検索する人物のID
	 * @return 検索した人物のDB登録情報
	 */
	public Member load(Integer id) {
		String sql = "select id,name,email,password,admin_flag from " + TABLENAME + " where id=:id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		Member member = template.queryForObject(sql, param, MEMBER_ROW_MAPPER);
		return member;
	}

	/**
	 * 登録している人物を全員検索する.
	 * 
	 * @return DBに登録している全員の情報
	 */
	public List<Member> findAll() {
		String sql = "select id,name,email,password,admin_flag from " + TABLENAME + " order by id asc;";
		List<Member> memberList = template.query(sql, MEMBER_ROW_MAPPER);
		return memberList;
	}

	/**
	 * 人物を登録・更新する.
	 * 
	 * @param member 登録・更新する人物情報
	 * @return 登録・更新した人物情報
	 */
	public Member save(Member member) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(member);
		if (member.getId() == null) {
			List<Member> memberList = findAll();
			if(memberList.size() == 0) {
				member.setAdminFlag(true);
			}else {
				member.setAdminFlag(false);
			}
			for(Member email:memberList) {
				if(member.getEmail().equals(email.getEmail())) {
					return null;
				}
			}
			String insertSql = "insert into " + TABLENAME
					+ " (name,email,password,admin_flag) values(:name,:email,:password,:adminFlag);";
			KeyHolder keyHolder = new GeneratedKeyHolder();
			String[] keyColumnName = { "id" };
			template.update(insertSql, param, keyHolder, keyColumnName);
			member.setId(keyHolder.getKey().intValue());
			System.out.println("id" + member.getId() + "へ登録しました");
			return member;
		}
		String updateSql = "update " + TABLENAME
				+ " set name=:name,email=:email,password=:password,admin_flag=:adminFlag where id=:id;";
		template.update(updateSql, param);
		return member;
	}

	/**
	 * ログイン情報が正しいか判断する.
	 * 
	 * @param email    メールアドレス情報
	 * @param password パスワード情報
	 * @return 登録人物情報
	 */
	public Member findByEmailAndPassword(String email, String password) {
		String sql = "select id,name,email,password,admin_flag from " + TABLENAME
				+ " where email=:email and password=:password;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("email", email).addValue("password", password);
		List<Member> memberList = template.query(sql, param, MEMBER_ROW_MAPPER);
		if (memberList.size() == 0) {
			return null;
		}
		return memberList.get(0);
	}
}
