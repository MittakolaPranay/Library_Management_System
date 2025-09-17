package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Book;
import models.BookDAO;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class GetBorrowedBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        res.setContentType("application/json");
        PrintWriter writer = res.getWriter();
        JSONObject responseObject = new JSONObject();
        JSONArray booksArray = new JSONArray();

        res.setHeader("Access-Control-Allow-Origin" ,"http://localhost:5173");
        res.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS");
        res.setHeader("Access-Control-Allow-Headers","content-type");
        res.setHeader("Access-Control-Allow-Credentials","true");

        try {
            String stringUserId = req.getParameter("userId");
            int userId = Integer.parseInt(stringUserId);
            BookDAO bookDAO = new BookDAO();

            List<Book> books = bookDAO.getBorrowedBooks(userId);

            for(Book book : books) {
                JSONObject jsonBook = new JSONObject();
                jsonBook.put("id",book.getId());
                jsonBook.put("title",book.getTitle());
                jsonBook.put("author",book.getAuthor());
                jsonBook.put("isbn",book.getIsbn());
                jsonBook.put("copies",book.getCopies());
                jsonBook.put("available",book.getAvailable());
                jsonBook.put("image_url",book.getImageURL());

                booksArray.put(jsonBook);
            }

            responseObject.put("status",true);
            responseObject.put("books",booksArray);

        } catch (Exception exception) {
            responseObject.put("status",false);
            responseObject.put("message", "Server error");
            System.err.print("Error : "+exception.getMessage());
        }
        writer.print(responseObject.toString());
        writer.flush();
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setHeader("Access-Control-Allow-Origin" ,"http://localhost:5173");
        res.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS");
        res.setHeader("Access-Control-Allow-Headers","content-type");
        res.setHeader("Access-Control-Allow-Credentials","true");
    }
}
