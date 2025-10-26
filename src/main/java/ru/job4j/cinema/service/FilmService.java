package ru.job4j.cinema.service;

import ru.job4j.cinema.dto.FilmImageDto;
import ru.job4j.cinema.model.Film;

import java.util.Collection;
import java.util.Optional;

public interface FilmService {

    Film save(Film film, FilmImageDto image);

    boolean deleteById(int id);

    boolean update(Film film, FilmImageDto image);

    Optional<Film> findById(int id);

    Collection<Film> findAll();
}
