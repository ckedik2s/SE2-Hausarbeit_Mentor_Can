import org.HardCore.model.dao.RoleDAO;
import org.HardCore.model.objects.dto.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class RoleDAOTest {

    private static RoleDAO roleDAO;
    private User user;

    @Test
    static void testCreate() {
        roleDAO =  RoleDAO.getInstance();
        assertNotNull(roleDAO);
    }
    @Test
    void testRead() {

    }
    @Test
    void testUpdate() {

    }
    @Test
    void testDelete() {

    }
}