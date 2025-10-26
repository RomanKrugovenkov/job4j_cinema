package ru.job4j.cinema.service;

import org.springframework.stereotype.Service;
import ru.job4j.cinema.dto.FilmImageDto;
import ru.job4j.cinema.model.Film;
import ru.job4j.cinema.repository.FilmRepository;

import java.util.Collection;
import java.util.Optional;

@Service
public class SimpleFilmService implements FilmService {
    private final FilmRepository sqloFilmRepository;

    public SimpleFilmService(FilmRepository sqloFilmRepository) {
        this.sqloFilmRepository = sqloFilmRepository;
    }

    @Override
    public Film save(Film film, FilmImageDto image) {
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }

    @Override
    public boolean update(Film film, FilmImageDto image) {
        return false;
    }

    @Override
    public Optional<Film> findById(int id) {
        return sqloFilmRepository.findById(id);
    }

    @Override
    public Collection<Film> findAll() {
        return sqloFilmRepository.findAll();
    }
}
