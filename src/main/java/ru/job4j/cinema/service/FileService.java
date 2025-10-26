package ru.job4j.cinema.service;

import ru.job4j.cinema.dto.FilmImageDto;
import ru.job4j.cinema.model.File;

import java.util.Optional;

public interface FileService {

    File save(FilmImageDto filmImageDto);

    Optional<FilmImageDto> getFileById(int id);

    void deleteById(int id);
}
