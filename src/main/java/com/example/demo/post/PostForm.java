package com.example.demo.post;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PostForm {
	
	public PostForm() {};
	
	public PostForm(String title, String content, LocalDateTime created) {
		super();
		this.title = title;
		this.content = content;
	}
	
	@NotBlank (message = "必須項目を埋めてください")
	private String title;
	
	@NotBlank (message = "必須項目を埋めてください")
	private String content;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
