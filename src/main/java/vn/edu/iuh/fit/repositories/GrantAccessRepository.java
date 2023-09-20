package vn.edu.iuh.fit.repositories;

import jakarta.servlet.http.HttpSession;
import vn.edu.iuh.fit.connection.ConnectDB;
import vn.edu.iuh.fit.models.Grant;
import vn.edu.iuh.fit.models.GrantAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.BooleanSupplier;

public class GrantAccessRepository {
    public String getRoleOfGrantAccessLoginById(String accountID) throws SQLException, ClassNotFoundException {
        String role = null;
        Connection connection;
        connection = ConnectDB.getInstance().getConnection();
        String sql = "SELECT role_id FROM grant_access WHERE account_id = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, accountID);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            role = rs.getString("role_id");
        }
        return role;
    }

    public Boolean addGrantAccess(GrantAccess newGrant) throws SQLException, ClassNotFoundException {
        Connection connection;
        connection = ConnectDB.getInstance().getConnection();
        String sql = "INSERT INTO grant_access VALUES (?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, newGrant.getRole());
        preparedStatement.setString(2,newGrant.getAccount() );
        preparedStatement.setInt(3, newGrant.getGrant().getGrantValue());
        preparedStatement.setString(4, newGrant.getNote());
        // Thực hiện truy vấn INSERT và kiểm tra kết quả
        int rowsAffected = preparedStatement.executeUpdate();
        return rowsAffected > 0;
    }

    public boolean isAccountGrand(String accountID) throws SQLException, ClassNotFoundException {
        Connection connection;
        connection = ConnectDB.getInstance().getConnection();
        String sql = "SELECT * FROM grant_access WHERE account_id = ?";
        boolean kq = false;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, accountID);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            kq = true;
        }
        return kq;
    }

    public Boolean updateGrantAccess(GrantAccess newGrant) throws SQLException, ClassNotFoundException {
        Connection connection;
        connection = ConnectDB.getInstance().getConnection();
        String sql = "UPDATE grant_access SET role_id = ?, is_grant = ?, note = ? WHERE account_id = ? ";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, newGrant.getRole());
        preparedStatement.setInt(2, newGrant.getGrant().getGrantValue());
        preparedStatement.setString(3, newGrant.getNote());
        preparedStatement.setString(4,newGrant.getAccount());
        return preparedStatement.executeUpdate()==1;
    }
}
