package com.depromeet.hay;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.depromeet.hay.dao.ArticleDao;
import com.depromeet.hay.dao.MemberDao;
import com.depromeet.hay.domain.Article;
import com.depromeet.hay.domain.Member;

@RunWith(SpringRunner.class)
@SpringBootTest
//@Transactional
public class ArticleDaoTests {

	@Autowired
	private ArticleDao articleDao;
	@Autowired
	private MemberDao memberDao;
	
	@Test
	public void deleteArticle() {
		this.articleDao.deleteAll();
		this.memberDao.deleteAll();
		
		Member member = new Member("155231heyyo@gamil.com", "test1234");
		memberDao.add(member);

		Article article = new Article();
		article.setTitle("테스트1");
		article.setContent("작동 좀 해라");
		article.setWeather(2);
		article.setLocation("우리 집ㅎㅎ");
		article.setAuthor(member.getId());
		this.articleDao.add(article);		

		this.articleDao.deleteArticle(article.getId());
	}
	
	@Test
	public void addAndGet() {
		this.articleDao.deleteAll();
		
		Member member = new Member("166231heyyo@gamil.com", "test1234");
		memberDao.add(member);

		Article article = new Article();
		article.setTitle("테스트1");
		article.setContent("작동 좀 해라");
		article.setWeather(2);
		article.setLocation("우리 집ㅎㅎ");
		article.setAuthor(member.getId());
		this.articleDao.add(article);		

		Article test = this.articleDao.getArticle(article.getId());
		assertEquals(test.getTitle(), article.getTitle());	
	}
	
	@Test
	public void addAndGetAll() {
		articleDao.deleteAll();
		memberDao.deleteAll();

		Member member = new Member("41723yo@gamil.com", "test1234");
		memberDao.add(member);
		
		Article article = new Article();
		article.setTitle("테스트1");
		article.setContent("작동 좀 해라");
		article.setWeather(2);
		article.setLocation("우리 집ㅎㅎ");
		article.setAuthor(member.getId());
		
		articleDao.add(article);		
		
		Article article2 = new Article();
		article2.setTitle("영어 못 읽냐");
		article2.setContent("Please work!!");
		article2.setWeather(1);
		article2.setLocation("at home");
		article2.setAuthor(member.getId());
		
		articleDao.add(article2);				
		
		List<Article> list = this.articleDao.getAllArticles();
		assertThat(list)
			.isNotEmpty()
			.hasSize(2)
			.contains(article);
	}
}
