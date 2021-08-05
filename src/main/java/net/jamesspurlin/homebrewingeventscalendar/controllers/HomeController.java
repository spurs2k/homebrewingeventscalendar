package net.jamesspurlin.homebrewingeventscalendar.controllers;

import net.jamesspurlin.homebrewingeventscalendar.models.Event;
import net.jamesspurlin.homebrewingeventscalendar.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    EventRepository eventRepository;

    @GetMapping(value = "/")
    public String index(Model model) {
        Sort sort = Sort.by("startDate").ascending();
        List<Event> allEvents = eventRepository.findAll(sort);
        model.addAttribute("events", allEvents);
        return "Events/list";
    }
}