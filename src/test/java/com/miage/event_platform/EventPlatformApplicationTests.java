package com.miage.event_platform;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@SpringBootTest
@AutoConfigureMockMvc
class EventPlatformApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
	}

	@Test
	public void shouldReturnIndexPage() throws Exception {
		mockMvc.perform(get("/"))
				.andExpect(status().isOk())
				.andExpect(view().name("index"));
	}

	@Test
	public void shouldRedirectUnauthenticatedUserToLogin() throws Exception {
		mockMvc.perform(get("/events/new"))
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("http://localhost/login"));
	}

	@Test
	@WithMockUser
	public void shouldAllowAuthenticatedUserToAccessEventForm() throws Exception {
		mockMvc.perform(get("/events/new"))
				.andExpect(status().isOk())
				.andExpect(view().name("event_form"));
	}

	@Test
	@WithMockUser
	public void shouldCreateEvent() throws Exception {
		mockMvc.perform(post("/events")
				.with(csrf()) // Include CSRF token for POST requests
				.param("title", "Test Event")
				.param("date", "2024-12-31T23:59")
				.param("location", "Test Location")
				.param("numberOfPlaces", "10")
				.param("description", "Test Description"))
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/"));
	}
}
