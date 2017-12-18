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
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;

@Repository
@Transactional
public class ArticleDao {

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
		CriteriaQuery<Article> criteria = entityManager.getCriteriaBuilder().createQuery(Article.class);
		Root<Article> root = criteria.from(Article.class);
		criteria.select(root);
		return entityManager.createQuery(criteria).getResultList();
	}

	public Article getRecentArticle() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Article> criteria = builder.createQuery(Article.class);
		Root<Article> root = criteria.from(Article.class);
		criteria.orderBy(builder.desc(root.get("id")));
		TypedQuery<Article> query = entityManager.createQuery(criteria.select(root));
		query.setMaxResults(1);
		return query.getSingleResult();
	}
	
	public void deleteAllArticles() {
		CriteriaDelete<Article> criteria = entityManager.getCriteriaBuilder().createCriteriaDelete(Article.class);
		criteria.from(Article.class);
		entityManager.createQuery(criteria);
	}

	public void deleteArticle(int id) {
		entityManager.remove(getArticle(id));
	}

	public void modifyArticle(Article article) {
		entityManager.merge(article);
	}
}
