package com.example.demo.post;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    
    private Post makePost(PostForm postForm, int postId) {
    	Post post = new Post();
    	if (postId != 0)  {
    		post.setId(postId);
    	}
        post.setTitle(postForm.getTitle());
        post.setContent(postForm.getContent());
        return post;
    }
    
    private PostForm makePostForm(Post post) {
    	
    	PostForm postForm = new PostForm();
    	
    	postForm.setTitle(post.getTitle());
    	postForm.setContent(post.getContent());
        return postForm;
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
    	postForm.setNewPost(true);
    	model.addAttribute("title", "Spring Practice");
        return "post/form";
    }
    
    @GetMapping("/edit/{id}")
    public String edit(
    		PostForm postForm, 
    		@PathVariable int id,
    		Model model) {
    	Optional<Post> postOpt =  postService.getPost(id);
    	Optional<PostForm> postFormOpt = postOpt.map(p -> makePostForm(p));
    	
    	if (postFormOpt.isPresent()) {
    		postForm = postFormOpt.get();
    	}
    	
    	model.addAttribute("title", "Spring Practice 更新用フォーム");
    	model.addAttribute("edit", "日記の更新ができます");
    	model.addAttribute("postId", id);
        model.addAttribute("postForm", postForm);
        postForm.setNewPost(false);
        return "post/form";
    }
    
    @PostMapping("/update")
    public String update(
    		@Validated PostForm postForm,
    		BindingResult result,
    		@RequestParam("postId") int postId,
    		Model model,
    		RedirectAttributes redirectAttributes) {
    	
    		Post post = makePost(postForm, postId);
    		
    		if (!result.hasErrors()) {
    			postService.update(post);
    			redirectAttributes.addFlashAttribute("complete", "変更に成功しました");
    			return "redirect:/";
    		} else {
    			model.addAttribute("title", "Spring Practice");
    			return "post/form";    			
    		}
    	
    }
    
    @PostMapping("/delete")
    public String delete(@RequestParam("postId") int postId) {
    	postService.deleteById(postId);
    	return "redirect:/";
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
		Post post = makePost(postForm, 0);
		postService.insert(post);
		List<Post> postList = postService.findAll();
		redirectAttributes.addFlashAttribute("complete", "投稿に成功しました");
		model.addAttribute("title", "Spring Practice");
		model.addAttribute("postList", postList);		
		return "redirect:/";
	}
}
