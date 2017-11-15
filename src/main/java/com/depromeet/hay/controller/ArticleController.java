package com.depromeet.hay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.depromeet.hay.domain.Article;
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
}
