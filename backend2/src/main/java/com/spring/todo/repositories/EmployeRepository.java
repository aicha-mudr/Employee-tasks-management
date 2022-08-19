package com.spring.todo.repositories;

import com.spring.todo.model.Employe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.ZonedDateTime;
import java.util.List;

public interface EmployeRepository extends JpaRepository<Employe, Long> {

    List<Employe> findCategoryByAdminId(Long userId);
    List<Employe> findCategoryByUserId(Long userId);

    @Query("select t.employe.id from Todo t where t.id = :todoId")
    Long findCategoryByTodoId(Long todoId);

    @Query("select c from Employe c inner join Todo t on t.employe.id = c.id where t.startDate >= :startDate and t.startDate <= :endDate and c.user.id = :userId")
    List<Employe> getAllTodoByCategoriesForToday(@Param("startDate") ZonedDateTime startDate, @Param("endDate") ZonedDateTime endDate, @Param("userId")Long userId);
}
