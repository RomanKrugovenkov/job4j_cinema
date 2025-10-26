package ru.job4j.cinema.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.sql2o.Sql2o;
import ru.job4j.cinema.configuration.DatasourceConfiguration;
import ru.job4j.cinema.model.Hall;

import java.util.Properties;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class Sql2oHallRepositoryTest {
    private static Sql2o sql2o;
    private static Sql2oHallRepository sql2oHallRepository;

    @BeforeAll
    public static void initRepositories() throws Exception {
        var properties = new Properties();
        try (var inputStream = Sql2oHallRepositoryTest.class.getClassLoader().getResourceAsStream("connection.properties")) {
            properties.load(inputStream);
        }
        var url = properties.getProperty("datasource.url");
        var username = properties.getProperty("datasource.username");
        var password = properties.getProperty("datasource.password");

        var configuration = new DatasourceConfiguration();
        var datasource = configuration.connectionPool(url, username, password);

        sql2o = configuration.databaseClient(datasource);
        sql2oHallRepository = new Sql2oHallRepository(sql2o);
    }

    @AfterEach
    public void clearVacancies() {
        sql2o.open().createQuery("DELETE FROM halls").executeUpdate();
    }

    @Test
    public void whenSaveThenGetSame() {
        var hall = sql2oHallRepository.save(new Hall("Hall 1", 10, 16, "Малый зал"));
        var savedHall = sql2oHallRepository.findById(hall.getId()).get();
        assertThat(savedHall).usingRecursiveComparison().isEqualTo(hall);
    }
}