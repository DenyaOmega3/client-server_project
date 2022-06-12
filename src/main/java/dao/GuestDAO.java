package dao;

import entity.Guest;
import runner.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GuestDAO implements DAO<Guest> {
    @Override
    public void add(Guest guest) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();

        String sql = "INSERT INTO guests (first_name, last_name, birth_date, email, password) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, guest.getFirstName());
            preparedStatement.setString(2, guest.getLastName());
            preparedStatement.setDate(3, Date.valueOf(guest.getDateOfBirth()));
            preparedStatement.setString(4, guest.getEmail());
            preparedStatement.setString(5, guest.getPassword());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }

    @Override
    public List<Guest> getAll() throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        List<Guest> guestList = new ArrayList<>();
        String sql = "SELECT guest_id, first_name, last_name, birth_date, email, password FROM guests";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Guest guest = new Guest();
                guest.setId(resultSet.getInt("guest_id"));
                guest.setFirstName(resultSet.getString("first_name"));
                guest.setLastName(resultSet.getString("last_name"));
                guest.setDateOfBirth(resultSet.getDate("birth_date").toLocalDate());
                guest.setEmail(resultSet.getString("email"));
                guest.setPassword(resultSet.getString("password"));

                guestList.add(guest);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return guestList;
    }

    @Override
    public Guest getByID(int id) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();

        String sql = "SELECT guest_id, first_name, last_name, birth_date, email, password FROM guests WHERE guest_id = ?";
        Guest guest = new Guest();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                guest.setId(resultSet.getInt("guest_id"));
                guest.setFirstName(resultSet.getString("first_name"));
                guest.setLastName(resultSet.getString("last_name"));
                guest.setDateOfBirth(resultSet.getDate("birth_date").toLocalDate());
                guest.setEmail(resultSet.getString("email"));
                guest.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return guest;
    }

    @Override
    public void update(Guest guest) throws SQLException {

    }

    @Override
    public void remove(Guest guest) throws SQLException {

    }

}

    /*
    public Guest getByUsername(String username) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();

        String sql = "SELECT id, username, email, password FROM user WHERE username = ?";
        Guest user = new Guest();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,username);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            connection.close();
        }
        return user;
    }

    @Override
    public void update(Guest user) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();

        String sql = "UPDATE user SET username = ?, email = ?, password = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,user.getUsername());
            preparedStatement.setString(2,user.getEmail());
            preparedStatement.setString(3,user.getPassword());
            preparedStatement.setInt(4,user.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            connection.close();
        }
    }

    @Override
    public void remove(Guest user) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();

        String sql = "DELETE FROM user WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            connection.close();
        }
    }
}

*/