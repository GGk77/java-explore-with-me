package ru.practicum.request.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.request.enums.Status;
import ru.practicum.request.model.Request;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {
    boolean existsByEventIdAndEventInitiatorId(Integer eventId, Integer userId);

    List<Request> getByEventIdAndStatus(Integer id, Status status);

    List<Request> getByEventInitiatorIdAndEventId(Integer userId, Integer eventId);

    List<Request> getByRequesterId(Integer id);


}