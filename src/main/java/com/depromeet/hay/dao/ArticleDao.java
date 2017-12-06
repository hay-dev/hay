package com.depromeet.hay.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.depromeet.hay.domain.Article;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
@Transactional
public class ArticleDao {
	
	public static final String NAMESPACE = "com.depromeet.hay.mapper.ArticleMapper.";

	@Autowired
	private SqlSession sqlSession;

	@PersistenceContext
	private EntityManager entityManager;
	
	public int addArticle(Article article) {
		entityManager.persist(article);
		return article.getId();
	}

	public Article getArticle(int id) {
		return entityManager.find(Article.class, id);
//		return entityManager.createQuery("SELECT * FROM article WHERE id=" + id, Article.class).getSingleResult();
	}

	public List<Article> getAllArticles() {
		CriteriaQuery<Article> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(Article.class);
		Root<Article> root = criteriaQuery.from(Article.class);
		criteriaQuery.select(root);
		return entityManager.createQuery(criteriaQuery).getResultList();
	}
	
	public void deleteAllArticles() {
		CriteriaDelete<Article> criteriaDelete = entityManager.getCriteriaBuilder().createCriteriaDelete(Article.class);
		criteriaDelete.from(Article.class);
		entityManager.createQuery(criteriaDelete);
	}

	public void deleteArticle(int id) {
		entityManager.remove(getArticle(id));
	}

	public void modifyArticle(Article article) {
		entityManager.merge(article);
	}
}
