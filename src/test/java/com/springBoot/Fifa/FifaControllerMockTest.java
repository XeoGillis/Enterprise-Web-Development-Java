package com.springBoot.Fifa;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

import domain.Wedstrijd;
import domain.WedstrijdTicket;
import service.VoetbalService;

class FifaControllerMockTest {

	private FifaController controller;
	private MockMvc mockMvc;

	@Mock
	private VoetbalService mock;

	@BeforeEach
	public void before() {
		MockitoAnnotations.openMocks(this);
		controller = new FifaController();
		mockMvc = standaloneSetup(controller).build();
	}

	@Test
	public void testFifaGet() throws Exception {
		List<String> expResult = new ArrayList<>(
				Arrays.asList(new String[] { "Al Bayt Stadium", "Al Thumama Stadium" }));
		Mockito.when(mock.getStadiumList()).thenReturn(expResult);
		ReflectionTestUtils.setField(controller, "voetbalService", mock);
		mockMvc.perform(get("/fifa")).andExpect(status().isOk()).andExpect(view().name("form"))
				.andExpect(model().attribute("stadiumList", expResult));
	}

	@Test
	public void testFifaPost() throws Exception {
		List<WedstrijdTicket> expResult = new ArrayList<>();
		expResult.add(new WedstrijdTicket(new Wedstrijd("4", new String[] { "Spanje", "Duitsland" }, 28, 18), 95));
		expResult.add(new WedstrijdTicket(new Wedstrijd("5", new String[] { "Frankrijk", "Denemarken" }, 30, 15), 45));
		expResult.add(new WedstrijdTicket(new Wedstrijd("8", new String[] { "Nederland", "Qatar" }, 31, 21), 16));
		Mockito.when(mock.getWedstrijdenByStadium("Al Thumama Stadium")).thenReturn(expResult);
		ReflectionTestUtils.setField(controller, "voetbalService", mock);
		mockMvc.perform(post("/fifa").param("selectedStadium", "Al Thumama Stadium")).andExpect(status().isOk())
				.andExpect(view().name("result")).andExpect(model().attribute("matchList", expResult));
	}

	@Test
	public void testFifaPostId() throws Exception {
		WedstrijdTicket expResult = new WedstrijdTicket(
				new Wedstrijd("8", new String[] { "Nederland", "Qatar" }, 31, 21), 16);
		Mockito.when(mock.getWedstrijd("8")).thenReturn(expResult);
		ReflectionTestUtils.setField(controller, "voetbalService", mock);
		mockMvc.perform(post("/fifa/8").param("selectedMatch", "8")).andExpect(status().isOk())
				.andExpect(view().name("ticket")).andExpect(model().attribute("match", expResult));
	}

}
