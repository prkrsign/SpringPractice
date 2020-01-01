package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Post;

public interface PostService {
	
	List<Post> findAll();
	
	Optional<Post> getPost(int id);
	
	void insert(Post post);
	
	void update(Post post);
	
	void deleteById(int id);


}
