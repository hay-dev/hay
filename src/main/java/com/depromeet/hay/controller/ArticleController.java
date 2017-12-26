package com.depromeet.hay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.depromeet.hay.domain.Article;
import com.depromeet.hay.domain.Comment;
import com.depromeet.hay.domain.Member;
import com.depromeet.hay.service.ArticleService;

@RestController
@RequestMapping("/articles")
public class ArticleController {
	
	@Autowired
	private ArticleService articleService;
	
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public void writeArticle(@RequestBody Article article) {
		articleService.writeArticle(article);
	}

	@GetMapping("/recent")
	@ResponseStatus(HttpStatus.OK)
	public Article getRecentArticle() {
		return articleService.getRecentArticle();
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Article getArticle(@PathVariable int id) {
		return articleService.getArticle(id);
	}

	@GetMapping("/search/recent")
	@ResponseStatus(HttpStatus.OK)
	public List<Article> searchRecentArticles(@RequestParam("query") String text) {
		return articleService.searchRecentArticles(text);
	}

	@GetMapping("/search")
	@ResponseStatus(HttpStatus.OK)
	public List<Article> searchArticles(@RequestParam("query") String text, @RequestParam("last_id") int beforeAddedThan) {
		return articleService.searchArticles(text, beforeAddedThan);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void modifyArticle(@PathVariable int id, @RequestBody Article article) {
		article.setId(id);
		articleService.modifyArticle(article);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteArticle(@PathVariable int id) {
		articleService.deleteArticle(id);
	}

	@GetMapping("/{id}/likers")
	@ResponseStatus(HttpStatus.OK)
	public List<Member> getLikers(@PathVariable int id) {
		return articleService.getLikers(id);
	}

	@PostMapping("/{id}/comments")
	@ResponseStatus(HttpStatus.CREATED)
	public void writeComment(@PathVariable int id, @RequestBody Comment comment) {
		articleService.writeComment(id, comment);
	}

	@GetMapping("/{id}/comments")
	@ResponseStatus(HttpStatus.OK)
	public List<Comment> getComments(@PathVariable int id) {
		return articleService.getComments(id);
	}
}
