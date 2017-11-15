package com.depromeet.hay;

import static org.junit.Assert.assertEquals;

import com.depromeet.hay.dao.FollowDao;
import com.depromeet.hay.domain.Follow;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.depromeet.hay.dao.MemberDao;
import com.depromeet.hay.domain.Member;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberDaoTests {

	@Autowired
	private MemberDao memberDao;
	@Autowired
	private FollowDao followDao;

	@Test
	public void deleteAllAndAddAndGet() {
		memberDao.deleteAll();

		Member member1 = addMember("test1234@gmail.com", "test1234");
		Member member2 = addMember("testasdf@gmail.com", "testasdf");

		Member addedMember1 = memberDao.get(member1.getEmail());
		Member addedMember2 = memberDao.get(member2.getEmail());

		assertEquals(member1, addedMember1);
		assertEquals(member2, addedMember2);
	}

	@Test(expected = DuplicateKeyException.class)
	public void addDuplicatedEmail() {
		memberDao.deleteAll();

		Member member1 = addMember("test1234@gmail.com", "test1234");
		Member member2 = addMember("test1234@gmail.com", "testasdf");

		memberDao.add(member1);
		memberDao.add(member2);
	}

	@Test
	public void getFollowers() {
		memberDao.deleteAll();

		Member member1 = addMember("test1234@gmail.com", "test1234");
		Member member2 = addMember("test5678@gmail.com", "test5678");
		Member member3 = addMember("testasdf@gmail.com", "testasdf");
		Member member4 = addMember("testqwer@gmail.com", "testqwer");

		followDao.add(new Follow(member2.getId(), member1.getId()));
		followDao.add(new Follow(member3.getId(), member1.getId()));
		followDao.add(new Follow(member4.getId(), member1.getId()));

		List<Member> members = new ArrayList<>();
		members.add(member2);
		members.add(member3);
		members.add(member4);
		members.sort(Comparator.comparingInt(Member::getId));

		List<Member> followers = memberDao.getFollowers(member1.getId());
		followers.sort(Comparator.comparingInt(Member::getId));

		for (int i = 0; i < followers.size(); i++) {
			assertEquals(followers.get(i).getId(), members.get(i).getId());
		}
	}

	private Member addMember(String email, String password) {
		Member member = new Member(email, password);
		memberDao.add(member);
		return member;
	}
}
