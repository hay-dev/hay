package com.depromeet.hay;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.depromeet.hay.controller.ArticleController;
import com.depromeet.hay.domain.Article;
import com.depromeet.hay.service.ArticleService;
import com.google.gson.Gson;

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
}
