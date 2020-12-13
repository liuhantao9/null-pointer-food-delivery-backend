package edu.northeastern.cs5500.delivery.controller;

import com.mongodb.lang.Nullable;
import edu.northeastern.cs5500.delivery.App;
import edu.northeastern.cs5500.delivery.model.Driver;
import edu.northeastern.cs5500.delivery.repository.GenericRepository;
import edu.northeastern.cs5500.delivery.utils.PasswordUtils;
import java.util.UUID;
import javax.inject.Inject;

public class DriverController {
    private final GenericRepository<Driver> driverRepository;

    @Inject
    public DriverController(GenericRepository<Driver> driverRepository) {
        this.driverRepository = driverRepository;
    }

    public Driver addDriver(Driver driver) throws Exception {
        if (!driver.isValid()) {
            throw new Exception("InvalidDriverException");
        }

        String id = UUID.nameUUIDFromBytes(driver.getEmail().getBytes()).toString();
        driver.setId(id);

        if (id != null && this.driverRepository.get(id) != null) {
            return this.getDriver(id);
        }

        String hashedPwd = PasswordUtils.generateSecurePassword(driver.getPassword(), App.SALT);
        driver.setPassword(hashedPwd);

        this.driverRepository.add(driver);
        return this.getDriver(id);
    }

    @Nullable
    public Driver getDriver(String id) throws Exception {
        Driver driver = this.driverRepository.get(id);
        if (driver == null) return null;

        return driver;
    }

    @Nullable
    public Driver signUp(String email, String password) throws Exception {
        Driver driver = new Driver();
        driver.setEmail(email);
        driver.setPassword(password);

        return this.addDriver(driver);
    }

    @Nullable
    public Driver login(String email, String password) throws Exception {
        String id = UUID.nameUUIDFromBytes(email.getBytes()).toString();
        Driver driver = this.driverRepository.get(id);
        if (PasswordUtils.verifyUserPassword(password, driver.getPassword(), App.SALT)) {
            return driver;
        }
        return null;
    }

    @Nullable
    public Driver editProfile(
            String id, String name, String phone, double longitude, double latitude)
            throws Exception {
        Driver driver = this.driverRepository.get(id);
        if (driver == null) return null;
        if (!phone.isEmpty()) driver.setPhone(phone);
        if (!name.isEmpty()) driver.setName(name);
        driver.setLongitude(longitude);
        driver.setLatitude(latitude);
        this.driverRepository.update(driver);
        return getDriver(id);
    }

    @Nullable
    public boolean resetPassword(String id, String oldPwd, String newPwd) {
        Driver driver = this.driverRepository.get(id);

        if (PasswordUtils.verifyUserPassword(oldPwd, driver.getPassword(), App.SALT)) {
            String hashedNewPwd = PasswordUtils.generateSecurePassword(newPwd, App.SALT);
            driver.setPassword(hashedNewPwd);
            driver = this.driverRepository.update(driver);
            return true;
        }
        return false;
    }
}
