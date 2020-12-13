package edu.northeastern.cs5500.delivery.view;

import static spark.Spark.get;
import static spark.Spark.halt;
import static spark.Spark.post;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.northeastern.cs5500.delivery.JsonTransformer;
import edu.northeastern.cs5500.delivery.controller.DriverController;
import edu.northeastern.cs5500.delivery.model.Driver;
import java.util.Map;
import java.util.UUID;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

@Singleton
@Slf4j
public class DriverView implements View {

    @Inject
    public DriverView() {}

    @Inject JsonTransformer jsonTransformer;

    @Inject DriverController driverController;

    @Override
    public void register() {
        log.info("Driver View -> register");

        post(
                "/driver/signup",
                (req, res) -> {
                    log.debug("Driver Signup");

                    ObjectMapper mapper = new ObjectMapper();
                    Map<String, String> paramMap =
                            mapper.readValue(
                                    req.body(), new TypeReference<Map<String, String>>() {});

                    final String email = paramMap.get("email");
                    final String password = paramMap.get("password");

                    if (email.isEmpty() || password.isEmpty()) halt(400);

                    Driver driver = driverController.signUp(email, password);
                    driver.setPassword("N/A");
                    return driver;
                },
                this.jsonTransformer);

        get(
                "/driver/email/:email",
                (req, res) -> {
                    final String email = req.params(":email");

                    log.debug("/driver/:email<{}>", email);
                    if (email.isEmpty()) halt(400);

                    final String id = UUID.nameUUIDFromBytes(email.getBytes()).toString();
                    Driver driver = driverController.getDriver(id);
                    if (driver == null) {
                        halt(404);
                    }
                    res.type("application/json");
                    driver.setPassword("N/A");
                    return driver;
                },
                this.jsonTransformer);

        get(
                "/driver/id/:id",
                (req, res) -> {
                    final String id = req.params(":id");

                    log.debug("/driver/:id<{}>", id);
                    if (id.isEmpty()) halt(400);

                    Driver driver = driverController.getDriver(id);
                    if (driver == null) {
                        halt(404);
                    }
                    res.type("application/json");
                    driver.setPassword("N/A");
                    return driver;
                },
                this.jsonTransformer);

        post(
                "/driver/login",
                (req, res) -> {
                    ObjectMapper mapper = new ObjectMapper();
                    Map<String, String> paramMap =
                            mapper.readValue(
                                    req.body(), new TypeReference<Map<String, String>>() {});

                    final String email = paramMap.get("email");
                    final String password = paramMap.get("password");

                    if (email.isEmpty() || password.isEmpty()) halt(400);

                    Driver driver = this.driverController.login(email, password);
                    if (driver == null) halt(404);
                    res.type("application/json");
                    driver.setPassword("N/A");
                    return driver;
                },
                this.jsonTransformer);

        post(
                "/driver/:id/editProfile",
                (req, res) -> {
                    ObjectMapper mapper = new ObjectMapper();
                    Driver driver = mapper.readValue(req.body(), Driver.class);

                    driver =
                            this.driverController.editProfile(
                                    driver.getId(),
                                    driver.getName(),
                                    driver.getPhone(),
                                    driver.getLongitude(),
                                    driver.getLatitude());
                    if (driver == null) halt(404);
                    res.type("application/json");
                    driver.setPassword("N/A");
                    return driver;
                },
                this.jsonTransformer);

        post(
                "/driver/:id/resetPassword",
                (req, res) -> {
                    ObjectMapper mapper = new ObjectMapper();
                    Map<String, String> paramMap =
                            mapper.readValue(
                                    req.body(), new TypeReference<Map<String, String>>() {});

                    final String id = req.params(":id");
                    final String oldPswd = paramMap.get("old-password");
                    final String newPswd = paramMap.get("new-password");
                    if (oldPswd.isEmpty() || newPswd.isEmpty()) halt(400);

                    boolean resetSuccessful =
                            this.driverController.resetPassword(id, oldPswd, newPswd);
                    if (!resetSuccessful) halt(400);
                    res.type("application/json");
                    res.status(200);
                    return "Succeess!";
                });
    }
}
