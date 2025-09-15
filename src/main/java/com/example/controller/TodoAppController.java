package com.example.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.entity.Todo;
import com.example.form.TodoForm;
import com.example.service.TodoService;


@Controller
public class TodoAppController {
	
    @Autowired
    private TodoService todoService;
	
    @GetMapping("/")
    private String getAllTodos(Model model) {
        List<Todo> taskList = todoService.findAllTodos();
        
        model.addAttribute("taskList", taskList);
        
		return "index";		//Viewを返す意味(View→html)
	}
    /*追加*/
    @GetMapping("/create")
    public String showCreateTodoForm(Model model) {
    	model.addAttribute("todoForm", new TodoForm());
    	return "create-todo";
    }
    
    @PostMapping("/addTodo")
    public String submitForm(@Valid TodoForm todoForm, BindingResult result) {
    	if(result.hasErrors()) {
    		return "create-todo";
    	}
    	
    	//フォームのデータの処理
    	todoService.addTodo(todoForm.getTitle(), todoForm.getDescription(), todoForm.isStatus());
    	
    	return "redirect:/";  //処理後のリダイレクト先
    }
	
    @GetMapping("/todo/edit/{id}")
    public String showEditTodo(@PathVariable("id") Long id, Model model) {
    	Todo todo = todoService.getTodoItemById(id);
    	model.addAttribute("todoForm", todo);
    	return "edit-todo";
    }
    
    @PostMapping("/todo/edit/{id}")
    public String editTodo(@PathVariable("id") Long id, @ModelAttribute("todoForm") TodoForm todoForm, BindingResult result) {
    	
    	if(result.hasErrors()) {
    		return "edit-todo";
    	}
    	todoService.editTodo(id, todoForm.getTitle(), todoForm.getDescription(), todoForm.isStatus());
    	return "redirect:/";
    }
    
    @GetMapping("/todo/delete/{id}")
    public String deleteTodoItem(@PathVariable("id") Long id) {
    	todoService.deleteTodoItem(id);
    	return "redirect:/";
    }
}
