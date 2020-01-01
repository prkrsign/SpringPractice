package com.example.demo.repository;

import java.util.List;

import com.example.demo.entity.Post;


public interface PostDao {

	List<Post> findAll();
	
	void insert(Post post);
	
	int update(Post post);
	
	int deleteById(int id);

	
}
