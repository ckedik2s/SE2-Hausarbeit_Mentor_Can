package org.HardCore.process.proxy;

import com.vaadin.ui.UI;
import org.HardCore.gui.ui.MyUI;
import org.HardCore.gui.windows.ConfirmationWindow;
import org.HardCore.model.dao.RegisterDAO;
import org.HardCore.model.dao.RoleDAO;
import org.HardCore.model.dao.UserDAO;
import org.HardCore.model.objects.dto.UserDTO;
import org.HardCore.process.Interfaces.RegistrationControlInterface;
import org.HardCore.process.control.RegistrationControl;
import org.HardCore.process.exceptions.DatabaseException;
import org.HardCore.process.exceptions.EmailInUseException;
import org.HardCore.process.exceptions.EmptyFieldException;
import org.HardCore.process.exceptions.NoEqualPasswordException;
import org.HardCore.services.db.JDBCConnection;
import org.HardCore.services.util.Roles;
import org.HardCore.services.util.Views;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RegistrationControlProxy implements RegistrationControlInterface {

    private static RegistrationControlProxy registration = null;
    private RegistrationControlProxy(){
    }

    public static RegistrationControlProxy getInstance(){
        if(registration == null){
            registration = new RegistrationControlProxy();
        }
        return registration;
    }

    public void checkValid(String email, boolean emailBool, String password1, String password2, boolean password1Bool, boolean password2Bool, boolean checkBox) throws NoEqualPasswordException, DatabaseException, EmailInUseException, EmptyFieldException {
        RegistrationControl.getInstance().checkValid(email, emailBool, password1, password2, password1Bool, password2Bool, checkBox);
    }

    //User registrieren
    public void registerUser( String email, String password, String regAs ) throws DatabaseException {
        RegistrationControl.getInstance().registerUser(email, password, regAs);
    }

    //User Löschen
    public void deleteUser(UserDTO userDTO){
        RegistrationControl.getInstance().deleteUser(userDTO);
    }
}