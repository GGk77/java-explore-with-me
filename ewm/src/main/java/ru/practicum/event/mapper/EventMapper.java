package ru.practicum.event.mapper;

import org.springframework.stereotype.Component;
import ru.practicum.category.dto.CategoryDto;
import ru.practicum.event.dto.EventDto;
import ru.practicum.event.dto.EventShortDto;
import ru.practicum.event.dto.LocationDto;
import ru.practicum.event.dto.NewEventDto;
import ru.practicum.event.enums.EventState;
import ru.practicum.event.model.Event;
import ru.practicum.user.mapper.UserMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class EventMapper {
    public static Event toEvent(NewEventDto eventDto) {
        return Event.builder()
                .annotation(eventDto.getAnnotation())
                .description(eventDto.getDescription())
                .eventDate(eventDto.getEventDate())
                .paid(eventDto.getPaid())
                .participantLimit(eventDto.getParticipantLimit())
                .moderation(eventDto.getRequestModeration())
                .title(eventDto.getTitle())
                .state(EventState.PENDING)
                .lat(eventDto.getLocation().getLat())
                .lon(eventDto.getLocation().getLon())
                .build();
    }

    public static EventDto toEventDto(Event event) {
        int confirmRequests = Optional.ofNullable(event.getParticipants())
                .orElse(new ArrayList<>()).size();
        return EventDto.builder()
                .id(event.getId())
                .eventDate(event.getEventDate())
                .annotation(event.getAnnotation())
                .description(event.getDescription())
                .requestModeration(event.getModeration())
                .title(event.getTitle())
                .paid(event.getPaid())
                .participantLimit(event.getParticipantLimit())
                .confirmedRequests(confirmRequests)
                .state(event.getState())
                .publishedOn(event.getPublishedOn())
                .createdOn(event.getCreatedOn())
                .category(CategoryDto.builder()
                        .id(event.getCategory().getId())
                        .name(event.getCategory().getName())
                        .build())
                .location(LocationDto.builder()
                        .lat(event.getLat())
                        .lon(event.getLon())
                        .build())
                .initiator(UserMapper.toUserShortDto(event.getInitiator()))
                .views(null)
                .build();
    }

    public static EventShortDto toEventShortDto(Event event) {
        Integer confirmRequests = Optional.ofNullable(event.getParticipants())
                .orElse(new ArrayList<>()).size();
        return EventShortDto.builder()
                .id(event.getId())
                .eventDate(event.getEventDate())
                .annotation(event.getAnnotation())
                .title(event.getTitle())
                .paid(event.getPaid())
                .confirmedRequests(confirmRequests)
                .category(CategoryDto.builder()
                        .id(event.getCategory().getId())
                        .name(event.getCategory().getName())
                        .build())
                .initiator(UserMapper.toUserShortDto(event.getInitiator()))
                .views(null)
                .build();
    }

    public static List<EventShortDto> toEventShortDto(List<Event> event) {
        return event.stream().map(EventMapper::toEventShortDto).collect(Collectors.toList());
    }
}
