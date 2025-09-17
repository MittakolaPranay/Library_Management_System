package models;

import utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    public boolean addBook(Book book) {

        String addBookQuery = "insert into books (title,author,isbn,copies,available,image_url) values (?,?,?,?,?,?)";
        try (
                Connection connection = DBConnection.getConnector();
                PreparedStatement preparedStatement = connection.prepareStatement(addBookQuery);
        ) {
            preparedStatement.setString(1,book.getTitle());
            preparedStatement.setString(2,book.getAuthor());
            preparedStatement.setString(3, book.getIsbn());
            preparedStatement.setInt(4,book.getCopies());
            preparedStatement.setInt(5,book.getAvailable());
            preparedStatement.setString(6,book.getImageURL());

            int row = preparedStatement.executeUpdate();

            if(row >= 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<Book> getAllBooks() {

        List<Book> books = new ArrayList<>();
        String getAllBooksQuery = "select * from books";
        try (
                Connection connection = DBConnection.getConnector();
                PreparedStatement preparedStatement = connection.prepareStatement(getAllBooksQuery);
                ) {
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()) {
                books.add(new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("isbn"),
                        rs.getInt("copies"),
                        rs.getInt("available"),
                        rs.getString("image_url")
                ));
            }

            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }


    public List<Book> searchBooks(String q) {

        System.out.println("---------- q is" + q);
        String searchBooksQuery = "select * from books where title like ? or author like ? or isbn like ?";
        List<Book> books = new ArrayList<>();
        String searchPattern = "%" + q + "%";
        System.out.println("----- final pattern :"+searchPattern);
        try (
                Connection connection = DBConnection.getConnector();
                PreparedStatement preparedStatement = connection.prepareStatement(searchBooksQuery);
                ) {
            preparedStatement.setString(1,searchPattern);
            preparedStatement.setString(2,searchPattern);
            preparedStatement.setString(3,searchPattern);

            ResultSet rs = preparedStatement.executeQuery();


            while(rs.next()) {
                books.add(new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("isbn"),
                        rs.getInt("copies"),
                        rs.getInt("available"),
                        rs.getString("image_url")
                ));
            }

            if(!books.isEmpty()) {
                for(Book book: books) {
                    System.out.println("----------"+book.getTitle());
                }
            } else {
                System.out.println("--------------------book is empty-----------------------");
            }

            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public boolean updateBook(Book book) {

        String updateBookQuery = "update books set title = ? ,author = ? ,isbn = ? ,copies = ? ,available = ? ,image_url = ? where id = ?";
        try (
                Connection connection = DBConnection.getConnector();
                PreparedStatement preparedStatement = connection.prepareStatement(updateBookQuery);
                ) {
            preparedStatement.setString(1,book.getTitle());
            preparedStatement.setString(2,book.getAuthor());
            preparedStatement.setString(3,book.getIsbn());
            preparedStatement.setInt(4,book.getCopies());
            preparedStatement.setInt(5,book.getAvailable());
            preparedStatement.setString(6,book.getImageURL());
            preparedStatement.setInt(7,book.getId());

            int row  = preparedStatement.executeUpdate();

            if(row >= 1) {
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return false;
    }


    public List<Book> getBorrowedBooks(int userId) {
        List<Book> books = new ArrayList<>();
        String query = "SELECT b.* FROM books b " +
                "INNER JOIN transactions t ON b.id = t.book_id " +
                "WHERE t.user_id = ? AND t.status = 'issued'";

        try (
                Connection connection = DBConnection.getConnector();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setInt(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    books.add(new Book(
                            resultSet.getInt("id"),
                            resultSet.getString("title"),
                            resultSet.getString("author"),
                            resultSet.getString("isbn"),
                            resultSet.getInt("copies"),
                            resultSet.getInt("available"),
                            resultSet.getString("image_url")
                    ));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return books;
    }
    public boolean DeleteBook(int id) {
        String checkIssueQuery = "select count(*) as cnt from transactions where book_id = ? and status = 'issued'";
        String deleteBookQuery = "delete from books where id = ?";

        try (
                Connection connection = DBConnection.getConnector();
                PreparedStatement preparedStatement = connection.prepareStatement(checkIssueQuery);
                ) {
            preparedStatement.setInt(1,id);
            try (
                    ResultSet resultSet = preparedStatement.executeQuery();
                    ) {
                if(resultSet.next() && resultSet.getInt("cnt") > 0){
                    return false;
                }
            }
            try (
                    PreparedStatement preparedStatement1 = connection.prepareStatement(deleteBookQuery);
                    ) {
                preparedStatement1.setInt(1,id);
                int affected = preparedStatement1.executeUpdate();

                return affected > 0;
            }
        } catch (SQLException exception) {
            System.err.print("SQL error :"+exception.getMessage());
        }
        return false;
    }
}

