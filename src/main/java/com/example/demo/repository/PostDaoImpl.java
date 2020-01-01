package com.example.demo.repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Post;

@Repository
public class PostDaoImpl implements PostDao {
	
	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public PostDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public List<Post> findAll() {
		String sql = "SELECT * FROM posts";
		
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql);
		List<Post> list = new ArrayList<Post>();
		
		for (Map<String, Object> result : resultList) {
			Post post = new Post();
			int postId = new  Integer((result.get("id")).toString());
			post.setId(postId);
			post.setTitle((String)result.get("title"));
			post.setContent((String)result.get("content"));
			post.setCreated(((Timestamp) result.get("created")).toLocalDateTime());
			
			list.add(post);
		}
		return list;
	}

	@Override
	public void insert(Post post) {	
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		jdbcTemplate.update("INSERT INTO posts(title, content, created) VALUES(?, ?, ?)",
				post.getTitle(), post.getContent(), timestamp  );
	}

	@Override
	public int update(Post post) {
		return jdbcTemplate.update("UPDATE posts SET title = ? , content = ? WHERE id = ?",
				post.getTitle(), post.getContent(), post.getId()  );		
	}

	@Override
	public int deleteById(int id) {
		return jdbcTemplate.update("DELETE FROM posts WHERE id = ?", id);
	}

	@Override
	public Optional<Post> findById(int id) {
		String sql = "SELECT * FROM posts WHERE id = ?";
		Map<String, Object> result = jdbcTemplate.queryForMap(sql, id);
		
		Post post = new Post();
		
		int postId = new  Integer((result.get("id")).toString());
		post.setId(postId);
		post.setTitle((String)result.get("title"));
		post.setContent((String)result.get("content"));
		post.setCreated(((Timestamp) result.get("created")).toLocalDateTime());
		
		Optional<Post> postOpt =  Optional.ofNullable(post);
		
		return postOpt;
	}

}
