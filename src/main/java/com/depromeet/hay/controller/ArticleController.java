package com.depromeet.hay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.depromeet.hay.dao.MemberDao;
import com.depromeet.hay.domain.Article;
import com.depromeet.hay.domain.Member;
import com.depromeet.hay.service.ArticleService;

@RestController
@RequestMapping("/articles")
public class ArticleController {
	
	@Autowired
	private ArticleService articleService;
	
	@RequestMapping(path = "", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void writeArticle(@RequestBody Article article) {
		articleService.writeArticle(article);
	}
	
	@RequestMapping(path = "", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<Article> getAllArticles() {
		return this.articleService.getAllArticles();
	}

	@RequestMapping(path ="/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Article getArticle(@PathVariable int id) {
		return this.articleService.getArticle(id);
	}
	
	@RequestMapping(path = "/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public void modifyArticle(@PathVariable int id, @RequestBody Article article) {
		article.setId(id);
		this.articleService.modifyArticle(article);
	}
	
	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public void deleteArticle(@PathVariable int id) {
		this.articleService.deleteArticle(id);
	}
}
