package ru.practicum.event.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import ru.practicum.event.enums.EventState;
import ru.practicum.event.model.Event;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer>, JpaSpecificationExecutor<Event> {
    List<Event> getByIdIn(List<Integer> ids);

    List<Event> getByInitiator_IdOrderByEventDateDesc(Integer id, Pageable pageable);

    @Query("SELECT e FROM Event e WHERE (e.initiator.id IN (?1) OR (?1) is null) " +
            "AND (e.category.id IN (?2) or (?2) is null) AND (e.state IN (?3) or (?3) is null) " +
            "AND e.eventDate > ?4 AND e.eventDate < ?5")
    List<Event> findAllUsersEvents(List<Integer> users, List<Integer> categories, List<EventState> states,
                                       LocalDateTime start, LocalDateTime end, Pageable pageable);

    @Query("SELECT e FROM Event e WHERE" +
            " (upper(e.annotation) like upper(CONCAT('%',?1,'%')) or upper(e.description)" +
            " like upper(CONCAT('%',?1,'%')) or ?1 is null) and e.category.id IN (?2) and" +
            " e.paid = ?3 and e.eventDate > ?4 and e.eventDate < ?5")
    List<Event> getFilteredEvents(String text, List<Integer> categories,
                                  Boolean paid, LocalDateTime start, LocalDateTime end,
                                  Pageable pageable);
}