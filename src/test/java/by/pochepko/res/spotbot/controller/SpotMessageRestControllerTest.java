package by.pochepko.res.spotbot.controller;

import by.pochepko.res.spotbot.SpotbotApplication;
import by.pochepko.res.spotbot.dto.SpotMessageDto;
import by.pochepko.res.spotbot.dto.UpdatedSpotMessageDto;
import by.pochepko.res.spotbot.service.SpotMessageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = SpotbotApplication.class)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class SpotMessageRestControllerTest {
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
        UpdatedSpotMessageDto spotMessage = new UpdatedSpotMessageDto("Visit Colloseum");
        ArgumentCaptor<String> locationCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<UpdatedSpotMessageDto> dtoCapture = ArgumentCaptor.forClass(UpdatedSpotMessageDto.class);
        //when-then
        mockMvc.perform(put("/api/spotmessages/{location}", "Rome").contentType(MediaType.APPLICATION_JSON)
                .content("{\"message\":\"Visit Colloseum\"}"))
                .andDo(print()).andExpect(status().is2xxSuccessful());
        verify(spotMessageService, times(1)).updateSpotMessage(locationCaptor.capture(), dtoCapture.capture());
        assertThat(locationCaptor.getAllValues()).hasSize(1);
        assertThat(locationCaptor.getValue()).isEqualTo("Rome");
        assertThat(dtoCapture.getAllValues()).hasSize(1);
        assertThat(dtoCapture.getValue()).isEqualTo(spotMessage);

    }

    @Test
    @WithMockUser(username = "user", password = "pswd", authorities = "USER")
    void deleteSpotMessage_shouldReturnStatusOk() throws Exception {
        //given
        ArgumentCaptor<String> locationCaptor = ArgumentCaptor.forClass(String.class);
        //when-then
        mockMvc.perform(delete("/api/spotmessages/{location}", "Rome"))
                .andDo(print()).andExpect(status().is2xxSuccessful());
        verify(spotMessageService, times(1)).deleteSpotMessage(locationCaptor.capture());
        assertThat(locationCaptor.getAllValues()).hasSize(1);
        assertThat(locationCaptor.getValue()).isEqualTo("Rome");

    }
}