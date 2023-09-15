package vn.edu.iuh.fit.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.iuh.fit.models.Account;
import vn.edu.iuh.fit.repositories.AccountRepository;
import vn.edu.iuh.fit.repositories.GrantAccessRepository;
import vn.edu.iuh.fit.repositories.RoleRepository;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/login")
public class ControllerServlet extends HttpServlet {
    private final AccountRepository accountRepository = new AccountRepository();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        String action = req.getParameter("action");
        switch (action){
            case "checkLogin":
                try {
                    login(req,resp);
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "addAccount":
                boolean rs = false;
                Account accountLogin = (Account) session.getAttribute("accountLogin");
                System.out.println("accountLogin: "+accountLogin);
                Account newAccount = new Account();
                newAccount.setAccount_id(req.getParameter("accountID"));
                newAccount.setFull_name(req.getParameter("fullName"));
                newAccount.setPassword(req.getParameter("password"));
                newAccount.setEmail(req.getParameter("email"));
                newAccount.setPhone(req.getParameter("phone"));
                newAccount.setStatus(1);
                try {
                    rs = accountRepository.addAccount(newAccount);
                    if(rs){
                            PrintWriter out = resp.getWriter();
                            out.println("<script type=\"text/javascript\">");
                            out.println("alert('Successfully');");
                            out.println("location='insert_account.jsp';");
                            out.println("</script>");
                    }else {
                            PrintWriter out = resp.getWriter();
                            out.println("<script type=\"text/javascript\">");
                            out.println("alert('Failed');");
                            out.println("location='insert_account.jsp';");
                            out.println("</script>");
                    }
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                break;

        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action){
            case "info":
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/dashboard.jsp");
                requestDispatcher.forward(req,resp);
                break;
            case "listRole":

                break;
        }
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException, ClassNotFoundException {
        HttpSession session = req.getSession(true);
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String url = "";
        if(accountRepository.login(email,password).isPresent()){
            url = "/dashboard.jsp";
            session.setAttribute("accountLogin", accountRepository.login(email,password).get());
        }else{
            resp.setContentType("text/html");
            url = "/index.jsp";
            PrintWriter out = resp.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Không tồn tại account này!');");
            out.println("</script>");
        }

        RequestDispatcher rd = req.getRequestDispatcher(url);
        rd.include(req,resp);
    }


}
