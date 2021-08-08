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

import com.example.domain.Task;

/**
 * tasksテーブル操作のためのRepositoryクラス.
 * 
 * @author inada
 *
 */
@Repository
public class TaskRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final String TABLENAME = "tasks";

	private static final RowMapper<Task> TASK_ROW_MAPPER = (rs, i) -> {
		Task task = new Task();
		task.setId(rs.getInt("id"));
		task.setTitle(rs.getString("title"));
		task.setContext(rs.getString("context"));
		task.setFinishDate(rs.getDate("finish_date").toLocalDate());
		task.setCompleteFlag(rs.getBoolean("complete_flag"));
		task.setMemberId(rs.getInt("member_id"));
		return task;
	};

	/**
	 * 登録しているタスクをすべて検索.
	 * 
	 * @return 検索したタスク情報
	 */
	public List<Task> findAll() {
		String sql = "select id,title,context,finish_date,complete_flag,member_id from " + TABLENAME
				+ " order by finish_date asc";
		List<Task> taskList = template.query(sql, TASK_ROW_MAPPER);
		return taskList;
	}

	/**
	 * ログインしているユーザが登録しているタスクをすべて検索
	 * 
	 * @param id ログインユーザID
	 * @return 登録しているタスク情報
	 */
	public List<Task> findByMyTasks(Integer id) {
		String sql = "select id,title,context,finish_date,complete_flag,member_id from " + TABLENAME
				+ " where member_id=:id order by finish_date asc";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		List<Task> taskList = template.query(sql, param, TASK_ROW_MAPPER);
		return taskList;
	}

	/**
	 * 曖昧検索を行う.
	 * 
	 * @param word 検索したいワード
	 * @return 検索したタスク情報
	 */
	public List<Task> findBySearchLikeTasks(String word) {
		String sql = "select id,title,context,finish_date,complete_flag,member_id from " + TABLENAME
				+ " where title like :word or context like :word;";
		word = "%" + word + "%";
		SqlParameterSource param = new MapSqlParameterSource().addValue("word", word).addValue("word", word);
		List<Task> taskList = template.query(sql, param, TASK_ROW_MAPPER);
		return taskList;
	}

	/**
	 * タスク情報の登録・更新をする.
	 * 
	 * @param task 登録・更新したいタスク情報
	 * @return 登録・更新したタスク情報
	 */
	public Task save(Task task) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(task);
		if (task.getId() == null) {
			String sql = "insert into " + TABLENAME
					+ " (title,context,finish_date,complete_flag,member_id) values(:title,:context,:finishDate,:completeFlag,:memberId);";
			KeyHolder keyHolder = new GeneratedKeyHolder();
			String[] keyId = { "id" };
			template.update(sql, param, keyHolder, keyId);
			task.setId(keyHolder.getKey().intValue());
			System.out.println("id" + task.getId() + "に登録しました");
			return task;
		}
		String sql = "update " + TABLENAME
				+ " set title=:title,context=:context,finish_date=:finishDate,complete_flag=:completeFlag,member_id=:memberId where id=:id;";
		template.update(sql, param);
		return task;
	}

	/**
	 * タスクを削除する.
	 * 
	 * @param id 削除するタスクID
	 */
	public void delete(Integer id) {
		String sql = "delete from " + TABLENAME + " where id=:id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		template.update(sql, param);
	}
}