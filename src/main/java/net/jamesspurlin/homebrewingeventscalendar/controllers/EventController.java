package net.jamesspurlin.homebrewingeventscalendar.controllers;

import net.jamesspurlin.homebrewingeventscalendar.models.Event;
import net.jamesspurlin.homebrewingeventscalendar.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/events")
public class EventController extends AbstractBaseController {

    @Autowired
    EventRepository eventRepository;

    @GetMapping
    public String listEvents(Model model) {
        Sort sort = Sort.by("startDate").descending();
        List<Event> allEvents = eventRepository.findAll(sort);
        //LocalDate date = LocalDate.now();
       // LocalDate yesterday = date.minusDays(1);
        //List<Event> allEvents = eventRepository.findAllWithStartDateAfter(yesterday, sort);
        model.addAttribute("events", allEvents);
        return "Events/list";
    }

    @GetMapping(value = "create")
    public String displayCreateEventForm(Model model, HttpServletRequest request) {
        model.addAttribute(new Event());
        model.addAttribute("actionUrl", request.getRequestURI());
        model.addAttribute("title", "Create Event");
        return "Events/create-or-update";
    }

    @PostMapping(value = "create")
    public String processCreateEventForm(@Valid @ModelAttribute Event event,
                                         Errors errors) {

        if (errors.hasErrors())
            return "Events/create-or-update";

        eventRepository.save(event);

        return "redirect:/events/detail/" + event.getUid();
    }

    @GetMapping(value = "detail/{uid}")
    public String displayEventDetails(@PathVariable int uid, Model model) {

        model.addAttribute("title", "Event Details");

        Optional<Event> result = eventRepository.findById(uid);
        if (result.isPresent()) {
            Event event = result.get();
            model.addAttribute(event);
        } else {
            model.addAttribute(MESSAGE_KEY, "warning|No event found with id: " + Integer.toString(uid));
        }

        return "Events/details";
    }

    @GetMapping(value = "update/{uid}")
    public String displayUpdateEventForm(@PathVariable int uid, Model model, HttpServletRequest request) {

        model.addAttribute("title", "Update Event");
        model.addAttribute("actionUrl", request.getRequestURI());

        Optional<Event> event = eventRepository.findById(uid);
        if (event.isPresent()) {
            model.addAttribute(event.get());
        } else {
            model.addAttribute(MESSAGE_KEY, "warning|No event found with id: " + Integer.toString(uid));
        }

        return "Events/create-or-update";
    }

    @PostMapping(value = "update/{uid}")
    public String processUpdateEventForm(@Valid @ModelAttribute Event event,
                                         RedirectAttributes model,
                                         Errors errors) {

        if (errors.hasErrors())
            return "events/create-or-update";

        eventRepository.save(event);
        model.addFlashAttribute(MESSAGE_KEY, "success|Updated event: " + event.getTitle());

        return "redirect:/events/detail/" + event.getUid();
    }

    @PostMapping(value = "delete/{uid}")
    public String processDeleteEventForm(@PathVariable int uid, RedirectAttributes model) {

        Optional<Event> result = eventRepository.findById(uid);
        if (result.isPresent()) {
            eventRepository.delete(result.get());
            model.addFlashAttribute(MESSAGE_KEY, "success|Event deleted");
            return "redirect:/events";
        } else {
            model.addFlashAttribute(MESSAGE_KEY, "danger|Event with ID does not exist: " +  uid);
            return "redirect:/events";
        }
    }

}
