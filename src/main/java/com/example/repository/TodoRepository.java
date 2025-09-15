package com.example.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository	//Repositoryであることを示す
public class TodoRepository {
	@Autowired	//自動的に値を注入するために使用される
	private JdbcTemplate jdbcTemplate;	//JdbcTemplateクラスはSQLの実行、結果の取得、例外処理などの行うためのもの
	
	//データベースからすべてのToDoを取得するメソッド
	public List<Map<String,Object>> findAll(){
		List<Map<String, Object>> list;
		String query = "SELECT * FROM test_table";
		try {
			list =jdbcTemplate.queryForList(query);
			}catch(Exception e) {
				return null;
			}
		return list;
	}
	//追加した行
	public void addTodo(String title, String description, Boolean status) {
		String query ="INSERT INTO test_table (title,description, status) VALUES (?,?,?)";
		jdbcTemplate.update(query, title,description,status);
	}
	public Map<String, Object> getTodoItemById(Long id){
		String query = "SELECT * FROM test_table WHERE id =?";
		try {
			return jdbcTemplate.queryForMap(query, id);
		}catch(Exception e) {
				return null;
		}
	}
	
	public void editTodo(Long id, String title, String description, Boolean status) {
		String query = "UPDATE test_table SET title =?, description = ?, status=? WHERE id = ?";
		jdbcTemplate.update(query, title, description, status, id);
	}
	
	public void deleteTodoItem(Long id) {
		String sql ="DELETE FROM test_table WHERE id=?";
		jdbcTemplate.update(sql,id);
	}
}
