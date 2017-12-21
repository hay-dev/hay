package com.depromeet.hay.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.depromeet.hay.domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;

@Repository
@Transactional
public class MemberDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	public int addMember(Member member) {
		entityManager.persist(member);
		return member.getId();
	}
	
	public Member getMember(int id) {
		return entityManager.find(Member.class, id);
	}
	
	public List<Member> findMembers(String text) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Member> criteria = builder.createQuery(Member.class);
		Root<Member> root = criteria.from(Member.class);
		Expression<Integer> startIndex = builder.literal(1);
		Expression<Integer> endIndex = builder.sum(builder.locate(root.get("email"), "@"), builder.literal(-1));
		criteria.where(
				builder.or(
						builder.equal(builder.substring(root.get("email"), startIndex, endIndex), text),
						builder.equal(root.get("name"), text)));
		return entityManager.createQuery(criteria).getResultList();
	}
	
	public int deleteAllMembers() {
		CriteriaDelete<Member> delete = entityManager.getCriteriaBuilder().createCriteriaDelete(Member.class);
		Root<Member> root = delete.from(Member.class);
		return entityManager.createQuery(delete).executeUpdate();
	}

	public void modifyMember(Member member) {
		entityManager.merge(member);
	}
}
