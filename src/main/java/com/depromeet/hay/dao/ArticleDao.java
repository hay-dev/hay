package com.depromeet.hay.dao;

import com.depromeet.hay.domain.Article;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

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

    public List<Article> findArticles(String text, int beforeAddedThan) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Article> criteria = builder.createQuery(Article.class);
        Root<Article> root = criteria.from(Article.class);

        String likeExpression = "%" + text + "%";
        criteria.where(
                builder.lt(root.get("id"), beforeAddedThan),
                builder.or(
                        builder.like(root.get("title"), likeExpression),
                        builder.like(root.get("content"), likeExpression)));
        criteria.orderBy(builder.desc(root.get("id")));
        return entityManager.createQuery(criteria).getResultList();
    }

    public List<Article> findArticles(String text) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Article> criteria = builder.createQuery(Article.class);
        Root<Article> root = criteria.from(Article.class);

        String likeExpression = "%" + text + "%";
        criteria.where(
                builder.or(
                        builder.like(root.get("title"), likeExpression),
                        builder.like(root.get("content"), likeExpression)));
        criteria.orderBy(builder.desc(root.get("id")));
        return entityManager.createQuery(criteria).getResultList();
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
