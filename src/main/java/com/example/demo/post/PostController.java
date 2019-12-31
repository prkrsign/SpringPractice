package com.example.demo.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Post;
import com.example.demo.repository.PostRepository;

@Controller
public class PostController {
	
    @Autowired
    PostRepository postRepository;
    
    @GetMapping
    public String index(Model model) {
        List<Post> postList=postRepository.findAll();
        model.addAttribute("title", "Spring Practice");
        model.addAttribute("postList", postList);
        return "post/index";
    }
    
    @GetMapping("/post")
    public String post() {
        return "post/post";
    }
}
