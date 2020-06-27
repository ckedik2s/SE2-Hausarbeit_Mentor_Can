package org.HardCore.process.proxy;

import org.HardCore.model.dao.BewerbungDAO;
import org.HardCore.model.dao.StudentDAO;
import org.HardCore.model.dao.UnternehmenDAO;
import org.HardCore.model.objects.dto.BewerbungDTO;
import org.HardCore.model.objects.dto.StudentDTO;
import org.HardCore.model.objects.dto.UnternehmenDTO;
import org.HardCore.model.objects.dto.UserDTO;
import org.HardCore.process.Interfaces.ProfileControlInterface;
import org.HardCore.process.control.ProfileControl;
import org.HardCore.process.exceptions.ProfileException;

import java.util.List;

public class ProfileControlProxy implements ProfileControlInterface {
    private static ProfileControlProxy profileControl = null;

    private ProfileControlProxy() {
    }

    public static ProfileControlProxy getInstance() {
        if (profileControl == null) {
            profileControl = new ProfileControlProxy();
        }
        return profileControl;
    }


    public void updateStudentData(StudentDTO studentDTO) throws ProfileException {
        ProfileControl.getInstance().updateStudentData(studentDTO);
    }

    public void updateUnternehmenData(UnternehmenDTO unternehmenDTO) throws ProfileException {
        ProfileControl.getInstance().updateUnternehmenData(unternehmenDTO);
    }

    public StudentDTO getStudent(UserDTO userDTO) {
        return ProfileControl.getInstance().getStudent(userDTO);
    }

    public UnternehmenDTO getUnternehmen(UserDTO userDTO) {
        return ProfileControl.getInstance().getUnternehmen(userDTO);
    }

    public void setBewerbung(String text, StudentDTO studentDTO) throws ProfileException {
        ProfileControl.getInstance().setBewerbung(text, studentDTO);
    }

    public List<BewerbungDTO> getBewerbung(StudentDTO studentDTO) {
        return ProfileControl.getInstance().getBewerbung(studentDTO);
    }
}