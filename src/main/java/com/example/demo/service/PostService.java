package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Post;

public interface PostService {
	
	List<Post> findAll();
	
	void insert(Post post);
	
	void update(Post post);
	
	void deleteById(int id);


}
