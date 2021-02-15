package com.spaws.book.springboot.web.dto;

import com.spaws.book.springboot.domain.posts.Posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsResponseDto {
	private Long id;
	private String title;
	private String content;
	private String author;
	
	@Builder
	public PostsResponseDto(Posts entity) {
		this.id 		= entity.getId();
		this.title 		= entity.getTitle();
		this.content 	= entity.getContent();
		this.author 	= entity.getAuthor();
	}
}
