package ru.job4j.cinema.service;

import ru.job4j.cinema.model.FilmSession;

import java.util.Collection;
import java.util.Optional;

public interface SessionService {

    FilmSession save(FilmSession filmSession);

    boolean deleteById(int id);

    boolean update(FilmSession filmSession);

    Optional<FilmSession> findById(int id);

    Collection<FilmSession> findAll();
}
