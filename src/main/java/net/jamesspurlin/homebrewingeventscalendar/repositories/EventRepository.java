package net.jamesspurlin.homebrewingeventscalendar.repositories;

import net.jamesspurlin.homebrewingeventscalendar.models.Event;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;


public interface EventRepository extends JpaRepository<Event, Integer> {

    public List<Event>findAll(Sort sort);

    @Query("select a from Event a where a.startDate >= :currentDate")
    List<Event> findAllWithStartDateAfter(@Param("currentDate") LocalDate currentDate, Sort sort);

}