package ru.job4j.cinema.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class SessionPreview {
    private int id;
    private String film;
    private String halls;
    private LocalDateTime startTime;
    private int price;

    public SessionPreview() {
    }

    public SessionPreview(int id, String film, String halls, LocalDateTime startTime, LocalDateTime endTime, int price) {
        this.id = id;
        this.film = film;
        this.halls = halls;
        this.startTime = startTime;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilm() {
        return film;
    }

    public void setFilm(String film) {
        this.film = film;
    }

    public String getHalls() {
        return halls;
    }

    public void setHalls(String halls) {
        this.halls = halls;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SessionPreview that = (SessionPreview) o;
        return id == that.id && price == that.price && Objects.equals(film, that.film) && Objects.equals(halls, that.halls) && Objects.equals(startTime, that.startTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, film, halls, startTime, price);
    }
}
