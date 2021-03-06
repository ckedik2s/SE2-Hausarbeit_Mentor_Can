package DAOTests;

import org.hardcore.model.dao.BewerbungDAO;
import org.hardcore.model.objects.dto.StudentDTO;
import org.hardcore.model.objects.dto.UserDTO;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class BewerbungDAOTest {


    @Test
    public void testCreate() {
        BewerbungDAO bewDao = BewerbungDAO.getInstance();
        assertNotNull(bewDao);
    }
    @Test
    public void createBewerbung() {
        UserDTO userDTO = new UserDTO();
        StudentDTO studentDAO = new StudentDTO(userDTO);
        assertFalse(BewerbungDAO.getInstance().createBewerbung("Lachs",studentDAO));
    }

}