package by.pochepko.res.spotbot.repository;

import by.pochepko.res.spotbot.model.SpotMessage;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpotMessageRepository extends PagingAndSortingRepository<SpotMessage, Long> {
    Optional<SpotMessage> findSpotMessageByLocation(String location);
}
