package ru.job4j.cinema.service;

import org.springframework.stereotype.Service;
import ru.job4j.cinema.model.Ticket;
import ru.job4j.cinema.repository.Sql2oFilmSessionRepository;
import ru.job4j.cinema.repository.Sql2oHallRepository;
import ru.job4j.cinema.repository.Sql2oTicketRepository;

import java.util.Collection;
import java.util.Optional;

@Service
public class SimpleTicketService implements TicketService {
    private final Sql2oTicketRepository sql2oTicketRepository;
    private final Sql2oFilmSessionRepository sql2oFilmSessionRepository;
    private final Sql2oHallRepository sql2oHallsRepository;

    public SimpleTicketService(
            Sql2oTicketRepository sql2oTicketRepository,
            Sql2oFilmSessionRepository sql2oFilmSessionRepository,
            Sql2oHallRepository sql2oHallsRepository
    ) {
        this.sql2oTicketRepository = sql2oTicketRepository;
        this.sql2oFilmSessionRepository = sql2oFilmSessionRepository;
        this.sql2oHallsRepository = sql2oHallsRepository;
    }

    @Override
    public Ticket save(Ticket ticket) {
        var checkedTicket = sql2oTicketRepository.findTicket(ticket);
        if (checkedTicket.isEmpty()) {
            return sql2oTicketRepository.save(ticket);
        }
        return null;
    }

    @Override
    public boolean deleteByRowAndPlace(int row, int place) {
        return false;
    }

    @Override
    public boolean update(Ticket ticket) {
        return false;
    }

    @Override
    public Optional<Ticket> findTicket(Ticket ticket) {
        return sql2oTicketRepository.findTicket(ticket);
    }

    @Override
    public Collection<Ticket> findAll() {
        return sql2oTicketRepository.findAll();
    }
}
