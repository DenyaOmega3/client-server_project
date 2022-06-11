package model;

import dao.FilmDAO;
import dao.GuestDAO;
import entity.Film;
import entity.Guest;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class FilmModel {
    FilmDAO filmDAO = new FilmDAO();

    public void setFilm(String title, String description, int runtime) {
        Film film = new Film(title,description, runtime);
        try {
            filmDAO.add(film);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Film> getListOfFilms() {
        try {
            return filmDAO.getAll();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
