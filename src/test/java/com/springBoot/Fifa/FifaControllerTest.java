package com.springBoot.Fifa;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@SpringBootTest
class FifaControllerTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@BeforeEach
	public void before() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void testFifaGet() throws Exception {
		mockMvc.perform(get("/fifa")).andExpect(status().isOk()).andExpect(view().name("form"))
				.andExpect(model().attributeExists("fifaCommand")).andExpect(model().attributeExists("verkocht"))
				.andExpect(model().attributeExists("uitverkocht"));
	}

	@Test
	public void testFifaPost() throws Exception {
		mockMvc.perform(post("/fifa").param("selectedStadium", "Al Bayt Stadium")).andExpect(status().isOk())
				.andExpect(view().name("result"));
	}

	@Test
	public void testFifaPostId() throws Exception {
		mockMvc.perform(post("/fifa/1").param("selectedMatch", "1")).andExpect(status().isOk())
				.andExpect(view().name("ticket")).andExpect(model().attributeExists("match"))
				.andExpect(model().attributeExists("stadium"));
	}

	@Test
	public void testFifaPostCheck() throws Exception {
		mockMvc.perform(post("/fifa/check").param("email", "@").param("ticket", "5").param("voetbalCode1", "10")
				.param("voetbalCode2", "20")).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/fifa?verkocht=5"));
	}

	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings = {"email", "   "})
	public void testFifaPostCheck_email(String email) throws Exception {
		mockMvc.perform(post("/fifa/check").param("email", email).param("ticket", "25").param("voetbalCode1", "10")
				.param("voetbalCode2", "20")).andExpect(status().isOk()).andExpect(view().name("ticket"));
	}
	
	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings = {"0", "-1", "26", "   "})
	public void testFifaPostCheck_ticket(String ticket) throws Exception {
		mockMvc.perform(post("/fifa/check").param("email", "@").param("ticket", ticket).param("voetbalCode1", "10")
				.param("voetbalCode2", "20")).andExpect(status().isOk()).andExpect(view().name("ticket"));
	}
	
	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings = {"-1", "16"})
	public void testFifaPostCheck_voetbalCode1(String voetbalCode1) throws Exception {
		mockMvc.perform(post("/fifa/check").param("email", "@").param("ticket", "25").param("voetbalCode1", voetbalCode1)
				.param("voetbalCode2", "15")).andExpect(status().isOk()).andExpect(view().name("ticket"));
	}
	
	@Test
	public void testFifaPostCheck_voetbalCode2() throws Exception {
		mockMvc.perform(post("/fifa/check").param("email", "").param("ticket", "25").param("voetbalCode1", "10")
				.param("voetbalCode2", "-1")).andExpect(status().isOk()).andExpect(view().name("ticket"));
	}
}