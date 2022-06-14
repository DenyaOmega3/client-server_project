package dao;

import entity.Film;
import runner.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilmDAO implements DAO<Film> {
    @Override
    public void add(Film film) throws SQLException {
        String sql = "INSERT INTO films (title, description, runtime) VALUES (?,?,?)";
        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setString(1, film.getTitle());
            preparedStatement.setString(2, film.getDescription());
            preparedStatement.setInt(3, film.getRuntime());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Film> getAll() throws SQLException {
        List<Film> filmList = new ArrayList<>();
        String sql = "SELECT film_id, title, description, runtime FROM films";

        try (            Connection connection = DBUtil.getDataSource().getConnection();
                         PreparedStatement statement = connection.prepareStatement(sql);) {
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
        }
        return filmList;
    }

    @Override
    public Film getByID(int id) throws SQLException {
        String sql = "SELECT * FROM films WHERE film_id = ?";
        Film film = new Film();

        try(Connection connection = DBUtil.getDataSource().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ) {
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
        }
        return film;
    }

    @Override
    public void update(Film film) throws SQLException {
        String sql = "UPDATE films SET title = ?, description = ?, runtime = ? WHERE film_id = ?";

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql); ){
            preparedStatement.setString(1, film.getTitle());
            preparedStatement.setString(2, film.getDescription());
            preparedStatement.setInt(3, film.getRuntime());
            preparedStatement.setInt(4, film.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(int id) throws SQLException {
        String sql = "DELETE FROM films WHERE film_id = ?";

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
