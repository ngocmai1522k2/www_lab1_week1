package vn.edu.iuh.fit.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
                Account newAccount = new Account();
                newAccount.setAccount_id(req.getParameter("accountID"));
                newAccount.setFull_name(req.getParameter("fullName"));
                newAccount.setPassword(req.getParameter("password"));
                newAccount.setEmail(req.getParameter("email"));
                newAccount.setPhone(req.getParameter("phone"));
                try {
                    if(accountRepository.login(newAccount.getEmail(),newAccount.getPassword()).isEmpty()) {
                        newAccount.setStatus(1);
                        try {
                            rs = accountRepository.addAccount(newAccount);
                        } catch (SQLException | ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                        if(rs){
                            RequestDispatcher dispatcher = req.getRequestDispatcher("insert_account.jsp");
                            dispatcher.include(req, resp);
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
                    }else {
                        PrintWriter out = resp.getWriter();
                        out.print("<h3 style='color: red; margin: inherit'> Đã tồn tại account</h3>");
                    }
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }

                break;
        }
    }



    private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException, ClassNotFoundException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String url = "";
        if(accountRepository.login(email,password).isPresent()){
            url = "/dashboard.jsp";
            req.setAttribute("account", accountRepository.login(email,password).get());
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
