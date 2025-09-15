package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Todo;
import com.example.repository.TodoRepository;

/*
 * タスク関連のサービスを提供するインターフェース
 * Serviceクラスは、アプリケーションのビジネスロジックを処理する
 * ビジネスロジックとは、アプリの「ルール」や「手順」を指す
 */
@Service
public class TodoService {
	
	@Autowired
	public TodoRepository todoRepository;
		//値の取得
	public List<Todo> findAllTodos(){
		List<Map<String, Object>> todos = todoRepository.findAll();
		
		//Todo(Entity)クラスに値をセット
		List<Todo> todoList = new ArrayList<>();
		
		for(Map<String, Object>mapTodo : todos) {
			//Mapから値を取得
			Long id =((Number)mapTodo.get("id")).longValue();
			String title =(String)mapTodo.get("title");
			String description =(String)mapTodo.get("description");
			Boolean status =(Boolean)mapTodo.get("status");
			
			//Todoエンティティを作成して値をセット
			Todo todo = new Todo();
			todo.setId(id);
			todo.setTitle(title);
			todo.setDescription(description);
			todo.setStatus(status);
			
			//リストに追加
			todoList.add(todo);
			
		}
		return todoList;
	}
	public void addTodo(String title, String description, Boolean status) {
		todoRepository.addTodo(title, description, status);
	}
	
	public Todo getTodoItemById(Long id) {
		Map<String, Object> mapTodo=todoRepository.getTodoItemById(id);
		
		//Mapから値を取得
		Long mapTodo_id = ((Number) mapTodo.get("id")).longValue();
		String title = (String) mapTodo.get("title");
		String description = (String) mapTodo.get("description");
		Boolean status = (Boolean) mapTodo.get("status");
		
		//Todoエンティティを作成して値をセット
		Todo todo = new Todo();
		todo.setId(mapTodo_id);
		todo.setTitle(title);
		todo.setDescription(description);
		todo.setStatus(status);
		
		return todo;
	}
	
	public void editTodo(Long id,String title, String description, Boolean status) {
		todoRepository.editTodo(id, title, description, status);
	}
	
	public void deleteTodoItem(Long id) {
		todoRepository.deleteTodoItem(id);
	}
}
