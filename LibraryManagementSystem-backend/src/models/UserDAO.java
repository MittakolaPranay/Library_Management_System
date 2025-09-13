package models;

import utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public boolean registerUser(User user) {

        String insertQuery = "insert into users (name,email,password,role) values(?,?,?,?)";

        try (
                Connection connection = DBConnection.getConnector();
                PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)
                ) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4,user.getRole());

            int row = preparedStatement.executeUpdate();

            return row > 0;
        } catch (SQLException e) {
            System.err.print("Error while registering the user :"+e.getMessage());
        }
        return false;
    }



    public User loginUser(String email,String password) {
        String checkQuery = "select * from users where email = ? and password = ?";

        try (
                Connection connection = DBConnection.getConnector();
                PreparedStatement preparedStatement = connection.prepareStatement(checkQuery);

                ) {
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,password);

            try (ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()) {
                    return new User(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("email"),
                            resultSet.getString("password"),
                            resultSet.getString("role")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.print("Error while login by user :"+ e.getMessage());
        }
        return null;
    }
}
