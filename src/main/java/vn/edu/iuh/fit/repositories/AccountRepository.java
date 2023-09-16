package vn.edu.iuh.fit.repositories;

import vn.edu.iuh.fit.connection.ConnectDB;
import vn.edu.iuh.fit.models.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountRepository {
    public Optional<Account> login(String email, String password) throws SQLException, ClassNotFoundException {
        Connection connection;
        connection = ConnectDB.getInstance().getConnection();
        try {

            String sql = "Select * from account where email =? and password=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Xử lý dữ liệu từ ResultSet
            while (resultSet.next()) {
                String account_id = resultSet.getString("account_id");
                String full_name  = resultSet.getString("full_name");
                String pass= resultSet.getString("password");
                String mail = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                Integer status = resultSet.getInt("status");
                Account account = new Account(account_id, full_name, pass, mail, phone, status);
                return Optional.of(account);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }


    public boolean addAccount(Account newAccount) throws SQLException, ClassNotFoundException {
        Connection connection;
        connection = ConnectDB.getInstance().getConnection();
        try {
            String sql = "INSERT into account VALUES(?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, newAccount.getAccount_id());
            preparedStatement.setString(2, newAccount.getFull_name());
            preparedStatement.setString(3, newAccount.getPassword());
            preparedStatement.setString(4, newAccount.getEmail());
            preparedStatement.setString(5, newAccount.getPhone());
            preparedStatement.setInt(6, newAccount.getStatus());
            System.out.println(newAccount.toString());
            // Thực hiện truy vấn INSERT và kiểm tra kết quả
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Account added successfully.");
                return true;
            } else {
                System.out.println("Failed to add account.");
                return false;
            }
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public Optional<Account> findAccountByID(String id) throws SQLException, ClassNotFoundException {
        Connection connection;
        connection = ConnectDB.getInstance().getConnection();
        String sql = "Select * from account where account_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            String account_id = resultSet.getString("account_id");
            String full_name = resultSet.getString("full_name");
            String pass = resultSet.getString("password");
            String mail = resultSet.getString("email");
            String phone = resultSet.getString("phone");
            Integer status = resultSet.getInt("status");
            Account account = new Account(account_id, full_name, pass, mail, phone, status);
            return Optional.of(account);
        }
        return Optional.empty();
    }

    public List<Account> getAllAccountExcludingLoggedInAccount(String id) throws SQLException, ClassNotFoundException {
        List<Account> listAccount = new ArrayList<>();
        Connection connection;
        connection = ConnectDB.getInstance().getConnection();
        String sql = "SELECT * FROM account WHERE account_id <> ? AND status <> -1 ";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            listAccount.add(new Account(rs.getString("account_id"), rs.getString("full_name"), rs.getString("password"), rs.getString("email"), rs.getString("phone"), rs.getInt("status")));
        }
        return listAccount;

    }

    public boolean deleteAccountByID(String id) throws SQLException, ClassNotFoundException {
        Connection connection;
        connection = ConnectDB.getInstance().getConnection();
        String sql = "UPDATE account SET status=-1 WHERE account_id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,id);
        return preparedStatement.executeUpdate() == 1;

    }

    public Boolean updateAccount(Account accountUdate) throws SQLException, ClassNotFoundException {
        Connection connection;
        connection = ConnectDB.getInstance().getConnection();
        String sql = "UPDATE account SET full_name=?, email=?, password=?, phone=? WHERE account_id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, accountUdate.getFull_name());
        preparedStatement.setString(2, accountUdate.getEmail());
        preparedStatement.setString(3,accountUdate.getPassword());
        preparedStatement.setString(4,accountUdate.getPhone());
        preparedStatement.setString(5,accountUdate.getAccount_id());
        return  preparedStatement.executeUpdate() == 1;
    }
}
