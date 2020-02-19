package by.pochepko.res.spotbot.controller;

import by.pochepko.res.spotbot.dto.SpotMessageDto;
import by.pochepko.res.spotbot.service.SpotMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class SpotBotRestController {

    @Autowired
    private SpotMessageService spotMessageService;

    @GetMapping(value = "/spotmessages/{location}")
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

    @PutMapping(value = "/spotmessages")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('USER')")

    public void updateSpotMessage(@Valid @RequestBody SpotMessageDto spotMessageDto) {
        spotMessageService.updateSpotMessage(spotMessageDto);
    }

    @DeleteMapping(value = "/spotmessages")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('USER')")
    void deleteSpotMessage(@Valid @RequestBody SpotMessageDto spotMessageDto) {
        spotMessageService.deleteSpotMessage(spotMessageDto);
    }

    @GetMapping(value = "/spotmessages")
    @PreAuthorize("hasAnyAuthority('USER')")
    public List<SpotMessageDto> getSpotMessage(@RequestParam int offset, @RequestParam int limit) {
        return spotMessageService.getSpotMessageList(offset, limit);
    }


}
