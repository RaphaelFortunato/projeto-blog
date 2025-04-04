package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Post;

import jakarta.transaction.Transactional;

@Repository
public interface PostsRepository extends JpaRepository<Post, Long> {

}
