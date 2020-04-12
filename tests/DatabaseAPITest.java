import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseAPITest {
    DatabaseAPI database = new DatabaseAPI();

    public DatabaseAPITest() {
        super();
    }

    @Before
    @Test
    public void createRandomUsers() {
        database.setAccount(
                "randomuser@gmail.com",
                "correctUser",
                "random user",
                "random users address",
                "1234LF",
                "random");
    }

    @Test
    public void checkAccountValidation() {
        assertTrue(database.checkAccount("admin@gmail.com", "password"));
        assertTrue(database.checkAccount("randomuser@gmail.com", "random"));
    }

    @Test
    public void checkForcedAdmin() {
        assertEquals(1, database.checkAdmin("admin@gmail.com", "password"));
        assertEquals(0, database.checkAdmin("randomuser@gmail.com", "random"));
    }
    @Test
    public void deleteAccount() {
        assertFalse(database.delAccount("4"));
    }

}