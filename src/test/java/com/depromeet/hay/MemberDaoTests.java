package com.depromeet.hay;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.depromeet.hay.dao.MemberDao;
import com.depromeet.hay.domain.Member;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberDaoTests {

	@Autowired MemberDao memberDao;

	@Test
	public void deleteAllAndAddAndGet() {
		memberDao.deleteAll();

		Member member1 = new Member("test1234@gmail.com", "test1234");
		Member member2 = new Member("testasdf@gmail.com", "testasdf");

		memberDao.add(member1);
		memberDao.add(member2);

		Member addedMember1 = memberDao.get(member1.getEmail());
		Member addedMember2 = memberDao.get(member2.getEmail());

		assertEquals(member1, addedMember1);
		assertEquals(member2, addedMember2);
	}

	@Test(expected = DuplicateKeyException.class)
	public void addDuplicateEmail() {
		memberDao.deleteAll();

		Member member1 = new Member("test1234@gmail.com", "test1234");
		Member member2 = new Member("test1234@gmail.com", "testasdf");

		memberDao.add(member1);
		memberDao.add(member2);
	}
}
