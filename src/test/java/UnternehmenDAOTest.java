import org.HardCore.model.dao.UnternehmenDAO;
import org.HardCore.model.objects.dto.UserDTO;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;


public class UnternehmenDAOTest {

    private UnternehmenDAO uDAO;
    private UserDTO userDTO;
    private Object un;

    @Test
    public void testCreate() {
        uDAO =  UnternehmenDAO.getInstance();
        assertNotNull(uDAO);
    }

}