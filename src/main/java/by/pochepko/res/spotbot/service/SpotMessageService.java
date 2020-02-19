package by.pochepko.res.spotbot.service;

import by.pochepko.res.spotbot.dto.SpotMessageDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SpotMessageService {

    SpotMessageDto getSpotMessage(String location);

    SpotMessageDto createSpotMessage(SpotMessageDto spotMessageDto);

    void updateSpotMessage(SpotMessageDto spotMessageDto);

    void deleteSpotMessage(SpotMessageDto spotMessageDto);

    List<SpotMessageDto> getSpotMessageList(int offset, int limit);


}
