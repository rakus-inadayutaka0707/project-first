package com.example.domain;

import java.time.LocalDate;

/**
 * tasksテーブルのDomainクラス.
 * 
 * @author inada
 *
 */
public class Task {
	private Integer id;
	private String title;
	private String context;
	private LocalDate finishDate;
	private Boolean completeFlag;
	private Integer memberId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public LocalDate getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(LocalDate finishDate) {
		this.finishDate = finishDate;
	}

	public Boolean getCompleteFlag() {
		return completeFlag;
	}

	public void setCompleteFlag(Boolean completeFlag) {
		this.completeFlag = completeFlag;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", title=" + title + ", context=" + context + ", finishDate=" + finishDate
				+ ", completeFlag=" + completeFlag + ", memberId=" + memberId + "]";
	}
}
