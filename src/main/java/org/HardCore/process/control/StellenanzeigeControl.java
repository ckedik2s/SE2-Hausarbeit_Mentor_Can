package org.HardCore.process.control;

import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import org.HardCore.gui.ui.MyUI;
import org.HardCore.model.dao.StellenanzeigeDAO;
import org.HardCore.model.objects.dto.StellenanzeigeDTO;
import org.HardCore.model.objects.dto.StudentDTO;
import org.HardCore.model.objects.dto.UnternehmenDTO;
import org.HardCore.model.objects.dto.UserDTO;
import org.HardCore.process.Interfaces.StellenanzeigeControlInterface;
import org.HardCore.process.exceptions.DatabaseException;
import org.HardCore.process.exceptions.StellenanzeigeException;
import org.HardCore.services.db.JDBCConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StellenanzeigeControl implements StellenanzeigeControlInterface {
    private static StellenanzeigeControl search = null;

    public static StellenanzeigeControl getInstance() {
        if (search == null) {
            search = new StellenanzeigeControl();
        }
        return search;
    }

    private StellenanzeigeControl() {

    }

    public List<StellenanzeigeDTO> getAnzeigenForUnternehmen(UnternehmenDTO unternehmenDTO) throws SQLException {
        return StellenanzeigeDAO.getInstance().getStellenanzeigenForUnternehmen(unternehmenDTO);
    }

    public List<StellenanzeigeDTO> getAnzeigenForStudent(StudentDTO studentDTO) throws SQLException {
        return StellenanzeigeDAO.getInstance().getStellenanzeigeforStudent(studentDTO);

    }

    public void createStellenanzeige(StellenanzeigeDTO stellenanzeigeDTO) throws StellenanzeigeException {
        UserDTO userDTO = ((MyUI) UI.getCurrent()).getUserDTO();
        boolean result = StellenanzeigeDAO.getInstance().createStellenanzeige(stellenanzeigeDTO, userDTO);
        if (result) {
            return;
        }
        throw new StellenanzeigeException();
    }

    public void updateStellenanzeige(StellenanzeigeDTO stellenanzeigeDTO) throws StellenanzeigeException {
        boolean result = StellenanzeigeDAO.getInstance().updateStellenanzeige(stellenanzeigeDTO);
        if (result) {
            return;
        }
        throw new StellenanzeigeException();
    }

    public void deleteStellenanzeige(StellenanzeigeDTO stellenanzeigeDTO) throws StellenanzeigeException {
        boolean result = StellenanzeigeDAO.getInstance().deleteStellenanzeige(stellenanzeigeDTO);
        if (result) {
            return;
        }
        throw new StellenanzeigeException();
    }

    public List<StellenanzeigeDTO> getAnzeigenForSearch(String suchtext, String filter) throws SQLException {
        return StellenanzeigeDAO.getInstance().getStellenanzeigenForSearch(suchtext, filter);
    }

    public int getAnzahlBewerber(StellenanzeigeDTO stellenanzeigeDTO) throws DatabaseException, SQLException {
        int anzahl_bewerber = 0;
        String sql = "SELECT count(id_bewerbung) " +
                "FROM collhbrs.bewerbung_to_stellenanzeige " +
                "WHERE id_anzeige = ? ;";
        ResultSet rs;
        PreparedStatement statement = JDBCConnection.getInstance().getPreparedStatement(sql);
        try {
            statement.setInt(1, stellenanzeigeDTO.getId_anzeige());
            rs = statement.executeQuery();
        } catch (SQLException throwables) {
            throw new DatabaseException("Fehler im SQL-Befehl: Bitte den Programmierer informieren!");
        }
        try {
            if (rs.next()) {
                anzahl_bewerber = rs.getInt(1);
            }
        } catch (SQLException e) {
            Notification.show("Es ist ein SQL-Fehler aufgetreten. Bitte informieren Sie einen Administrator!", Notification.Type.ERROR_MESSAGE);
        } finally {
            JDBCConnection.getInstance().closeConnection();
            rs.close();
        }

        return anzahl_bewerber;
    }
}
