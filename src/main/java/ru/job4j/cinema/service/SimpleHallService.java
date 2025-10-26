package ru.job4j.cinema.service;

import org.springframework.stereotype.Service;
import ru.job4j.cinema.model.Hall;
import ru.job4j.cinema.repository.Sql2oHallRepository;

import java.util.Collection;
import java.util.Optional;

@Service
public class SimpleHallService implements HallService {
    private final Sql2oHallRepository sql2oHallRepository;

    public SimpleHallService(Sql2oHallRepository sql2oHallRepository) {
        this.sql2oHallRepository = sql2oHallRepository;
    }

    @Override
    public Hall save(Hall hall) {
        return null;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }

    @Override
    public boolean update(Hall hall) {
        return false;
    }

    @Override
    public Optional<Hall> findById(int id) {
        return sql2oHallRepository.findById(id);
    }

    @Override
    public Collection<Hall> findAll() {
        return sql2oHallRepository.findAll();
    }
}
