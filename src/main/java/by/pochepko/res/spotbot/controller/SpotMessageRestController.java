package by.pochepko.res.spotbot.controller;

import by.pochepko.res.spotbot.dto.SpotMessageDto;
import by.pochepko.res.spotbot.dto.UpdatedSpotMessageDto;
import by.pochepko.res.spotbot.service.SpotMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class SpotMessageRestController {

    @Autowired
    private SpotMessageService spotMessageService;

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(value = "/spotmessages/{location}", produces = "application/json")
    @PreAuthorize("hasAnyAuthority('USER')")
    public SpotMessageDto getSpotMessage(@Valid @PathVariable String location) {
        return spotMessageService.getSpotMessage(location);
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(value = "/spotmessages", consumes = "application/json", produces = "application/json")
    @PreAuthorize("hasAuthority('USER')")
    public SpotMessageDto createSpotMessage(@Valid @RequestBody SpotMessageDto spotMessageDto) {
        return spotMessageService.createSpotMessage(spotMessageDto);
    }

    @PutMapping(value = "/spotmessages/{location}", consumes = "application/json")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('USER')")
    public void updateSpotMessage(@Valid @RequestBody UpdatedSpotMessageDto updatedSpotMessageDto, @PathVariable String location) {
        spotMessageService.updateSpotMessage(location, updatedSpotMessageDto);
    }

    @DeleteMapping(value = "/spotmessages/{location}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('USER')")
    void deleteSpotMessage(@PathVariable String location) {
        spotMessageService.deleteSpotMessage(location);
    }

    @GetMapping(value = "/spotmessages", produces = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('USER')")
    public List<SpotMessageDto> getSpotMessage(@RequestParam int offset, @RequestParam int limit) {
        return spotMessageService.getSpotMessageList(offset, limit);
    }


}
