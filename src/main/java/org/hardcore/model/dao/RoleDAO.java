package org.hardcore.model.dao;

import com.vaadin.ui.Notification;
import org.hardcore.model.objects.dto.RoleDTO;
import org.hardcore.model.objects.dto.UserDTO;
import org.hardcore.services.util.Roles;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDAO extends AbstractDAO{
    private static RoleDAO dao = null;

    private RoleDAO() {

    }

    public static RoleDAO getInstance() {
        if (dao == null) {
            dao = new RoleDAO();
        }
        return dao;
    }

    public List<RoleDTO> getRolesForUser(UserDTO userDTO) throws SQLException {
        String sql = "SELECT rolle " +
                "FROM collhbrs.user_to_rolle " +
                "WHERE id = ? ";
        PreparedStatement statement = getPreparedStatement(sql);

        ResultSet rs = null;

        try {
            statement.setInt(1,userDTO.getId());
            rs = statement.executeQuery();
        } catch (SQLException throwables) {
            Notification.show("Es ist ein SQL-Fehler aufgetreten. Bitte informieren Sie einen Administrator!", Notification.Type.ERROR_MESSAGE);
        }

        List<RoleDTO> liste = new ArrayList<>();
        RoleDTO role;

        try {
            while (true) {
                assert rs != null;
                if (!rs.next()) break;
                role = new RoleDTO();
                role.setBezeichnung(rs.getString(1));
                liste.add(role);
            }
        } catch (SQLException ex) {
            return null;
        } finally {
            assert rs != null;
            rs.close();
        }
        return liste;
    }

    public boolean setRolesForStudent(UserDTO userDTO) {
        String sql = "INSERT INTO collhbrs.user_to_rolle VALUES (?,?)";
        PreparedStatement statement = this.getPreparedStatement(sql);

        try {
            statement.setInt(1, userDTO.getId());
            statement.setString(2, Roles.STUDENT);
            statement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    public boolean setRolesForUnternehmen(UserDTO userDTO) {
        String sql = "INSERT INTO collhbrs.user_to_rolle VALUES (?,?)";
        PreparedStatement statement = this.getPreparedStatement(sql);

        try {
            statement.setInt(1, userDTO.getId());
            statement.setString(2, Roles.UNTERNEHMEN);
            statement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
 }
