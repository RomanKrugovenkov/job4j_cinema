package ru.job4j.cinema.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;
import ru.job4j.cinema.model.Film;
import ru.job4j.cinema.model.FilmSession;
import ru.job4j.cinema.model.Hall;
import ru.job4j.cinema.model.Ticket;
import ru.job4j.cinema.service.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TicketsControllerTest {
    private TicketsController ticketsController;
    private TicketService ticketService;
    private SessionService sessionService;
    private FilmService filmService;
    private HallService hallService;

    @BeforeEach
    public void initServices() {
        ticketService = mock(TicketService.class);
        sessionService = mock(SessionService.class);
        filmService = mock(FilmService.class);
        hallService = mock(HallService.class);
        ticketsController = new TicketsController(ticketService, sessionService, filmService, hallService);
    }

    @Test
    public void whenRequestRegisterListPageThenGetPageWithRegister() {
        var model = new ConcurrentModel();
        var view = ticketsController.getAll(model);

        assertThat(view).isEqualTo("tickets/list");
    }

    @Test
    public void whenRequestBuyPageThenGetPageWithBuy() {
        var model = new ConcurrentModel();
        LocalDateTime startTime = LocalDateTime.of(2025, Month.OCTOBER, 10, 18, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(2025, Month.OCTOBER, 10, 20, 0, 0);
        var session = new FilmSession(1, 1, startTime, endTime, 10);
        var film = new Film("Matrix", "Нео против системы", 1998, 1, 18, 100, 1);
        var hall = new Hall("Hall 1", 10, 16, "Малый зал");
        when(sessionService.findById(anyInt())).thenReturn(Optional.of(session));
        when(filmService.findById(anyInt())).thenReturn(Optional.of(film));
        when(hallService.findById(anyInt())).thenReturn(Optional.of(hall));

        var view = ticketsController.getBuyPage(model, 1);
        var actualSession = model.getAttribute("filmSession");
        var actualFilm = model.getAttribute("film");
        var actualHall = model.getAttribute("hall");
        assertThat(view).isEqualTo("tickets/buy");
        assertThat(actualSession).isEqualTo(session);
        assertThat(actualFilm).isEqualTo(film);
        assertThat(actualHall).isEqualTo(hall);
    }

    @Test
    public void whenRequestBuyTicketThenGetErrorMessage() {
        var message = "Не удалось приобрести билет на заданное место. Вероятно оно уже занято.";
        var ticket = new Ticket(1, 1, 1, 1);
        var model = new ConcurrentModel();
        when(ticketService.save(any())).thenReturn(null);

        var view = ticketsController.buyTicket(model, ticket);
        var actualMessage = model.getAttribute("message");
        assertThat(actualMessage).isEqualTo(message);
        assertThat(view).isEqualTo("message/404");
    }

    @Test
    public void whenPostTicketThenBuySuccess() throws IOException {
        var ticket = new Ticket(1, 1, 1, 1);
        var model = new ConcurrentModel();
        when(ticketService.save(any())).thenReturn(ticket);

        var view = ticketsController.buyTicket(model, ticket);
        var actualMessage = model.getAttribute("message");

        assertThat(actualMessage).isEqualTo("Ряд №1 Место №1");
        assertThat(view).isEqualTo("message/buySuccess");
    }
}