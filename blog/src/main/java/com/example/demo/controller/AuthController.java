package com.example.demo.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.model.Usuario;
import com.example.demo.security.JwtUtil;
import com.example.demo.service.UsuarioService;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

	private final UsuarioService usuarioService;
	
	public AuthController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}
	
	
	//@Autowired
	//private UsuarioService usuarioService;
	
	//registrar usuario
	 @PostMapping(value = "/register")
	    public ResponseEntity<?> register(@RequestBody Map<String, String> request) {
	        Usuario usuario = usuarioService.registrarUsuario(request.get("username"), request.get("password"));
	        return ResponseEntity.ok(usuario);
	    }
	
	//fazer login
	 @PostMapping(value = "/login")
	    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
	       Optional<Usuario> usuario = usuarioService.buscarPorUsername(request.get("username"));
	       if (usuario.isPresent() && usuario.get().getPassword().equals(request.get("password"))) {
	         String token = JwtUtil.geradorToken(usuario.get().getUsername());
	         return ResponseEntity.ok(Map.of("token", token));
	       }
	       return ResponseEntity.status(401).body("Credenciais inválidas");
	    }
	
}
