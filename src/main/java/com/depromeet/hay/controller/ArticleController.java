package com.depromeet.hay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.depromeet.hay.domain.Article;
import com.depromeet.hay.service.ArticleService;

@RestController
@RequestMapping("/articles")
public class ArticleController {
	
	@Autowired
	private ArticleService articleService;
	
	@PostMapping(path = "")
	@ResponseStatus(HttpStatus.CREATED)
	public void writeArticle(@RequestBody Article article) {
		articleService.writeArticle(article);
	}
	
	@GetMapping(path = "")
	@ResponseStatus(HttpStatus.OK)
	public List<Article> getAllArticles() {
		return this.articleService.getAllArticles();
	}

	@GetMapping(path ="/recent")
	@ResponseStatus(HttpStatus.OK)
	public Article getRecentArticle() {
		return this.articleService.getRecentArticle();
	}

	@GetMapping(path ="/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Article getArticle(@PathVariable int id) {
		return this.articleService.getArticle(id);
	}
	
	@PutMapping(path = "/modify/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void modifyArticle(@PathVariable int id, @RequestBody Article article) {
		this.articleService.modifyArticle(article);
	}
	
	@DeleteMapping(path = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteArticle(@PathVariable int id) {
		this.articleService.deleteArticle(id);
	}
}
