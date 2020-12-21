package miptsbertechpdris;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class RegisterServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.getRequestDispatcher("/register.html").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    PrintWriter write = resp.getWriter();

    String username = req.getParameter("username");
    String password = req.getParameter("password");

    if (username == null || username.equals("")) {
      write.println("<h1 style=\"color:red;\">Bad username</h1>");
      RequestDispatcher rd = req.getRequestDispatcher("/register.html");
      rd.include(req, resp);
      return;
    }
    if (password == null || password.equals("")) {
      write.println("<h1 style=\"color:red;\">Bad password</h1>");
      RequestDispatcher rd = req.getRequestDispatcher("/register.html");
      rd.include(req, resp);
      return;
    }
    if (username.equals("admin")) {
      write.println("<h1 style=\"color:red;\">Username \"admin\" is not allowed</h1>");
      RequestDispatcher rd = req.getRequestDispatcher("/register.html");
      rd.include(req, resp);
      return;
    }

    if (Storage.usernameExists(username)) {
      write.println(String.format("<h1 style=\"color:red;\">Username \"%s\" is taken</h1>", username));
      RequestDispatcher rd = req.getRequestDispatcher("/register.html");
      rd.include(req, resp);
    } else {
      Storage.register(username, password);
      write.println("<h1 style=\"color:green;\">Success. Log in to proceed</h1>");
      RequestDispatcher rd = req.getRequestDispatcher("/login.html");
      rd.include(req, resp);
    }
  }
}
