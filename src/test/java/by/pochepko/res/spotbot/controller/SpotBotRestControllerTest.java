package by.pochepko.res.spotbot.controller;

import by.pochepko.res.spotbot.SpotbotApplication;
import by.pochepko.res.spotbot.dto.SpotMessageDto;
import by.pochepko.res.spotbot.service.SpotMessageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = SpotbotApplication.class)
@AutoConfigureMockMvc
class SpotBotRestControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    private SpotMessageService spotMessageService;

    @Test
    @WithMockUser(username = "user", password = "pswd", authorities = "USER")
    void getSpotMessage() throws Exception {
        //given
        SpotMessageDto spotMessage = new SpotMessageDto("Rome", "Visit Colloseum");
        when(spotMessageService.getSpotMessage("Rome")).thenReturn(spotMessage);
        //then
        mockMvc.perform(get("/api/spotmessages/{location}", "Rome"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"location\":\"Rome\",\"message\":\"Visit Colloseum\"}")));
    }

    @Test
    @WithMockUser(username = "user", password = "pswd", authorities = "USER")
    void createSpotMessage_shouldReturnSameDtoAndStatusOk() throws Exception {
        //given
        SpotMessageDto spotMessage = new SpotMessageDto("Rome", "Visit Colloseum");
        when(spotMessageService.createSpotMessage(spotMessage)).thenReturn(spotMessage);
        //when-then
        mockMvc.perform(post("/api/spotmessages").contentType(MediaType.APPLICATION_JSON)
                .content("{\"location\":\"Rome\",\"message\":\"Visit Colloseum\"}"))
                .andDo(print()).andExpect(status().is2xxSuccessful())
                .andExpect(content().string(containsString("{\"location\":\"Rome\",\"message\":\"Visit Colloseum\"}")));
    }

    @Test
    @WithMockUser(username = "user", password = "pswd", authorities = "USER")
    void updateSpotMessage_shouldReturnStatusOk() throws Exception {
        //given
        SpotMessageDto spotMessage = new SpotMessageDto("Rome", "Visit Colloseum");
        //when-then
        mockMvc.perform(put("/api/spotmessages").contentType(MediaType.APPLICATION_JSON)
                .content("{\"location\":\"Rome\",\"message\":\"Visit Colloseum\"}"))
                .andDo(print()).andExpect(status().is2xxSuccessful());
    }

    @Test
    @WithMockUser(username = "user", password = "pswd", authorities = "USER")
    void deleteSpotMessage_shouldReturnStatusOk() throws Exception {
        mockMvc.perform(delete("/api/spotmessages").contentType(MediaType.APPLICATION_JSON)
                .content("{\"location\":\"Rome\",\"message\":\"Visit Colloseum\"}"))
                .andDo(print()).andExpect(status().is2xxSuccessful());
    }
}