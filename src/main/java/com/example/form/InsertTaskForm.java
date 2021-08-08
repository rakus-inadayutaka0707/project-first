package com.example.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * タスクを登録する際に使用するフォームクラス.
 * 
 * @author inada
 *
 */
public class InsertTaskForm {
	/** タスクタイトル */
	@Size(min = 1, max = 127, message = "1文字から127文字で入力してください")
	private String title;
	/** タスク内容 */
	@NotBlank(message = "タスク内容を入力してください")
	private String context;
	/** タスク終了予定日 */
	@NotBlank(message = "タスク終了日を入力してください")
	private String finishDate;
	/** ログインユーザID */
	private String memberId;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	@Override
	public String toString() {
		return "InsertTaskForm [title=" + title + ", context=" + context + ", finishDate=" + finishDate + ", memberId="
				+ memberId + "]";
	}
}
