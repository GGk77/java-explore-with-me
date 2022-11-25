package ru.practicum.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.practicum.category.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query(value = "SELECT count(e.event_id) > 0 FROM events e WHERE e.category_id = :id", nativeQuery = true)
    boolean existsEventByCategoryId(@Param("id") Integer id);
}