package ru.job4j.cinema.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;
import ru.job4j.cinema.model.Film;
import ru.job4j.cinema.model.FilmSession;
import ru.job4j.cinema.model.Hall;
import ru.job4j.cinema.service.FilmService;
import ru.job4j.cinema.service.HallService;
import ru.job4j.cinema.service.SessionService;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SessionsControllerTest {
    private SessionsController sessionsController;
    private SessionService sessionService;
    private FilmService filmService;
    private HallService hallService;

    @BeforeEach
    public void initServices() {
        sessionService = mock(SessionService.class);
        filmService = mock(FilmService.class);
        hallService = mock(HallService.class);
        sessionsController = new SessionsController(sessionService, filmService, hallService);
    }

    @Test
    public void whenRequestSessionsListPageThenGetPageWithSessions() {
        var model = new ConcurrentModel();
        var view = sessionsController.getAll(model);

        assertThat(view).isEqualTo("views/sessions");
    }

    @Test
    public void whenRequestSessionsPageThenGetPageWithSessions() {
        var model = new ConcurrentModel();
        LocalDateTime startTime = LocalDateTime.of(2025, Month.OCTOBER, 10, 18, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(2025, Month.OCTOBER, 10, 20, 0, 0);
        var session1 = new FilmSession(1, 1, startTime, endTime, 10);
        var session2 = new FilmSession(2, 2, startTime, endTime, 20);
        var film1 = new Film("Matrix", "Нео против системы", 1998, 1, 18, 100, 1);
        var film2 = new Film("The Ring", "Смертельная кассета", 2002, 3, 18, 150, 3);
        var hall1 = new Hall("Hall 1", 10, 16, "Малый зал");
        var hall2 = new Hall("Hall 2", 15, 20, "Средний зал");
        var sessions = List.of(session1, session2);
        when(sessionService.findAll()).thenReturn(sessions);
        when(filmService.findById(anyInt())).thenReturn(Optional.of(film1));
        when(hallService.findById(anyInt())).thenReturn(Optional.of(hall1));

        var view = sessionsController.getAll(model);
        var actualSessions = model.getAttribute("previews");

        assertThat(view).isEqualTo("views/sessions");
        assertThat(actualSessions).isNotNull();
    }
}