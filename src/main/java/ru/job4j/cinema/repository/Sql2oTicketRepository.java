package ru.job4j.cinema.repository;

import org.springframework.stereotype.Service;
import org.sql2o.Sql2o;
import ru.job4j.cinema.model.Ticket;

import java.util.Collection;
import java.util.Optional;

@Service
public class Sql2oTicketRepository implements TicketRepository {
    private final Sql2o sql2o;

    public Sql2oTicketRepository(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public Ticket save(Ticket ticket) {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("INSERT INTO tickets(session_id, row_number, place_number, user_id) "
                            + "VALUES (:sessionId, :rowNumber, :placeNumber, :userId)", true)
                    .addParameter("sessionId", ticket.getSessionId())
                    .addParameter("rowNumber", ticket.getRowNumber())
                    .addParameter("placeNumber", ticket.getPlaceNumber())
                    .addParameter("userId", ticket.getUserId());
            int generatedId = query.executeUpdate().getKey(Integer.class);
            ticket.setId(generatedId);
            return ticket;
        }
    }

    @Override
    public boolean deleteByRowAndPlace(int row, int place) {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("DELETE FROM tickets WHERE row_number = :row AND place_number = :place")
            .addParameter("row", row)
            .addParameter("place", place);
            int rsl = query.executeUpdate().getResult();
            return rsl > 0;
        }
    }

    @Override
    public boolean update(Ticket ticket) {
        return false;
    }

    @Override
    public Optional<Ticket> findTicket(Ticket ticket) {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("SELECT * FROM tickets "
                    + "WHERE session_id = :sessionId AND row_number = :row AND place_number = :place");
            query.addParameter("sessionId", ticket.getSessionId());
            query.addParameter("row", ticket.getRowNumber());
            query.addParameter("place", ticket.getPlaceNumber());
            var rsl = query.setColumnMappings(Ticket.COLUMN_MAPPING).executeAndFetchFirst(Ticket.class);
            return Optional.ofNullable(rsl);
        }
    }

    @Override
    public Collection<Ticket> findAll() {
        try (var connection = sql2o.open()) {
            var query = connection.createQuery("SELECT * FROM tickets");
            return query.setColumnMappings(Ticket.COLUMN_MAPPING).executeAndFetch(Ticket.class);
        }
    }
}
