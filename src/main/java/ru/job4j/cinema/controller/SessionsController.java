package ru.job4j.cinema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.job4j.cinema.model.SessionPreview;
import ru.job4j.cinema.service.FilmService;
import ru.job4j.cinema.service.HallService;
import ru.job4j.cinema.service.SessionService;

import java.util.ArrayList;
import java.util.Collection;

@Controller
@RequestMapping("/sessions")
public class SessionsController {
    private final SessionService sessionService;
    private final FilmService filmService;
    private final HallService hallService;

    public SessionsController(SessionService sessionService, FilmService filmService, HallService hallService) {
        this.sessionService = sessionService;
        this.filmService = filmService;
        this.hallService = hallService;
    }

    @GetMapping
    public String getAll(Model model) {
        Collection<SessionPreview> previews = new ArrayList<>();
        var sessionList = sessionService.findAll();
        for (var session : sessionList) {
            var film = filmService.findById(session.getFilmId());
            var hall = hallService.findById(session.getHallsId());
            SessionPreview sp = new SessionPreview(
                    session.getId(), film.get().getName(),
                    hall.get().getName(), session.getStartTime(),
                    session.getEndTime(), session.getPrice()
            );
            previews.add(sp);
        }
        model.addAttribute("previews", previews);
        return "views/sessions";
    }
}
