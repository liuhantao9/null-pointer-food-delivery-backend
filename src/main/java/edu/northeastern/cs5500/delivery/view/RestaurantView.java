package edu.northeastern.cs5500.delivery.view;

import static spark.Spark.get;
import static spark.Spark.halt;
import static spark.Spark.post;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.northeastern.cs5500.delivery.JsonTransformer;
import edu.northeastern.cs5500.delivery.controller.RestaurantController;
import edu.northeastern.cs5500.delivery.controller.util.RestaurantBrief;
import edu.northeastern.cs5500.delivery.model.Dish;
import edu.northeastern.cs5500.delivery.model.Restaurant;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

@Singleton
@Slf4j
public class RestaurantView implements View {

    @Inject
    public RestaurantView() {}

    @Inject JsonTransformer jsonTransformer;

    @Inject RestaurantController restaurantController;

    @Override
    public void register() {
        log.info("Restaurant View -> register");
        // signup function
        post(
                "/restaurant/signup",
                (req, res) -> {
                    log.debug("Restaurant Signup");

                    ObjectMapper mapper = new ObjectMapper();
                    Map<String, String> paramMap =
                            mapper.readValue(
                                    req.body(), new TypeReference<Map<String, String>>() {});

                    String email = paramMap.get("email");
                    String password = paramMap.get("password");

                    if (email.isEmpty() || password.isEmpty()) {
                        halt(400);
                    }

                    Restaurant restaurant = restaurantController.signUp(email, password);
                    return restaurant;
                },
                this.jsonTransformer);

        // remove restaurant
        post(
                "/restaurant/:id/remove",
                (req, res) -> {
                    log.debug("Restaurant Remove");

                    final String id = req.params(":id");

                    if (id.isEmpty()) {
                        halt(400);
                    }

                    Boolean removeSuccessful = restaurantController.removeRestaurant(id);
                    if (!removeSuccessful) halt(400);
                    res.type("application/json");
                    res.status(200);
                    return "Successfully removed ";
                },
                this.jsonTransformer);

        // get restaurant by email
        get(
                "/restaurant/email/:email",
                (req, res) -> {
                    final String email = req.params(":email");

                    log.debug("/restaurant/:email<{}>", email);
                    if (email.isEmpty()) halt(400);

                    final String id = UUID.nameUUIDFromBytes(email.getBytes()).toString();
                    Restaurant restaurant = restaurantController.getRestaurant(id);
                    if (restaurant == null) {
                        halt(404);
                    }
                    res.type("application/json");
                    return restaurant;
                },
                this.jsonTransformer);

        // login
        post(
                "/restaurant/login",
                (req, res) -> {
                    ObjectMapper mapper = new ObjectMapper();
                    Map<String, String> paramMap =
                            mapper.readValue(
                                    req.body(), new TypeReference<Map<String, String>>() {});

                    final String email = paramMap.get("email");
                    final String password = paramMap.get("password");

                    if (email.isEmpty() || password.isEmpty()) halt(400);

                    Restaurant restaurant = this.restaurantController.logIn(email, password);
                    if (restaurant == null) halt(404);
                    res.type("application/json");
                    return restaurant;
                },
                this.jsonTransformer);

        // reset password for restaurant
        post(
                "/restaurant/:id/resetPassword",
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
                            this.restaurantController.resetPassword(id, oldPswd, newPswd);
                    if (!resetSuccessful) halt(400);
                    res.type("application/json");
                    res.status(200);
                    return "Succeess!";
                },
                this.jsonTransformer);

        // get menu
        get(
                "/restaurant/:id/menu",
                (req, res) -> {
                    final String id = req.params(":id");

                    Restaurant restaurant = restaurantController.getRestaurant(id);
                    if (restaurant == null) {
                        halt(404);
                    }
                    res.type("application/json");
                    return restaurant.getMenu();
                },
                this.jsonTransformer);

        // addMenuItem
        post(
                "/restaurant/:id/addMenuItem",
                (req, res) -> {
                    log.debug("Restaurant add menuItem");

                    final String id = req.params(":id");

                    ObjectMapper mapper = new ObjectMapper();
                    Map<String, String> paramMap =
                            mapper.readValue(
                                    req.body(), new TypeReference<Map<String, String>>() {});

                    String name = paramMap.get("name");
                    String price = paramMap.get("price");
                    String imageUrl = paramMap.get("imageUrl");

                    if (name.isEmpty() || price.isEmpty()) {
                        halt(400);
                    }
                    res.type("application/json");
                    return this.restaurantController.addMenuItem(id, name, price, imageUrl);
                },
                this.jsonTransformer);

        // addMenuItemList
        post(
                "/restaurant/:id/addMenuItemList",
                (req, res) -> {
                    log.debug("Restaurant add menuItemList");

                    final String id = req.params(":id");

                    ObjectMapper mapper = new ObjectMapper();
                    // convert JSON array to List of objects
                    List<Dish> menuItemList =
                            Arrays.asList(mapper.readValue(req.body(), Dish[].class));

                    if (menuItemList.isEmpty()) {
                        halt(400);
                    }
                    res.type("application/json");
                    return this.restaurantController.addMenuItemList(id, menuItemList);
                },
                this.jsonTransformer);

        // remove dish from menu
        post(
                "/restaurant/:id/removeMenuItem",
                (req, res) -> {
                    log.debug("MenuItem Remove");

                    final String id = req.params(":id");

                    ObjectMapper mapper = new ObjectMapper();
                    Map<String, String> paramMap =
                            mapper.readValue(
                                    req.body(), new TypeReference<Map<String, String>>() {});

                    String name = paramMap.get("name");
                    if (id.isEmpty() || name.isEmpty()) {
                        halt(400);
                    }

                    Boolean removeSuccessful = restaurantController.removeMenuItem(id, name);
                    if (!removeSuccessful) halt(400);
                    res.type("application/json");
                    res.status(200);
                    return "Successfully removed ";
                },
                this.jsonTransformer);

        // add Review
        post(
                "/restaurant/:id/addReview",
                (req, res) -> {
                    log.debug("Restaurant add Review");

                    final String id = req.params(":id");

                    ObjectMapper mapper = new ObjectMapper();
                    Map<String, String> paramMap =
                            mapper.readValue(
                                    req.body(), new TypeReference<Map<String, String>>() {});

                    String stars = paramMap.get("stars");
                    String comment = paramMap.get("comment");

                    if (stars.isEmpty()) {
                        halt(400);
                    }
                    res.type("application/json");
                    return this.restaurantController.addReview(id, stars, comment);
                },
                this.jsonTransformer);

        get(
                "/restaurants",
                (req, res) -> {
                    List<RestaurantBrief> briefs = restaurantController.getRestaurantBriefs();
                    res.type("application/json");
                    return briefs;
                },
                this.jsonTransformer);

        get(
                "/restaurants/all",
                (req, res) -> {
                    List<Restaurant> restaurants = restaurantController.getAll();
                    res.type("application/json");
                    return restaurants;
                },
                this.jsonTransformer);
    }
}
