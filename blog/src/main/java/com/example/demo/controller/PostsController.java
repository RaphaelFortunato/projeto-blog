package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exceptions.RecursoNaoEncntradoException;
import com.example.demo.model.Post;
import com.example.demo.repository.PostsRepository;
import com.example.demo.service.PostsService;

@Controller
@RestController
@RequestMapping(value = "/post")
public class PostsController {
	
	@Autowired
	private PostsService postsService;
	
	/*private final PostsService postsService;
	
	public PostsController(PostsService postsService) {
		this.postsService = postsService;
	}*/
	
	@Autowired
	private PostsRepository postsRepository;
	
	@GetMapping
	public List<Post> listarPosts(){
		return postsService.listarPosts();
	}
	
	@GetMapping(value = "/buscar/{id}")
	public ResponseEntity<Post> buscarPorId2(@PathVariable ("id") Long id){
		
		Post post = postsRepository.findById(id).get();
		
		return new ResponseEntity<Post>(post, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable Long id){
		Post post = postsService.buscarPorId(id);
		return ResponseEntity.ok(post);
	}
	
	@PostMapping
	public Post salvarPost(@RequestBody Post posts) {
		return postsService.salvarPost(posts);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarPost(@PathVariable Long id){
		postsService.deletarPost(id);
		
		return ResponseEntity.noContent().build();
	}
	

}
