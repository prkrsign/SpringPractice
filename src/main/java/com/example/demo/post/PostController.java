package com.example.demo.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Post;
import com.example.demo.service.PostService;

@Controller
public class PostController {
	
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }
    
    private Post makePost(PostForm postForm) {
    	Post post = new Post();
        post.setTitle(postForm.getTitle());
        post.setContent(postForm.getContent());
        return post;
    }
	
    
    @GetMapping
    public String index(Model model) {
    	List<Post> postList = postService.findAll();
        model.addAttribute("title", "Spring Practice");
        model.addAttribute("postList", postList);
        return "post/index";
    }
    
    @GetMapping("/form")
    public String post(PostForm postForm, Model model) {
    	model.addAttribute("title", "Spring Practice");
        return "post/form";
    }
    
	@PostMapping("/post")
	public String complete(
			@Validated PostForm postForm,
	        BindingResult result,
	        Model model,
	        RedirectAttributes redirectAttributes) {
		
		if (result.hasErrors()) {
			model.addAttribute("title", "Spring Practice");
			return "post/form";
		}
		Post post = makePost(postForm);
		postService.insert(post);
		List<Post> postList = postService.findAll();
		redirectAttributes.addFlashAttribute("complete", "投稿に成功しました");
		model.addAttribute("title", "Spring Practice");
		model.addAttribute("postList", postList);		
		return "redirect:/";
	}
}
