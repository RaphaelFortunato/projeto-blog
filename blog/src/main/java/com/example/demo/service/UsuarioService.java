package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	private final UsuarioRepository usuarioRepository;
	private final PasswordEncoder passwordEncoder;
	
	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
		this.passwordEncoder = new BCryptPasswordEncoder();
	}
	
	//@Autowired
	//private UsuarioRepository usuarioRepository;
	
	//@Autowired
	//private PasswordEncoder passwordEncoder;
	
	public Usuario registrarUsuario(String username, String password) {
		String senhaCripto = passwordEncoder.encode(password);
		Usuario usuario = new Usuario(username, senhaCripto);
		return usuarioRepository.save(usuario);
	}
	
	
	public Optional<Usuario> buscarPorUsername(String username){
		return usuarioRepository.findByUsername(username);
	}
	
	
	

}
