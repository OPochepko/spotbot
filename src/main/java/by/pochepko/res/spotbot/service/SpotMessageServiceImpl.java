package by.pochepko.res.spotbot.service;

import by.pochepko.res.spotbot.dto.SpotMessageDto;
import by.pochepko.res.spotbot.mapper.SpotMessageDtoMapper;
import by.pochepko.res.spotbot.model.SpotMessage;
import by.pochepko.res.spotbot.repository.SpotMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Transactional
@Component
public class SpotMessageServiceImpl implements SpotMessageService {

    private String message = "Unknown location";

    private SpotMessageDto unknownLocationSpotMessage = new SpotMessageDto(message);
    @Autowired
    private SpotMessageDtoMapper mapper;

    @Autowired
    private SpotMessageRepository repository;

    @Override
    public SpotMessageDto getSpotMessage(String location) {
        Optional<SpotMessage> spotMessage = repository.findSpotMessageByLocation(location);
        return spotMessage.isEmpty() ? unknownLocationSpotMessage : mapper.modelToDto(spotMessage.get());
    }

    @Override
    public SpotMessageDto createSpotMessage(SpotMessageDto spotMessageDto) {
        return mapper.modelToDto(repository.save(mapper.dtoToModel(spotMessageDto)));
    }

    @Override
    public void updateSpotMessage(SpotMessageDto spotMessageDto) {
        repository.save(mapper.dtoToModel(spotMessageDto));
    }

    @Override
    public void deleteSpotMessage(SpotMessageDto spotMessageDto) {
        repository.delete(mapper.dtoToModel(spotMessageDto));
    }

    @Override
    public List<SpotMessageDto> getSpotMessageList() {
        return StreamSupport.stream(repository.findAll().spliterator(), true)
                .map(mapper::modelToDto)
                .collect(Collectors.toList());
    }
}
