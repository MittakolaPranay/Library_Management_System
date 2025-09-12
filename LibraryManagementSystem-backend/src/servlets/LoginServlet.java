package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.UserDAO;
import org.json.JSONObject;
import models.User;

import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        res.setContentType("application/json");
        PrintWriter writer = res.getWriter();
        JSONObject responseObject = new JSONObject();


        res.setHeader("Access-Control-Allow-Origin" ,"http://localhost:5173");
        res.setHeader("Access-Control-Allow-Methods","GET,POST,OPTIONS");
        res.setHeader("Access-Control-Allow-Headers","content-type");
        res.setHeader("Access-Control-Allow-Credentials","true");

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        UserDAO userDAO = new UserDAO();
        User user = userDAO.loginUser(email,password);

        if(user != null) {
            HttpSession session = req.getSession(false);

            if(session != null) {
                session.invalidate();
            }

            HttpSession newSession = req.getSession();
            newSession.setAttribute("userID",user.getId());
            newSession.setAttribute("userName",user.getName());
            newSession.setAttribute("userEmail",user.getEmail());
            newSession.setAttribute("userRole",user.getRole());

            newSession.setMaxInactiveInterval(30 * 60); // 30 minutes


            responseObject.put("status",true);
            responseObject.put("role",user.getRole());

        }else {
            responseObject.put("status",false);
        }

        writer.print(responseObject.toString());
        writer.close();
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setHeader("Access-Control-Allow-Origin" ,"http://localhost:5173");
        res.setHeader("Access-Control-Allow-Methods","GET,POST,OPTIONS");
        res.setHeader("Access-Control-Allow-Headers","content-type");
        res.setHeader("Access-Control-Allow-Credentials","true");
    }
}
