insert into halls(id, name, row_count, place_count, description) values(1, 'Hall 1', 10, 16, 'Малый зал');
insert into halls(id, name, row_count, place_count, description) values(2, 'Hall 2', 16, 22, 'Средний зал');

insert into files(id, name, path) values(1, 'Matrix', '/images/1.jpg');
insert into files(id, name, path) values(2, 'Lord of the rings', '/images/2.jpg');
insert into files(id, name, path) values(3, 'The Ring', '/images/3.jpg');
insert into files(id, name, path) values(4, 'Tootsie', '/images/4.jpg');

insert into genres(id, name) values(1, 'Action');
insert into genres(id, name) values(2, 'Fantasy');
insert into genres(id, name) values(3, 'Horror');
insert into genres(id, name) values(4, 'Comedy');

insert into films(id, name, description, year, genre_id, minimal_age, duration_in_minutes, file_id) values(1, 'Matrix', 'Нео против системы', 1998, 1, 18, 100, 1);
insert into films(id, name, description, year, genre_id, minimal_age, duration_in_minutes, file_id) values(2, 'Lord of the rings', 'Средиземье против Мордора', 2002, 2, 14, 150, 2);
insert into films(id, name, description, year, genre_id, minimal_age, duration_in_minutes, file_id) values(3, 'The Ring', 'Смертельная кассета', 2002, 3, 18, 150, 3);
insert into films(id, name, description, year, genre_id, minimal_age, duration_in_minutes, file_id) values(4, 'Tootsie', 'Средиземье против Мордора', 1982, 4, 6, 90, 4);

insert into film_sessions(id, film_id, halls_id, start_time, end_time, price) values(1, 1, 1, '2025-10-10 16:00:00', '2025-10-10 18:00:00', 10);
insert into film_sessions(id, film_id, halls_id, start_time, end_time, price) values(2, 2, 2, '2025-10-10 16:00:00', '2025-10-10 18:00:00', 10);
insert into film_sessions(id, film_id, halls_id, start_time, end_time, price) values(3, 3, 1, '2025-10-10 18:00:00', '2025-10-10 20:00:00', 15);
insert into film_sessions(id, film_id, halls_id, start_time, end_time, price) values(4, 4, 2, '2025-10-10 18:00:00', '2025-10-10 20:00:00', 15);
insert into film_sessions(id, film_id, halls_id, start_time, end_time, price) values(5, 2, 1, '2025-10-10 20:00:00', '2025-10-10 22:00:00', 20);
insert into film_sessions(id, film_id, halls_id, start_time, end_time, price) values(6, 4, 2, '2025-10-10 20:00:00', '2025-10-10 22:00:00', 20);