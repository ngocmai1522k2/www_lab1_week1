package vn.edu.iuh.fit.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.edu.iuh.fit.models.Account;
import vn.edu.iuh.fit.models.Role;
import vn.edu.iuh.fit.repositories.AccountRepository;
import vn.edu.iuh.fit.repositories.GrantAccessRepository;
import vn.edu.iuh.fit.repositories.RoleRepository;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/login")
public class ControllerServlet extends HttpServlet {
    private final AccountRepository accountRepository = new AccountRepository();
    private final RoleRepository roleRepository = new RoleRepository();
    private final GrantAccessRepository grantAccessRepository = new GrantAccessRepository();
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

            case "updateAccount":
                try {
                    Boolean rsu = false;
                    Account newAcc = new Account();
                    newAcc.setAccount_id(req.getParameter("accountID"));
                    newAcc.setFull_name(req.getParameter("fullName"));
                    newAcc.setPassword(req.getParameter("password"));
                    newAcc.setEmail(req.getParameter("email"));
                    newAcc.setPhone(req.getParameter("phone"));
                    rsu = accountRepository.updateAccount(newAcc);
                    if(rsu){
                        PrintWriter out = resp.getWriter();
                        out.println("<script type=\"text/javascript\">");
                        out.println("alert('Update Successfully');");
                        resp.sendRedirect("login?action=listAllAccount");
                        out.println("</script>");
                    }else {
                        PrintWriter out = resp.getWriter();
                        out.println("<script type=\"text/javascript\">");
                        out.println("alert('Failed');");
                        resp.sendRedirect("login?action=update");
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
        HttpSession session = req.getSession(true);
        String action = req.getParameter("action");
        switch (action){
            case "info":
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/dashboard.jsp");
                requestDispatcher.forward(req,resp);
                break;
            case "listRole":
                Account accountLogin = (Account) session.getAttribute("accountLogin");
                try {
                    List<Role> listRoleAccount = roleRepository.getListRoleForAccount(accountLogin.getAccount_id());
                    session.setAttribute("listRole",listRoleAccount);
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }

                RequestDispatcher rd = req.getRequestDispatcher("/list_role.jsp");
                rd.forward(req,resp);
                break;
            case "listAllAccount":
                try {
                    Account loggedInAccount = (Account) session.getAttribute("accountLogin");
                    List<Account> listAccount = accountRepository.getAllAccountExcludingLoggedInAccount(loggedInAccount.getAccount_id());
                    session.setAttribute("listAccount", listAccount);
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                req.getRequestDispatcher("/list_account.jsp").forward(req,resp);
                break;
            case "delete":
                String id = req.getParameter("id");
                try {
                    if(accountRepository.deleteAccountByID(id)){
                        PrintWriter out = resp.getWriter();
                        out.println("<script type=\"text/javascript\">");
                        out.println("alert('Xóa thành công');");
                        out.println("</script>");
                    }
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                resp.sendRedirect("login?action=listAllAccount");
                break;
            case "update":
                try {
                    Account accountUpdate = accountRepository.findAccountByID(req.getParameter("id")).get();
                    req.setAttribute("accountUpdate",accountUpdate);
                    req.getRequestDispatcher("/update_account.jsp").forward(req,resp);
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
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
            Account acc = (Account) session.getAttribute("accountLogin");
            session.setAttribute("grant_accessLogin", grantAccessRepository.getRoleOfGrantAccessLoginById(acc.getAccount_id()));
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
