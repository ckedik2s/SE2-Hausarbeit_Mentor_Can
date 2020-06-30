package org.hardcore.process.Interfaces;

import org.hardcore.model.objects.dto.UserDTO;
import org.hardcore.process.exceptions.DatabaseException;
import org.hardcore.process.exceptions.EmailInUseException;
import org.hardcore.process.exceptions.EmptyFieldException;
import org.hardcore.process.exceptions.NoEqualPasswordException;

import java.sql.SQLException;

public interface RegistrationControlInterface {

    void checkValid(String email, boolean emailBool, String password1, String password2, boolean password1Bool, boolean password2Bool, boolean checkBox) throws NoEqualPasswordException, DatabaseException, EmailInUseException, EmptyFieldException, SQLException;

    //User registrieren
    void registerUser( String email, String password, String regAs ) throws DatabaseException, SQLException;

    //User Löschen
    void deleteUser(UserDTO userDTO);
}
