package ru.job4j.cinema.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.sql2o.Sql2o;
import ru.job4j.cinema.configuration.DatasourceConfiguration;
import ru.job4j.cinema.model.File;
import ru.job4j.cinema.model.Film;
import ru.job4j.cinema.model.Genre;

import java.util.Properties;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Sql2oFilmRepositoryTest {
    private static Sql2o sql2o;
    private static Sql2oFilmRepository sql2oFilmRepository;
    private static Sql2oFileRepository sql2oFileRepository;
    private static Sql2oGenreRepository sql2oGenreRepository;

    @BeforeAll
    public static void initRepositories() throws Exception {
        var properties = new Properties();
        try (var inputStream = Sql2oFilmRepositoryTest.class.getClassLoader().getResourceAsStream("connection.properties")) {
            properties.load(inputStream);
        }
        var url = properties.getProperty("datasource.url");
        var username = properties.getProperty("datasource.username");
        var password = properties.getProperty("datasource.password");

        var configuration = new DatasourceConfiguration();
        var datasource = configuration.connectionPool(url, username, password);

        sql2o = configuration.databaseClient(datasource);
        sql2oFilmRepository = new Sql2oFilmRepository(sql2o);
        sql2oFileRepository = new Sql2oFileRepository(sql2o);
        sql2oGenreRepository = new Sql2oGenreRepository(sql2o);
    }

    @AfterEach
    public void clearVacancies() {
        sql2o.open().createQuery("DELETE FROM films").executeUpdate();
        sql2o.open().createQuery("DELETE FROM files").executeUpdate();
        sql2o.open().createQuery("DELETE FROM genres").executeUpdate();
    }

    @Test
    public void whenSaveThenGetSame() {
        var genre = sql2oGenreRepository.save(new Genre("Action"));
        var file = sql2oFileRepository.save(new File("Matrix", "/images/1.jpg"));
        var film = sql2oFilmRepository.save(new Film("Matrix", "Нео против системы", 1998, genre.getId(), 18, 100, file.getId()));
        var savedFilm = sql2oFilmRepository.findById(film.getId()).get();
        assertThat(savedFilm).usingRecursiveComparison().isEqualTo(film);
    }
}