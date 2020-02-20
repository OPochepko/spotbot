package by.pochepko.res.spotbot.service;

import by.pochepko.res.spotbot.dto.SpotMessageDto;
import by.pochepko.res.spotbot.dto.UpdatedSpotMessageDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SpotMessageService {

    SpotMessageDto getSpotMessage(String location);

    SpotMessageDto createSpotMessage(SpotMessageDto spotMessageDto);

    void updateSpotMessage(String location, UpdatedSpotMessageDto updatedSpotMessageDto);

    void deleteSpotMessage(String location);

    List<SpotMessageDto> getSpotMessageList(int offset, int limit);


}
