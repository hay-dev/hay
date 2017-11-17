package com.depromeet.hay;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.depromeet.hay.dao.ArticleDao;
import com.depromeet.hay.dao.CommentDao;
import com.depromeet.hay.dao.MemberDao;
import com.depromeet.hay.domain.Article;
import com.depromeet.hay.domain.Comment;
import com.depromeet.hay.domain.Member;

@RunWith(SpringRunner.class)
@SpringBootTest
//@Transactional
public class CommentDaoTests {
	
	@Autowired
	private CommentDao commentDao;
	@Autowired
	private ArticleDao articleDao;
	@Autowired
	private MemberDao memberDao;

	@Test
	public void addComment() {
		this.articleDao.deleteAll();
		this.memberDao.deleteAll();
		
		Member member = new Member("commentTest@gmail.com", "test1234");
		this.memberDao.add(member);
		
		Article article = new Article();
		article.setTitle("댓글달린글");
		article.setContent("달려라");
		article.setWeather(1);
		article.setLocation("북극");
		article.setAuthor(member.getId());
		this.articleDao.add(article);
		
		Comment comment = new Comment();
		comment.setContent("댓글입니다");
		comment.setArticleId(article.getId());
		comment.setAuthorId(member.getId());
		
		this.commentDao.addComment(comment);
	}
}
