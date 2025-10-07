package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.TransactionDAO;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;

public class ReturnBookServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        res.setContentType("application/json");
        PrintWriter writer = res.getWriter();
        JSONObject responseObject = new JSONObject();

        res.setHeader("Access-Control-Allow-Origin","http://localhost:5173");
        res.setHeader("Access-Control-Allow-Methods","GET, POST, OPTIONS");
        res.setHeader("Access-Control-Allow-Headers","content-type");
        res.setHeader("Access-Control-Allow-Credentials","true");

        try {
            String stringUserId = req.getParameter("userId");
            int userId = Integer.parseInt(stringUserId);
            String stringBookId = req.getParameter("bookId");
            int bookId = Integer.parseInt(stringBookId);

            TransactionDAO transactionDAO = new TransactionDAO();
            boolean success = transactionDAO.returnBook(userId,bookId);

            if(success) {
                responseObject.put("status",true);
            }else {
                responseObject.put("status",false);
            }
        } catch (Exception exception) {
            responseObject.put("status",false);
        }

        writer.print(responseObject.toString());
        writer.flush();
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setHeader("Access-Control-Allow-Origin","http://localhost:5173");
        res.setHeader("Access-Control-Allow-Methods","GET, POST, OPTIONS");
        res.setHeader("Access-Control-Allow-Headers","content-type");
        res.setHeader("Access-Control-Allow-Credentials","true");
    }
}
