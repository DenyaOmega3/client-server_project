package dao;

import entity.Film;
import entity.Guest;
import runner.DatabaseConnection;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilmDAO implements DAO<Film> {
    @Override
    public void add(Film film) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();

        String sql = "INSERT INTO films (title, description, runtime) VALUES (?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, film.getTitle());
            preparedStatement.setString(2, film.getDescription());
            preparedStatement.setInt(3, film.getRuntime());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }

    @Override
    public List<Film> getAll() throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        List<Film> filmList = new ArrayList<>();
        String sql = "SELECT film_id, title, description, runtime FROM films";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Film film = new Film();
                film.setId(resultSet.getInt("film_id"));
                film.setTitle(resultSet.getString("title"));
                film.setDescription(resultSet.getString("description"));
                film.setRuntime(resultSet.getInt("runtime"));

                filmList.add(film);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return filmList;
    }

    @Override
    public Film getByID(int id) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();

        String sql = "SELECT film_id, title, description, runtime FROM films WHERE film_id = ?";
        Film film = new Film();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                film.setId(resultSet.getInt("film_id"));
                film.setTitle(resultSet.getString("title"));
                film.setDescription(resultSet.getString("description"));
                film.setRuntime(resultSet.getInt("runtime"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return film;
    }

    @Override
    public void update(Film film) throws SQLException {

    }

    @Override
    public void remove(Film film) throws SQLException {
    }

    public void removeById(int id) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String sql = "DELETE FROM films WHERE film_id = ?";

        try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }
}
