package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Post;
import com.example.demo.repository.PostDao;

@Service
public class PostServiceImpl implements PostService {
	
	private final PostDao dao;
	
	@Autowired
	public PostServiceImpl(PostDao dao) {
		this.dao = dao;
	}

	@Override
	public List<Post> findAll() {
		return dao.findAll();
	}

	@Override
	public void insert(Post post) {
		dao.insert(post);
	}

	@Override
	public void update(Post post) {
		dao.update(post);
	}

	@Override
	public void deleteById(int id) {
		dao.deleteById(id);
	}

	@Override
	public Optional<Post> getPost(int id) {
		return dao.findById(id);
	}

}
