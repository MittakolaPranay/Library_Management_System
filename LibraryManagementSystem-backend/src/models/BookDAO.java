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

        String addBookQuery = "insert into books (title,author,isbn,copies,available,image_url) value (?,?,?,?,?,?)";
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

        String searchBooksQuery = "select * from books where title like ? or author like ? or isbn like ?";
        List<Book> books = new ArrayList<>();
        String searchPattern = "%" + q + "%";
        try (
                Connection connection = DBConnection.getConnector();
                PreparedStatement preparedStatement = connection.prepareStatement(searchBooksQuery);
                ) {
            preparedStatement.setString(1,searchPattern);
            preparedStatement.setString(2,searchBooksQuery);
            preparedStatement.setString(3,searchBooksQuery);

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

    public boolean DeleteBook(int id) {

        String deleteBookQuery = "delete from books where id = ?";
        try (
                Connection connection = DBConnection.getConnector();
                PreparedStatement preparedStatement = connection.prepareStatement(deleteBookQuery);
                ) {
            preparedStatement.setInt(1,id);

            int row = preparedStatement.executeUpdate();

            if(row >= 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

}
