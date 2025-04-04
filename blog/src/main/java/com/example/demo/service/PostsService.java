package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.demo.BlogApplication;
import com.example.demo.exceptions.RecursoNaoEncntradoException;
import com.example.demo.model.Post;
import com.example.demo.repository.PostsRepository;

@Service
public class PostsService {

    
	//@Autowired
	//private PostsRepository postsRepository;

	private final PostsRepository postsRepository;
	
	public PostsService(PostsRepository postsRepository) {
		this.postsRepository = postsRepository;
	}
	
	
	//salvar
	public Post salvarPost(Post post) {
		return postsRepository.save(post);
	}
	
    //buscar por id
	public Post buscarPorId(Long id){
		return postsRepository.findById(id)
				.orElseThrow(() -> new RecursoNaoEncntradoException("Prduto com ID " + id + "não encontrado"));
				
	}
	
	//listar posts
	public List<Post> listarPosts(){
		return postsRepository.findAll();
	}

	//deletar
	public void deletarPost(Long id) {
		
		if(!postsRepository.existsById(id)) {
			throw new RecursoNaoEncntradoException("Prduto com ID " + id + " não encontrado.");
		}
		
		postsRepository.findById(id);
	}
	
	
}
