package org.HardCore.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends AbstractDAO {
    private static UserDAO dao = null;

    private UserDAO() {

    }

    public static UserDAO getInstance() {
        if (dao == null) {
            dao = new UserDAO();
        }
        return dao;
    }

    public int getMaxID() throws SQLException {
        String sql = "SELECT max(id) " +
        "FROM collhbrs.user ;";
        PreparedStatement statement = getPreparedStatement(sql);
        ResultSet rs = null;

        try {
            rs = statement.executeQuery();
        } catch (SQLException throwables) {
            System.out.println("Fehler 1 bei addStudent");
        }

        int currentValue = 0;

        try {
            rs.next();
            currentValue = rs.getInt(1);
        } catch (SQLException throwables) {
            System.out.println("Fehler 2 bei addStudent");
        } finally {
            rs.close();
        }
        return currentValue;
    }
}
