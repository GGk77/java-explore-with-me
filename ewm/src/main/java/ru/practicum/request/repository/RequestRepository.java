package ru.practicum.request.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.practicum.request.enums.Status;
import ru.practicum.request.model.Request;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {
    List<Request> getByIdOrderByStatus(Integer id, Status status);

    List<Request> getByEvent_Initiator_IdAndEvent_Id(Integer userId, Integer eventId);



    List<Request> getByIdOrderById(Integer id);




}