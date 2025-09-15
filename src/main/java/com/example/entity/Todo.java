package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//Entityは、データベースのテーブルをJavaオブジェクトとして表現するもの
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Todo {
	private Long id;
	private String title;
	private String description;
	private Boolean status;
}
