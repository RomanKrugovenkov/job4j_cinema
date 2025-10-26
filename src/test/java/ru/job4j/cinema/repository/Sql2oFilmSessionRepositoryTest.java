package ru.job4j.cinema.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.sql2o.Sql2o;
import ru.job4j.cinema.configuration.DatasourceConfiguration;
import ru.job4j.cinema.model.*;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Properties;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Sql2oFilmSessionRepositoryTest {
    private static Sql2o sql2o;
    private static Sql2oFilmSessionRepository sql2oFilmSessionRepository;
    private static Sql2oFilmRepository sql2oFilmRepository;
    private static Sql2oHallRepository sql2oHallRepository;
    private static Sql2oGenreRepository sql2oGenreRepository;
    private static Sql2oFileRepository sql2oFileRepository;

    @BeforeAll
    public static void initRepositories() throws Exception {
        var properties = new Properties();
        try (var inputStream = Sql2oFilmSessionRepository.class.getClassLoader().getResourceAsStream("connection.properties")) {
            properties.load(inputStream);
        }
        var url = properties.getProperty("datasource.url");
        var username = properties.getProperty("datasource.username");
        var password = properties.getProperty("datasource.password");

        var configuration = new DatasourceConfiguration();
        var datasource = configuration.connectionPool(url, username, password);

        sql2o = configuration.databaseClient(datasource);
        sql2oFilmSessionRepository = new Sql2oFilmSessionRepository(sql2o);
        sql2oFilmRepository = new Sql2oFilmRepository(sql2o);
        sql2oHallRepository = new Sql2oHallRepository(sql2o);
        sql2oGenreRepository = new Sql2oGenreRepository(sql2o);
        sql2oFileRepository = new Sql2oFileRepository(sql2o);
    }

    @AfterEach
    public void clearVacancies() {
        sql2o.open().createQuery("DELETE FROM film_sessions").executeUpdate();
        sql2o.open().createQuery("DELETE FROM films").executeUpdate();
        sql2o.open().createQuery("DELETE FROM halls").executeUpdate();
        sql2o.open().createQuery("DELETE FROM genres").executeUpdate();
        sql2o.open().createQuery("DELETE FROM files").executeUpdate();
    }

    @Test
    public void whenSaveThenGetSame() {
        var genre = sql2oGenreRepository.save(new Genre("Action"));
        var file = sql2oFileRepository.save(new File("Matrix", "/images/1.jpg"));
        var film = sql2oFilmRepository.save(new Film("Matrix", "Нео против системы", 1998, genre.getId(), 18, 100, file.getId()));
        var hall = sql2oHallRepository.save(new Hall("Hall 1", 10, 16, "Малый зал"));
        LocalDateTime startTime = LocalDateTime.of(2025, Month.OCTOBER, 10, 18, 0, 0);
        LocalDateTime endTime = LocalDateTime.of(2025, Month.OCTOBER, 10, 20, 0, 0);
        var session = new FilmSession(film.getId(), hall.getId(), startTime, endTime, 10);
        var filmSession = sql2oFilmSessionRepository.save(session);
        var savedFilmSession = sql2oFilmSessionRepository.findById(filmSession.getId()).get();
        assertThat(savedFilmSession).usingRecursiveComparison().isEqualTo(session);
    }
}
