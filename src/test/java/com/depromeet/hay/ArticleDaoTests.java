package com.depromeet.hay;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.depromeet.hay.dao.ArticleDao;
import com.depromeet.hay.dao.MemberDao;
import com.depromeet.hay.domain.Article;
import com.depromeet.hay.domain.Member;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ArticleDaoTests {

	@Autowired
	private ArticleDao articleDao;
	@Autowired
	private MemberDao memberDao;

	@Test
	public void deleteAllAndAddAndGet() {
		articleDao.deleteAll();

		Member member = new Member("test1234@gmail.com", "test1234");
		memberDao.add(member);

		Article article = new Article();
		article.setTitle("제목");
		article.setContent("내용");
		article.setWeather(2);
		article.setLocation("위치");
		article.setAuthor(member.getId());
		articleDao.add(article);

		Article addedArticle = articleDao.get(article.getId());
		assertEquals(article, addedArticle);
	}
}
