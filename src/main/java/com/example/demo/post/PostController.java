package com.example.demo.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Post;
import com.example.demo.service.PostService;

@Controller
public class PostController {
	
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }
	
    
    @GetMapping
    public String index(Model model) {
    	List<Post> postList = postService.findAll();
        model.addAttribute("title", "Spring Practice");
        model.addAttribute("postList", postList);
        return "post/index";
    }
    
    @GetMapping("/post")
    public String post(Model model) {
    	model.addAttribute("title", "Spring Practice");
        return "post/post";
    }
    
	@PostMapping("/complete")
	public String complete(Model model) {
		model.addAttribute("title", "Spring Practice");
		return "post/complete";
	}
}
