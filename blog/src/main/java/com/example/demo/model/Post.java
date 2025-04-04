package com.example.demo.model;

import java.time.OffsetDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "posts")
@SequenceGenerator(name = "seq_posts", sequenceName = "seq_posts", allocationSize = 1, initialValue = 1)
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_posts")
	private Long id;
	
	@Column(nullable = false)
	private String titulo;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dtHora;
	
	@Column(columnDefinition = "text", nullable = false)
	private String textos;

	
	public Post() {
		
	}


	public Post(String titulo, Date dtHora, String textos) {
		super();
		this.titulo = titulo;
		this.dtHora = dtHora;
		this.textos = textos;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public Date getDtHora() {
		return dtHora;
	}


	public void setDtHora(Date dtHora) {
		this.dtHora = dtHora;
	}


	public String getTextos() {
		return textos;
	}


	public void setTextos(String textos) {
		this.textos = textos;
	}
	
	
	
	

}
