package com.example.demo.repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public int update(Post post) {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

	@Override
	public int deleteById(int id) {
		// TODO 自動生成されたメソッド・スタブ
		return 0;
	}

}
