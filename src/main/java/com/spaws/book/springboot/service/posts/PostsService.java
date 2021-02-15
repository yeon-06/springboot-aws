package com.spaws.book.springboot.service.posts;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spaws.book.springboot.domain.posts.Posts;
import com.spaws.book.springboot.domain.posts.PostsRepository;
import com.spaws.book.springboot.web.dto.PostsUpdateRequestDto;
import com.spaws.book.springboot.web.dto.PostsListResponseDto;
import com.spaws.book.springboot.web.dto.PostsResponseDto;
import com.spaws.book.springboot.web.dto.PostsSaveRequestDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor	// final로 선언된 모든 필드로 생성자 생성
@Service
public class PostsService {
	private final PostsRepository postsRepository;
	// 게시글 add
	@Transactional
	public Long save(PostsSaveRequestDto requestDto) { 
		return postsRepository.save(requestDto.toEntity()).getId();
	}
	
	// 게시글 update
	@Transactional
	public Long update(Long id, PostsUpdateRequestDto requestDto) { 
		Posts posts = postsRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
		
		posts.update(requestDto.getTitle(), requestDto.getContent());
		return id;
	}

	// id를 이용해 게시글 찾기
	public PostsResponseDto findById(Long id) { 
		Posts entity = postsRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
		return new PostsResponseDto(entity);
	}
	
	// 게시글 전체 조회
	@Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }
	
	// 게시글 delete
	@Transactional
	public void delete (Long id) {
		Posts posts = postsRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
		postsRepository.delete(posts);	// jpa에서 지원하는 delete 메소드 이용
	}
}
