package com.depromeet.hay;

//import com.depromeet.hay.dao.FollowDao;
import com.depromeet.hay.dao.MemberDao;
import com.depromeet.hay.domain.Member;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberDaoTests {

    @Autowired
    private MemberDao memberDao;
//    @Autowired
//    private FollowDao followDao;

    @Test
    public void deleteAllAndAddAndGet() {
        memberDao.deleteAllMembers();

        Member member1 = addMember("test1234@gmail.com", "test1234");
        Member member2 = addMember("testasdf@gmail.com", "testasdf");

        Member addedMember1 = memberDao.getMember(member1.getId());
        Member addedMember2 = memberDao.getMember(member2.getId());

        assertEquals(member1, addedMember1);
        assertEquals(member2, addedMember2);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void addDuplicatedEmail() {
        memberDao.deleteAllMembers();

        Member member1 = addMember("test1234@gmail.com", "test1234");
        Member member2 = addMember("test1234@gmail.com", "testasdf");

        memberDao.addMember(member1);
        memberDao.addMember(member2);
    }

    @Test
    public void modifyMember() {
        memberDao.deleteAllMembers();

        Member member1 = addMember("test1234@gmail.com", "test1234");
        Member member2 = addMember("testasdf@gmail.com", "testasdf");

        member1.setEmail("modify1234@naver.com");
        memberDao.modifyMember(member1);

        assertEquals(member1.getEmail(), "modify1234@naver.com");
    }

    @Test
    public void search() {
        memberDao.deleteAllMembers();

        Member member = addMember("test1234@gmail.com", "test1234");

        List<Member> memberList = null;

        memberList = memberDao.findMember("test123");
        assertEquals(memberList.size(), 0);

        memberList = memberDao.findMember("test1234");
        assertEquals(memberList.size(), 1);
    }

	@Test
	public void getFollowers() {
		memberDao.deleteAllMembers();

		Member member = addMember("test1234@gmail.com", "test1234");
		Member member2 = addMember("test5678@gmail.com", "test5678");
		Member member3 = addMember("testasdf@gmail.com", "testasdf");
		Member member4 = addMember("testqwer@gmail.com", "testqwer");

        member.addFollower(member2);
        member.addFollower(member3);
        member.addFollower(member4);

		List<Member> members = new ArrayList<>();
		members.add(member2);
		members.add(member3);
		members.add(member4);

        List<Member> followers = memberDao.getMember(member.getId()).getFollowers();

        Comparator<Member> comparator = Comparator.comparingInt(Member::getId);
        members.sort(comparator);
        followers.sort(comparator);

		for (int i = 0; i < followers.size(); i++) {
			assertEquals(followers.get(i).getId(), members.get(i).getId());
		}
	}

	@Test
	public void getFollowings() {
		memberDao.deleteAllMembers();

		Member member = addMember("test1234@gmail.com", "test1234");
		Member member2 = addMember("test5678@gmail.com", "test5678");
		Member member3 = addMember("testasdf@gmail.com", "testasdf");
		Member member4 = addMember("testqwer@gmail.com", "testqwer");

        member.addFollower(member2);
        member.addFollower(member3);
        member.addFollower(member4);

		List<Member> members = new ArrayList<>();
		members.add(member2);
		members.add(member3);
		members.add(member4);

		List<Member> followings = memberDao.getMember(member.getId()).getFollowings();

		Comparator<Member> comparator = Comparator.comparingInt(Member::getId);
		members.sort(comparator);
		followings.sort(comparator);

		for (int i = 0; i < followings.size(); i++) {
			assertEquals(followings.get(i).getId(), members.get(i).getId());
		}
	}

    private Member addMember(String email, String password) {
        Member member = new Member(email, password);
        memberDao.addMember(member);
        return member;
    }
}
