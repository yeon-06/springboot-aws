package com.spaws.book.springboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.spaws.book.springboot.config.auth.LoginUser;
import com.spaws.book.springboot.config.auth.dto.SessionUser;
import com.spaws.book.springboot.service.posts.PostsService;
import com.spaws.book.springboot.web.dto.PostsResponseDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class IndexController {
	private final PostsService postsService;
	
	@GetMapping("/")
	public String index(Model model, @LoginUser SessionUser user) {
		model.addAttribute("posts", postsService.findAllDesc());
		if(user!=null) {	// session에 저장된 값이 있을 때만 model에 userName으로 등록
			model.addAttribute("userNames", user.getName());
		}
		return "index";
	}
	
	@GetMapping("/posts/save")
	public String postsSave() {
		return "posts-save";
	}
	
	@GetMapping("/posts/update/{id}")
	public String postsUpdate(@PathVariable Long id, Model model) {
		PostsResponseDto dto = postsService.findById(id);
		model.addAttribute("posts",dto);
		
		return "posts-update";
	}
}
