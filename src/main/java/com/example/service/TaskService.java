package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Task;
import com.example.repository.TaskRepository;

/**
 * tasksテーブル操作のためのサービスクラス.
 * 
 * @author inada
 *
 */
@Service
@Transactional
public class TaskService {

	@Autowired
	private TaskRepository repository;
	
	/**
	 * 登録している1件のタスクを検索
	 * @param id 検索したいタスク
	 * @return 検索したタスク
	 */
	public Task findById(Integer id) {
		return repository.findById(id);
	}

	/**
	 * 登録しているすべてのタスクを検索する.
	 * 
	 * @return 検索したタスク情報
	 */
	public List<Task> findAll() {
		return repository.findAll();
	}

	/**
	 * ログインしているユーザが登録しているタスクをすべて検索
	 * 
	 * @param id ログインユーザID
	 * @return 登録しているタスク情報
	 */
	public List<Task> findByMyTasks(Integer id) {
		return repository.findByMyTasks(id);
	}

	/**
	 * 曖昧検索を行う.
	 * 
	 * @param word 検索したいワード
	 * @return 検索したタスク情報
	 */
	public List<Task> findBySearchLikeTasks(String word) {
		return repository.findBySearchLikeTasks(word);
	}

	/**
	 * タスク情報の登録・更新をする.
	 * 
	 * @param task 登録・更新したいタスク情報
	 * @return 登録・更新したタスク情報
	 */
	public Task save(Task task) {
		return repository.save(task);
	}

	/**
	 * タスクを削除する.
	 * 
	 * @param id 削除するタスクID
	 */
	public void delete(Integer id) {
		repository.delete(id);
	}
}
