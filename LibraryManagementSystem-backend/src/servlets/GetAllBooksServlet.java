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

public class GetAllBooksServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        res.setContentType("application/json");
        JSONObject responseObject = new JSONObject();
        JSONArray booksArray = new JSONArray();
        PrintWriter writer = res.getWriter();

        res.setHeader("Access-Control-Allow-Origin" ,"http://localhost:5173");
        res.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS");
        res.setHeader("Access-Control-Allow-Headers","content-type");
        res.setHeader("Access-Control-Allow-Credentials","true");

        try {
            BookDAO bookDAO = new BookDAO();
            List<Book> books = bookDAO.getAllBooks();

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

        } catch (Exception e) {
            responseObject.put("status",false);
            responseObject.put("message", "Server error");
            e.printStackTrace();
        }

        writer.print(responseObject.toString());
        writer.flush();
    }

    protected void doOptions(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setHeader("Access-Control-Allow-Origin" ,"http://localhost:5173");
        res.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS");
        res.setHeader("Access-Control-Allow-Headers","content-type");
        res.setHeader("Access-Control-Allow-Credentials","true");
    }
}
