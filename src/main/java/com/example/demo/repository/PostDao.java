package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Post;


public interface PostDao {

	List<Post> findAll();
	
	Optional<Post> findById(int id);
	
	void insert(Post post);
	
	int update(Post post);
	
	int deleteById(int id);

	
}
