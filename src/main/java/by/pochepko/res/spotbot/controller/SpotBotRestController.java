package by.pochepko.res.spotbot.controller;

import by.pochepko.res.spotbot.dto.SpotMessageDto;
import by.pochepko.res.spotbot.service.SpotMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class SpotBotRestController {

    @Autowired
    private SpotMessageService spotMessageService;

    @GetMapping(value = "/spotmessages/{location}")
    @PreAuthorize("hasAnyAuthority('USER')")
    public SpotMessageDto getSpotMessage(@PathVariable String location){
        return spotMessageService.getSpotMessage(location);
    }

    @PostMapping(value = "/spotmessages",consumes = "application/json", produces = "application/json")
    @PreAuthorize("hasAuthority('USER')")
    public SpotMessageDto createSpotMessage(@RequestBody SpotMessageDto spotMessageDto){
        return spotMessageService.createSpotMessage(spotMessageDto);
    }

    @PutMapping(value = "/spotmessages")
    @PreAuthorize("hasAnyAuthority('USER')")

    public void updateSpotMessage(@RequestBody SpotMessageDto spotMessageDto){
        spotMessageService.updateSpotMessage(spotMessageDto);
    }

    @DeleteMapping void deleteSpotMessage(@RequestBody SpotMessageDto spotMessageDto){
        spotMessageService.deleteSpotMessage(spotMessageDto);
    }
}
