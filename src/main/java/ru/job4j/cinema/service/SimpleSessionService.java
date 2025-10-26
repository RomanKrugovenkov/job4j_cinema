package ru.job4j.cinema.service;

import org.springframework.stereotype.Service;
import ru.job4j.cinema.model.FilmSession;
import ru.job4j.cinema.repository.Sql2oFilmSessionRepository;

import java.util.Collection;
import java.util.Optional;

@Service
public class SimpleSessionService implements SessionService {
        private final Sql2oFilmSessionRepository sql2oFilmSessionRepository;

    public SimpleSessionService(Sql2oFilmSessionRepository sql2oFilmSessionRepository) {
        this.sql2oFilmSessionRepository = sql2oFilmSessionRepository;
    }

    @Override
    public FilmSession save(FilmSession filmSession) {
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }

    @Override
    public boolean update(FilmSession filmSession) {
        return false;
    }

    @Override
    public Optional<FilmSession> findById(int id) {
        return sql2oFilmSessionRepository.findById(id);
    }

    @Override
    public Collection<FilmSession> findAll() {
        return sql2oFilmSessionRepository.findAll();
    }
}
