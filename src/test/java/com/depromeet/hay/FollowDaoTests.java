package com.depromeet.hay;

import com.depromeet.hay.dao.FollowDao;
import com.depromeet.hay.dao.MemberDao;
import com.depromeet.hay.domain.Follow;
import com.depromeet.hay.domain.Member;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class FollowDaoTests {

	@Autowired
	private FollowDao followDao;
	@Autowired
	private MemberDao memberDao;

	@Test
	public void add() {
		memberDao.deleteAll();

		Member following = new Member("test1234@gmail.com", "test1234");
		memberDao.add(following);
		Member follower = new Member("testasdf@gmail.com", "testasdf");
		memberDao.add(follower);

		followDao.add(new Follow(following.getId(), follower.getId()));
	}
}
