package ru.practicum.request.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.request.enums.Status;
import ru.practicum.request.model.Request;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {
    boolean existsByEvent_IdAndEvent_Initiator_Id(Integer eventId, Integer userId);

    List<Request> getByEvent_IdAndStatus(Integer id, Status status);

    List<Request> getByEvent_Initiator_IdAndEvent_Id(Integer userId, Integer eventId);

    List<Request> getByRequester_Id(Integer id);


}