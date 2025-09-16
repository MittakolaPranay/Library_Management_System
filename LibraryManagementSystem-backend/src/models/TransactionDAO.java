package models;

import utils.DBConnection;

import java.sql.*;
import java.time.LocalDate;

public class TransactionDAO {

    public boolean borrowBook(int userId, int bookId) {
        System.out.println("user id and book id at borrow book function :" + userId + " and " + bookId);

        String checkAlreadyIssuedQuery =
                "SELECT COUNT(*) FROM transactions WHERE user_id = ? AND book_id = ? AND status = 'issued'";

        try (Connection connection = DBConnection.getConnector()) {

           
            try (PreparedStatement psCheck = connection.prepareStatement(checkAlreadyIssuedQuery)) {
                psCheck.setInt(1, userId);
                psCheck.setInt(2, bookId);
                try (ResultSet rs = psCheck.executeQuery()) {
                    if (rs.next() && rs.getInt(1) > 0) {
                        // Already borrowed, block it
                        System.out.println("User already has this book issued.");
                        return false;
                    }
                }
            }


            String checkBookAvailableQuery = "SELECT available FROM books WHERE id = ?";
            try (PreparedStatement psBook = connection.prepareStatement(checkBookAvailableQuery)) {
                psBook.setInt(1, bookId);
                try (ResultSet resultSet = psBook.executeQuery()) {
                    if (resultSet.next() && resultSet.getInt("available") > 0) {


                        String insertIntoTransactionsQuery =
                                "INSERT INTO transactions (user_id, book_id, issue_date, due_date, return_date, status) VALUES (?,?,?,?,?,?)";
                        try (PreparedStatement psInsert = connection.prepareStatement(insertIntoTransactionsQuery)) {
                            Date issueDate = Date.valueOf(LocalDate.now());
                            Date dueDate = Date.valueOf(LocalDate.now().plusDays(14));

                            psInsert.setInt(1, userId);
                            psInsert.setInt(2, bookId);
                            psInsert.setDate(3, issueDate);
                            psInsert.setDate(4, dueDate);
                            psInsert.setNull(5, Types.DATE); // return_date is null initially
                            psInsert.setString(6, "issued"); // explicitly set status

                            int row = psInsert.executeUpdate();
                            if (row > 0) {

                                String decrementAvailableQuery = "UPDATE books SET available = ? WHERE id = ?";
                                try (PreparedStatement psUpdate = connection.prepareStatement(decrementAvailableQuery)) {
                                    psUpdate.setInt(1, resultSet.getInt("available") - 1);
                                    psUpdate.setInt(2, bookId);
                                    psUpdate.executeUpdate();
                                }
                                return true;
                            }
                        }
                    }
                }
            }

        } catch (SQLException exception) {
            System.err.print("SQL Error: " + exception.getMessage());
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
