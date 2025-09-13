package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.BookDAO;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;

public class DeleteBookServlet extends HttpServlet {
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        res.setContentType("application/json");
        PrintWriter writer = res.getWriter();
        JSONObject responseObject = new JSONObject();

        res.setHeader("Access-Control-Allow-Origin" ,"http://localhost:5173");
        res.setHeader("Access-Control-Allow-Methods", "GET,POST,DELETE,OPTIONS");
        res.setHeader("Access-Control-Allow-Headers","content-type");
        res.setHeader("Access-Control-Allow-Credentials","true");

        try {
            String stringId = req.getParameter("id");
            int id = Integer.parseInt(stringId);
            BookDAO bookDAO = new BookDAO();

            boolean success = bookDAO.DeleteBook(id);

            if(success) {
                responseObject.put("status",true);
                responseObject.put("message","book successfully deleted");
            } else {
                responseObject.put("status",false);
                responseObject.put("message","failed to delete the book");
            }
        } catch ( Exception e) {
            responseObject.put("status",false);
            responseObject.put("message","server error");
            e.printStackTrace();
        }

        writer.print(responseObject.toString());
        writer.flush();
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        res.setHeader("Access-Control-Allow-Origin" ,"http://localhost:5173");
        res.setHeader("Access-Control-Allow-Methods", "GET,POST,DELETE,OPTIONS");
        res.setHeader("Access-Control-Allow-Headers","content-type");
        res.setHeader("Access-Control-Allow-Credentials","true");
    }
}
