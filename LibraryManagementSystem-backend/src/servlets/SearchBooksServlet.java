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

public class SearchBooksServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        res.setContentType("application/json");
        PrintWriter writer = res.getWriter();
        JSONArray booksArray = new JSONArray();
        JSONObject responseObject = new JSONObject();


        res.setHeader("Access-Control-Allow-Origin" ,"http://localhost:5173");
        res.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS");
        res.setHeader("Access-Control-Allow-Headers","content-type");
        res.setHeader("Access-Control-Allow-Credentials","true");

        try {

            String q = req.getParameter("q");
            BookDAO bookDAO = new BookDAO();
            List<Book> books = bookDAO.searchBooks(q);

            if(books.isEmpty()) {
                responseObject.put("status",false);
                responseObject.put("message","Couldn't found book..");
            }else {
                for(Book book : books) {
                    JSONObject bookObject = new JSONObject();
                    bookObject.put("id",book.getId());
                    bookObject.put("title",book.getTitle());
                    bookObject.put("author",book.getAuthor());
                    bookObject.put("isbn",book.getIsbn());
                    bookObject.put("copies",book.getCopies());
                    bookObject.put("available",book.getAvailable());
                    bookObject.put("image_url",book.getImageURL());

                    booksArray.put(bookObject);
                }

                responseObject.put("status",true);
                responseObject.put("books",booksArray);
            }

        } catch (Exception e) {
            e.printStackTrace();
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
