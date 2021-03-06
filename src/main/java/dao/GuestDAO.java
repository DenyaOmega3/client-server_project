package dao;

import entity.Guest;
import runner.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GuestDAO implements DAO<Guest> {
    @Override
    public void add(Guest guest) throws SQLException {
        String sql = "INSERT INTO guests (first_name, last_name, birth_date, email, password) VALUES (?,?,?,?,?)";
        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setString(1, guest.getFirstName());
            preparedStatement.setString(2, guest.getLastName());
            preparedStatement.setDate(3, Date.valueOf(guest.getDateOfBirth()));
            preparedStatement.setString(4, guest.getEmail());
            preparedStatement.setString(5, guest.getPassword());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Guest> getAll() throws SQLException {
        List<Guest> guestList = new ArrayList<>();
        String sql = "SELECT * FROM guests";

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
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
        }
        return guestList;
    }

    @Override
    public Guest getByID(int id) throws SQLException {
        String sql = "SELECT * FROM guests WHERE guest_id = ?";
        Guest guest = new Guest();

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
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
        }
        return guest;
    }

    @Override
    public void update(Guest guest) throws SQLException {
        String sql = "UPDATE guests SET first_name = ?, last_name = ?, birth_date = ?, email = ?, password = ? WHERE guest_id = ?";

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql); ){
            preparedStatement.setString(1, guest.getFirstName());
            preparedStatement.setString(2, guest.getLastName());
            preparedStatement.setDate(3, Date.valueOf(guest.getDateOfBirth()));
            preparedStatement.setString(4, guest.getEmail());
            preparedStatement.setString(5, guest.getPassword());
            preparedStatement.setInt(6, guest.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(int id) throws SQLException {
        String sql = "DELETE FROM guests WHERE guest_id = ?";

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql); ){
            preparedStatement.setInt(1, id);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Guest getByEMail(String email) throws SQLException {
        String sql = "SELECT * FROM guests WHERE email = ? ";
        //guest_id, first_name, last_name, birth_date, email, password?
        Guest guest = null;

        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                guest = new Guest();
                guest.setId(resultSet.getInt("guest_id"));
                guest.setFirstName(resultSet.getString("first_name"));
                guest.setLastName(resultSet.getString("last_name"));
                guest.setDateOfBirth(resultSet.getDate("birth_date").toLocalDate());
                guest.setEmail(resultSet.getString("email"));
                guest.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return guest;
    }
}