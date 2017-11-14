package com.depromeet.hay;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.depromeet.hay.dao.ArticleDao;
import com.depromeet.hay.domain.Article;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ArticleDaoTests {
	
	@Autowired
	ArticleDao articleDao;

	@Test
	public void deleteAllAndAddAndGet() {
		articleDao.deleteAll();
		
		Article article = new Article();
		article.setTitle("제목");
		article.setContent("내용");
		article.setWeather(2);
		article.setAuthor(1);
		articleDao.add(article);
		
		Article addedArticle = articleDao.get(1);
		assertEquals(article, addedArticle);
	}
}
