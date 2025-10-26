package ru.job4j.cinema.service;

import ru.job4j.cinema.model.Hall;

import java.util.Collection;
import java.util.Optional;

public interface HallService {

    Hall save(Hall hall);

    boolean deleteById(int id);

    boolean update(Hall hall);

    Optional<Hall> findById(int id);

    Collection<Hall> findAll();
}
