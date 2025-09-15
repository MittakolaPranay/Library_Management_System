package models;

import utils.DBConnection;

import java.sql.*;
import java.time.LocalDate;

public class TransactionDAO {

    public boolean borrowBook(int userId,int bookId) {
        String checkBookAvailableQuery = "select available from books where id = ?";
        try (
                Connection connection = DBConnection.getConnector();
                PreparedStatement preparedStatement = connection.prepareStatement(checkBookAvailableQuery);
                ) {
            preparedStatement.setInt(1,bookId);
            try (
                    ResultSet resultSet = preparedStatement.executeQuery();
                    ) {
                if(resultSet.next() && resultSet.getInt("available") > 0) {
                    String insertIntoTransactionsQuery = "insert into transactions (user_id,book_id,issue_date,due_date,return_date) values (?,?,?,?,?)";
                    try (
                            PreparedStatement preparedStatement1 = connection.prepareStatement(insertIntoTransactionsQuery);
                            ) {
                        Date issueDate = Date.valueOf(LocalDate.now());
                        Date dueDate = Date.valueOf(LocalDate.now().plusDays(14));
                        preparedStatement1.setInt(1,userId);
                        preparedStatement1.setInt(2,bookId);
                        preparedStatement1.setDate(3,issueDate);
                        preparedStatement1.setDate(4,dueDate);
                        preparedStatement1.setNull(5, Types.DATE);
                        int row = preparedStatement1.executeUpdate();
                        if(row > 0) {
                            String decrementAvailableQuery = "update books set available = ? where id = ?";
                            try (
                                    PreparedStatement preparedStatement2 = connection.prepareStatement(decrementAvailableQuery)
                                    ) {
                                preparedStatement2.setInt(1,resultSet.getInt("available") - 1);
                                preparedStatement2.setInt(2,bookId);
                                preparedStatement2.executeUpdate();
                            }
                            return true;
                        }
                    }
                }
            }
        } catch (SQLException exception) {
            System.err.print("SQL Error : "+exception.getMessage());
        }
        return false;
    }

    public boolean returnBook(int userId,int bookId) {

        String returnBookQuery = "update transactions set return_date = ?, status = ? where user_id = ? and book_id = ? and stats = 'issued'";
        try (
                Connection connection = DBConnection.getConnector();
                PreparedStatement preparedStatement = connection.prepareStatement(returnBookQuery);
                ) {
            Date returnDate = Date.valueOf(LocalDate.now());
            preparedStatement.setDate(1,returnDate);
            preparedStatement.setString(2,"returned");
            preparedStatement.setInt(3,userId);
            preparedStatement.setInt(4,bookId);
            int row = preparedStatement.executeUpdate();
            if(row > 0) {
                String checkBookAvailableQuery = "select available from books where id = ?";
                try (
                        PreparedStatement preparedStatement1 = connection.prepareStatement(checkBookAvailableQuery);
                        ) {
                    preparedStatement1.setInt(1,bookId);
                    try (
                            ResultSet resultSet = preparedStatement1.executeQuery();
                            ) {
                        if(resultSet.next()) {
                            String incrementAvailable = "update books set available = ? where id = ?";
                            try (
                                    PreparedStatement preparedStatement2 = connection.prepareStatement(incrementAvailable);
                                    ) {
                                preparedStatement2.setInt(1,resultSet.getInt("available") + 1);
                                preparedStatement2.setInt(2,bookId);
                                int row1 = preparedStatement2.executeUpdate();
                                if(row1 > 0) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.err.print("Database error :"+e.getMessage());
        }

        return false;
    }
}
