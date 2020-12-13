package edu.northeastern.cs5500.delivery.controller;

import edu.northeastern.cs5500.delivery.model.Driver;
import edu.northeastern.cs5500.delivery.repository.GenericRepository;
import edu.northeastern.cs5500.delivery.repository.InMemoryRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class DriverControllerTest {

    private GenericRepository repo = new InMemoryRepository<Driver>();
    private DriverController driverController = new DriverController(repo);
    private Driver driver = new Driver();

    public DriverControllerTest() {
        driver.setEmail("email");
        driver.setPassword("password");
        driver.setName("name");
        driver.setPhone("phone");
    }

    @Test
    public void testAddDriver() throws Exception {
        Driver testNull = new Driver();
        Assertions.assertThrows(
                Exception.class,
                () -> {
                    driverController.addDriver(testNull);
                });

        driverController.addDriver(driver);
        Assertions.assertEquals(driver, repo.get(driver.getId()));

        Assertions.assertEquals(repo.get(driver.getId()), driverController.addDriver(driver));
        repo.delete(driver.getId());
    }

    @Test
    public void testGetDriver() throws Exception {
        Assertions.assertNull(driverController.getDriver("null"));

        driverController.addDriver(driver);
        Assertions.assertTrue(driverController.getDriver(driver.getId()).equals(driver));
        repo.delete(driver.getId());
    }

    @Test
    public void testSignup() throws Exception {
        Driver test = driverController.signUp("email", "password");
        Assertions.assertTrue(repo.getAll().size() != 0);
        Assertions.assertTrue(((Driver) repo.get(test.getId())).getEmail().equals("email"));
        repo.delete(test.getId());
    }

    @Test
    public void testLogin() throws Exception {
        Driver test = driverController.signUp("email", "password");
        Assertions.assertEquals(test, driverController.login("email", "password"));
        Assertions.assertNull(driverController.login("email", ""));
        Assertions.assertThrows(
                Exception.class,
                () -> {
                    driverController.login("gggg", "");
                });
        repo.delete(test.getId());
    }

    @Test
    public void testEditProfile() throws Exception {
        Driver test = driverController.signUp("email", "password");
        driverController.editProfile(test.getId(), "name", "phone", 100.0, 100.0);
        Assertions.assertTrue(test.getName().equals("name"));
        Assertions.assertTrue(test.getPhone().equals("phone"));
        repo.delete(test.getId());
    }

    @Test
    public void testResetPassword() throws Exception {
        Driver test = driverController.signUp("email", "password");
        driverController.resetPassword(test.getId(), "password", "newPassword");
        Assertions.assertNotNull(driverController.login("email", "newPassword"));
        Assertions.assertNull(driverController.login("email", "password"));
        repo.delete(test.getId());
    }
}
