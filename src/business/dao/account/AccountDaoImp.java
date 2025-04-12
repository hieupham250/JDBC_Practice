package business.dao.account;

import business.config.ConnectionDB;
import business.model.Account;
import datatype.Role;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDaoImp implements AccountDao {
    @Override
    public Account login(String username, String password) {
        Connection conn = null;
        CallableStatement callSt = null;
        Account account = null;
        ResultSet rs = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call login(?, ?)}");
            callSt.setString(1, username);
            callSt.setString(2, password);
            rs = callSt.executeQuery();
            if (rs.next()) {
                account = new Account();
                account.setId(rs.getInt("id"));
                account.setUsername(rs.getString("username"));
                account.setPassword(rs.getString("password"));
                account.setRole(Role.fromString(rs.getString("role")));
                account.setStatus(rs.getBoolean("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return account;
    }
}