package com.depromeet.hay;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.depromeet.hay.controller.MemberController;
import com.depromeet.hay.domain.Member;
import com.depromeet.hay.service.MemberService;
import com.google.gson.Gson;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberControllerTests {

	private MockMvc mockMvc;

	@Mock
	private MemberService memberService;
	@InjectMocks
	private MemberController memberController;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(memberController).build();
	}

	@Test
	public void signUp() throws Exception {
		Member member = new Member();
		member.setEmail("testing@gmail.com");
		member.setPassword("test1234");

		String memberJson = new Gson().toJson(member);

		mockMvc.perform(post("/members").contentType(MediaType.APPLICATION_JSON).content(memberJson))
				.andExpect(status().isCreated());
	}
	
	@Test
	public void modifyMember() throws Exception {
		Member member = new Member();
		member.setEmail("testing@gmail.com");
		member.setPassword("test1234");

		String memberJson = new Gson().toJson(member);
		
		mockMvc.perform(put("/members/92").contentType(MediaType.APPLICATION_JSON).content(memberJson))
		.andExpect(status().isOk());
	}

	@Test
	public void getFollowers() throws Exception {
		MvcResult result = mockMvc.perform(get("/members/1/followers"))
				.andExpect(status().isOk())
				.andReturn();
		String content = result.getResponse().getContentAsString();
		
//		Type listType = new TypeToken<List<Member>>() {}.getType();
//		List<Member> followers = new Gson().fromJson(content, listType);
	}

	@Test
	public void getFollowings() throws Exception {
		mockMvc.perform(get("/members/1/followings"))
		.andExpect(status().isOk());
	}
}
