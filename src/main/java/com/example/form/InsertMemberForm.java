package com.example.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class InsertMemberForm {
	/** 名前 */
	@NotBlank(message = "入力してください")
	@Size(min = 1, max = 30, message = "30文字以内で入力してください")
	private String name;

	/** メールアドレス */
	@NotBlank(message = "入力してください")
	@Size(min = 1, max = 127, message = "127文字以内で入力してください")
	@Email(message = "メール形式で入力してください")
	private String email;

	/** パスワード */
	@Size(min = 4, max = 127, message = "4文字以上127文字以内で入力してください")
	private String password;

	/** 管理者フラグ */
	private String adminFlag;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAdminFlag() {
		return adminFlag;
	}

	public void setAdminFlag(String adminFlag) {
		this.adminFlag = adminFlag;
	}

	@Override
	public String toString() {
		return "MemberForm [name=" + name + ", email=" + email + ", password=" + password + ", adminFlag=" + adminFlag
				+ "]";
	}
}
