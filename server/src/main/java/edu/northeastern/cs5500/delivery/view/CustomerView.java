package edu.northeastern.cs5500.delivery.view;

import static spark.Spark.get;
import static spark.Spark.halt;
import static spark.Spark.post;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.northeastern.cs5500.delivery.JsonTransformer;
import edu.northeastern.cs5500.delivery.controller.CustomerController;
import edu.northeastern.cs5500.delivery.model.Customer;
import edu.northeastern.cs5500.delivery.model.Payment;
import java.util.Map;
import java.util.UUID;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

@Singleton
@Slf4j
public class CustomerView implements View {

    @Inject
    public CustomerView() {}

    @Inject JsonTransformer jsonTransformer;

    @Inject CustomerController customerController;

    @Override
    public void register() {
        log.info("Customer View -> register");

        post(
                "/customer/signup",
                (req, res) -> {
                    log.debug("Customer Signup");

                    ObjectMapper mapper = new ObjectMapper();
                    Map<String, String> paramMap =
                            mapper.readValue(
                                    req.body(), new TypeReference<Map<String, String>>() {});

                    final String email = paramMap.get("email");
                    final String password = paramMap.get("password");

                    if (email.isEmpty() || password.isEmpty()) halt(400);

                    Customer customer = customerController.signUp(email, password);
                    customer.setPassword("N/A");
                    return customer;
                },
                this.jsonTransformer);

        get(
                "/customer/email/:email",
                (req, res) -> {
                    final String email = req.params(":email");

                    log.debug("/cusotmer/:email<{}>", email);
                    if (email.isEmpty()) halt(400);

                    final String id = UUID.nameUUIDFromBytes(email.getBytes()).toString();
                    Customer customer = customerController.getCustomer(id);
                    if (customer == null) {
                        halt(404);
                    }
                    res.type("application/json");
                    customer.setPassword("N/A");
                    return customer;
                },
                this.jsonTransformer);

        get(
                "/customer/id/:id",
                (req, res) -> {
                    final String id = req.params(":id");

                    log.debug("/cusotmer/:id<{}>", id);
                    if (id.isEmpty()) halt(400);

                    Customer customer = customerController.getCustomer(id);
                    if (customer == null) {
                        halt(404);
                    }
                    res.type("application/json");
                    customer.setPassword("N/A");
                    return customer;
                },
                this.jsonTransformer);

        post(
                "/customer/login",
                (req, res) -> {
                    ObjectMapper mapper = new ObjectMapper();
                    Map<String, String> paramMap =
                            mapper.readValue(
                                    req.body(), new TypeReference<Map<String, String>>() {});

                    final String email = paramMap.get("email");
                    final String password = paramMap.get("password");

                    if (email.isEmpty() || password.isEmpty()) halt(400);

                    Customer customer = this.customerController.login(email, password);
                    if (customer == null) halt(404);
                    res.type("application/json");
                    customer.setPassword("N/A");
                    return customer;
                },
                this.jsonTransformer);

        post(
                "/customer/:id/editProfile",
                (req, res) -> {
                    ObjectMapper mapper = new ObjectMapper();
                    Map<String, String> paramMap =
                            mapper.readValue(
                                    req.body(), new TypeReference<Map<String, String>>() {});

                    final String id = req.params(":id");
                    final String address = (String) paramMap.getOrDefault("address", "");
                    final String avatar = (String) paramMap.getOrDefault("avatar", "");
                    final String phone = (String) paramMap.getOrDefault("phone", "");
                    final String name = (String) paramMap.getOrDefault("name", "");

                    if (address.isEmpty() && avatar.isEmpty() && phone.isEmpty() && name.isEmpty())
                        halt(400);

                    Customer customer =
                            this.customerController.editProfile(id, address, avatar, phone, name);
                    if (customer == null) halt(404);
                    res.type("application/json");
                    customer.setPassword("N/A");
                    return customer;
                },
                this.jsonTransformer);

        post(
                "/customer/:id/editPayment",
                (req, res) -> {
                    ObjectMapper mapper = new ObjectMapper();
                    Payment payment = mapper.readValue(req.body(), Payment.class);

                    final String id = req.params(":id");

                    if (id.isEmpty() || payment == null) halt(400);

                    Customer customer = this.customerController.editPayment(id, payment);
                    if (customer == null) halt(404);
                    res.type("application/json");
                    customer.setPassword("N/A");
                    return customer;
                },
                this.jsonTransformer);

        post(
                "/customer/:id/resetPassword",
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
                            this.customerController.resetPassword(id, oldPswd, newPswd);
                    if (!resetSuccessful) halt(400);
                    res.type("application/json");
                    res.status(200);
                    return "Succeess!";
                });
    }
}
