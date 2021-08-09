package com.example.controller;

import java.time.LocalDate;

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
import com.example.domain.Task;
import com.example.form.InsertTaskForm;
import com.example.service.TaskService;

@Controller
@RequestMapping("/task")
public class TaskController {

	@Autowired
	private TaskService service;

	@Autowired
	private HttpSession session;

	@ModelAttribute
	private InsertTaskForm setUpInsertTaskForm() {
		return new InsertTaskForm();
	}

	@RequestMapping("/list")
	public String list(Model model) {
		Member member = (Member) session.getAttribute("member");
		model.addAttribute("tasks", service.findByMyTasks(member.getId()));
		return "task/list";
	}
	
	@RequestMapping("/taskList")
	public String taskList(Integer id, Model model) {
		model.addAttribute("taskList", service.findByMyTasks(id));
		return "task/taskList";
	}

	@RequestMapping("/insert")
	public String insert(@Validated InsertTaskForm form, BindingResult result) {
		if (result.hasErrors()) {
			return "redirect:/task/list";
		}
		Task task = new Task();
		BeanUtils.copyProperties(form, task);
		task.setFinishDate(LocalDate.parse(form.getFinishDate()));
		task.setCompleteFlag(false);
		task.setMemberId(Integer.parseInt(form.getMemberId()));
		service.save(task);
		return "redirect:/task/list";
	}

	@RequestMapping("/update")
	public String update(Integer id) {
		Task task = service.findById(id);
		task.setCompleteFlag(true);
		service.save(task);
		return "redirect:/task/list";
	}

	@RequestMapping("/delete")
	public String delete(Integer id) {
		service.delete(id);
		return "redirect:/task/taskList";
	}
}
