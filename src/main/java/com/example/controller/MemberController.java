package com.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Member;
import com.example.form.InsertMemberForm;
import com.example.service.MemberService;

/**
 * Memberテーブルを操作するControllerクラス.
 * 
 * @author inada
 *
 */
@Controller
@RequestMapping("/")
public class MemberController {

	@Autowired
	private MemberService service;

	@Autowired
	private HttpSession session;

	@ModelAttribute
	private InsertMemberForm setUpInsertMemberForm() {
		return new InsertMemberForm();
	}

	/**
	 * ログイン画面を表示する.
	 * 
	 * @return ログイン画面
	 */
	@RequestMapping("/toInsert")
	public String toInset() {
		return "member/insert";
	}

	/**
	 * 人物を登録する.
	 * 
	 * @param form   登録者入力情報
	 * @param result エラーチェック
	 * @return ログイン画面へ
	 */
	@RequestMapping("/insert")
	public String insert(@Validated InsertMemberForm form, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "member/insert";
		}
		Member member = new Member();
		BeanUtils.copyProperties(form, member);
		member = service.save(member);
		if(member == null) {
			model.addAttribute("errorMessage", "メールアドレスがすでに登録されています。");
			return "member/insert";
		}
		return "redirect:/";
	}

	/**
	 * ログイン画面を表示する.
	 * 
	 * @return ログイン画面を表示
	 */
	@RequestMapping("/")
	public String toLogin() {
		return "member/login";
	}

	/**
	 * ログイン情報が正しいか判断する.
	 * 
	 * @param email    入力されたメールアドレス
	 * @param password 入力されたパスワード
	 * @param model    エラーメッセージ格納リクエストスコープ
	 * @return タスク管理画面へ
	 */
	@RequestMapping("/login")
	public String login(String email, String password, Model model) {
		Member member = service.findByEmailAndPassword(email, password);
		if (member == null) {
			model.addAttribute("errorMessage", "メールアドレスまたはパスワードが間違っています。");
			return "member/login";
		}
		session.setAttribute("member", member);
		return "forward:/task/list";
	}
}
