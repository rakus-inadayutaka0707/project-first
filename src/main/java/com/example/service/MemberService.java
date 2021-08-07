package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Member;
import com.example.repository.MemberRepository;

/**
 * MemberテーブルのServiceクラス.
 * 
 * @author inada
 *
 */
@Service
@Transactional
public class MemberService {

	@Autowired
	private MemberRepository repository;

	/**
	 * 登録している人物を検索する.
	 * 
	 * @param id 検索する人物の登録ID
	 * @return 検索した人物の情報
	 */
	public Member load(Integer id) {
		return repository.load(id);
	}

	/**
	 * 登録している人物を全員検索する.
	 * 
	 * @return 登録している人物全員の情報
	 */
	public List<Member> findAll() {
		return repository.findAll();
	}

	/**
	 * 人物を登録・更新する.
	 * 
	 * @param member 登録・更新する人物情報
	 * @return 登録・更新した人物情報
	 */
	public Member save(Member member) {
		return repository.save(member);
	}

	/**
	 * ログイン情報が正しいか判断する.
	 * 
	 * @param email    メールアドレス情報
	 * @param password パスワード情報
	 * @return 登録人物情報
	 */
	public Member findByEmailAndPassword(String email, String password) {
		return repository.findByEmailAndPassword(email, password);
	}
}
