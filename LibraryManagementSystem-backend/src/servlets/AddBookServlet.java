package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import models.Book;
import models.BookDAO;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class AddBookServlet extends HttpServlet {

    private static final String UPLOAD_FOLDER = "D:\\PRANAY\\Projects\\LibraryImages\\";
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        res.setContentType("application/json");
        JSONObject responseObject = new JSONObject();
        PrintWriter writer = res.getWriter();

        res.setHeader("Access-Control-Allow-Origin" ,"http://localhost:5173");
        res.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS");
        res.setHeader("Access-Control-Allow-Headers","content-type");
        res.setHeader("Access-Control-Allow-Credentials","true");

        //access form fields

        try {

            String title = req.getParameter("title");
            String author = req.getParameter("author");
            String isbn = req.getParameter("isbn");
            String stringCopies = req.getParameter("copies");
            int copies = Integer.parseInt(stringCopies);
            String stringAvailable = req.getParameter("available");
            int available = Integer.parseInt(stringAvailable);
            Part image = req.getPart("image");

            if(title == null || author == null || isbn == null || copies <= 0 || available <= 0 || image == null) {
                responseObject.put("status", "error");
                responseObject.put("message", "All fields are required");
                writer.print(responseObject.toString());
                return;
            }

            String fileName = image.getSubmittedFileName();
            InputStream fileContent = image.getInputStream();


            File uploads = new File(UPLOAD_FOLDER);
            if(!uploads.exists()) uploads.mkdirs();

            File file = new File(uploads, fileName);
            Files.copy(fileContent,file.toPath(), StandardCopyOption.REPLACE_EXISTING);

            String imageURL = "/LibraryImages/" + fileName;

            Book book = new Book(title,author,isbn,copies,available,imageURL);
            BookDAO bookDAO = new BookDAO();

            boolean success = bookDAO.addBook(book);

            if(success) {
                responseObject.put("status",true);
                responseObject.put("message","book added successfully");
            }else {
                responseObject.put("status",false);
                responseObject.put("message","failed to add book");
            }
        }catch (Exception e) {

            responseObject.put("status","server error");
            responseObject.put("message","error from server");
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
