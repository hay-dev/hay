package com.depromeet.hay;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import com.depromeet.hay.controller.ArticleController;
import com.depromeet.hay.domain.Article;
import com.depromeet.hay.service.ArticleService;
import com.google.gson.Gson;

/*
 * 컨트롤러는 컨트롤러 연결 자체에 대한 테스트만 권장.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ArticleControllerTests {

	private MockMvc mockMvc;

	@Mock
	private ArticleService articleService;
	@InjectMocks
	private ArticleController articleController;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(articleController).build();
	}

	@Test
	public void writeArticle() throws Exception {
		Article article = new Article();
		article.setTitle("제목");
		article.setContent("내용");
		article.setWeather(2);
		article.setAuthor(1);

		Gson gson = new Gson();
		String articleJson = gson.toJson(article);

		mockMvc.perform(post("/articles")
			.contentType(MediaType.APPLICATION_JSON).content(articleJson))
			.andExpect(status().isCreated());
	}
	
	@Test
	public void getAllArticles() throws Exception {
		mockMvc.perform(get("/articles"))
		.andExpect(status().isOk());
	}
	
	@Test
	public void getArticle() throws Exception {
		mockMvc.perform(get("/articles/1"))
		.andExpect(status().isOk());
	}
}
