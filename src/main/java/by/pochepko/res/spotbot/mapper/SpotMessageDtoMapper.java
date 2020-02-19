package by.pochepko.res.spotbot.mapper;

import by.pochepko.res.spotbot.dto.SpotMessageDto;
import by.pochepko.res.spotbot.model.SpotMessage;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SpotMessageDtoMapper {

    SpotMessage dtoToModel(SpotMessageDto spotMessageDto);

    SpotMessageDto modelToDto(SpotMessage spotMessage);
}
