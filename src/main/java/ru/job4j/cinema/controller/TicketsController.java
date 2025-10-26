package ru.job4j.cinema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.cinema.model.Ticket;
import ru.job4j.cinema.service.*;

@Controller
@RequestMapping("/tickets")
public class TicketsController {
    private final TicketService ticketService;
    private final SessionService sessionService;
    private final FilmService filmService;
    private final HallService hallService;

    public TicketsController(TicketService ticketService, SessionService sessionService, FilmService filmService, HallService hallService) {
        this.ticketService = ticketService;
        this.sessionService = sessionService;
        this.filmService = filmService;
        this.hallService = hallService;
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("tickets", ticketService.findAll());
        return "tickets/list";
    }

    @GetMapping("/buy/{id}")
    public String getBuyPage(Model model, @PathVariable int id) {
        var session = sessionService.findById(id);
        var film = filmService.findById(session.get().getFilmId());
        var hall = hallService.findById(session.get().getHallsId());
        model.addAttribute("filmSession", session.get());
        model.addAttribute("film", film.get());
        model.addAttribute("hall", hall.get());
        return "tickets/buy";
    }

    @PostMapping("/buy")
    public String buyTicket(Model model, @ModelAttribute Ticket ticket) {
        try {
            var ticketSaved = ticketService.save(ticket);
            if (ticketSaved == null) {
                model.addAttribute("message",
                        "Не удалось приобрести билет на заданное место. Вероятно оно уже занято.");
                return "message/404";
            }
            String message = String.format("Ряд №%d Место №%d", ticket.getRowNumber(), ticket.getPlaceNumber());
            model.addAttribute("message", message);
            return "message/buySuccess";
        } catch (Exception exception) {
            model.addAttribute("message", exception.getMessage());
            return "message/404";
        }
    }
}
