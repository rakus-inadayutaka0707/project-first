package com.example.domain;

public class Member {
	/** ID */
	private Integer id;
	/** 名前 */
	private String name;
	/** メールアドレス */
	private String email;
	/** パスワード */
	private String password;
	/** 管理者フラグ */
	private Boolean adminFlag;

	public Boolean getAdminFlag() {
		return adminFlag;
	}

	public void setAdminFlag(Boolean adminFlag) {
		this.adminFlag = adminFlag;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	@Override
	public String toString() {
		return "Member [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", adminFlag="
				+ adminFlag + "]";
	}
}
