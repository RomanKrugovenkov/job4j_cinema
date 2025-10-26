package ru.job4j.cinema.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ConcurrentModel;
import ru.job4j.cinema.service.FilmService;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;

class FilmsControllerTest {
    private FilmService filmService;
    private FilmsController filmsController;

    @BeforeEach
    public void initServices() {
        filmService = mock(FilmService.class);
        filmsController = new FilmsController(filmService);
    }

    @Test
    public void whenRequestSessionsListPageThenGetPageWithSessions() {
        var model = new ConcurrentModel();
        var view = filmsController.getAll(model);

        assertThat(view).isEqualTo("views/films");
    }
}