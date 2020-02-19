package by.pochepko.res.spotbot.service;

import by.pochepko.res.spotbot.dto.SpotMessageDto;
import by.pochepko.res.spotbot.mapper.SpotMessageDtoMapper;
import by.pochepko.res.spotbot.model.SpotMessage;
import by.pochepko.res.spotbot.repository.SpotMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
public class SpotMessageServiceImpl implements SpotMessageService {

    @Autowired
    MessageSource messageSource;

    @Autowired
    private SpotMessageDtoMapper mapper;

    @Autowired
    private SpotMessageRepository repository;

    @Override
    public SpotMessageDto getSpotMessage(String location) {
        Optional<SpotMessage> spotMessage = repository.findSpotMessageByLocation(location);
        return spotMessage.isEmpty() ? new SpotMessageDto(location, messageSource.getMessage("text.unknownLocation", null, Locale.getDefault())) : mapper.modelToDto(spotMessage.get());
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
    public List<SpotMessageDto> getSpotMessageList(int offset, int limit) {

        return repository.findAll(PageRequest.of(getCurrentPage(offset, limit), limit, Sort.by("location"))).stream()
                .map(mapper::modelToDto)
                .collect(Collectors.toList());
    }

    private int getCurrentPage(int offset, int limit) {
        return (int) Math.ceil((double) offset / limit);
    }

}
