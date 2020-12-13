package edu.northeastern.cs5500.delivery.controller;

import edu.northeastern.cs5500.delivery.App;
import edu.northeastern.cs5500.delivery.controller.util.RestaurantBrief;
import edu.northeastern.cs5500.delivery.model.Dish;
import edu.northeastern.cs5500.delivery.model.Restaurant;
import edu.northeastern.cs5500.delivery.model.Review;
import edu.northeastern.cs5500.delivery.repository.GenericRepository;
import edu.northeastern.cs5500.delivery.utils.PasswordUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RestaurantController {
    private final GenericRepository<Restaurant> restaurantRepository;

    @Inject
    RestaurantController(GenericRepository<Restaurant> restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Nonnull
    public Restaurant addRestaurant(Restaurant restaurant) throws Exception {

        if (!restaurant.isValid()) {
            throw new Exception("InvalidRestaurantException");
        }

        String id = UUID.nameUUIDFromBytes(restaurant.getEmail().getBytes()).toString();
        restaurant.setId(id);

        if (id != null && this.restaurantRepository.get(id) != null) {
            return this.getRestaurant(id);
        }

        String hashedPwd = PasswordUtils.generateSecurePassword(restaurant.getPassword(), App.SALT);
        restaurant.setPassword(hashedPwd);

        this.restaurantRepository.add(restaurant);
        return this.getRestaurant(id);
    }

    @Nullable
    public Restaurant getRestaurant(String id) throws Exception {
        Restaurant restaurant = this.restaurantRepository.get(id);
        if (restaurant != null) {
            restaurant.setPassword("N/A");
        }
        return restaurant;
    }

    @Nullable
    public Restaurant signUp(String email, String password) throws Exception {
        Restaurant restaurant = new Restaurant();
        restaurant.setEmail(email);
        restaurant.setPassword(password);

        return this.addRestaurant(restaurant);
    }

    @Nullable
    public boolean resetPassword(String id, String oldPswd, String newPswd) {
        Restaurant restaurant = this.restaurantRepository.get(id);
        if (PasswordUtils.verifyUserPassword(oldPswd, restaurant.getPassword(), App.SALT)) {
            String hashedNewPswd = PasswordUtils.generateSecurePassword(newPswd, App.SALT);
            restaurant.setPassword(hashedNewPswd);
            restaurant = this.restaurantRepository.update(restaurant);
            return true;
        }
        return false;
    }

    @Nullable
    public Restaurant addReview(String restaurantID, String stars, String comment)
            throws Exception {
        Restaurant restaurant = this.restaurantRepository.get(restaurantID);
        Review review = new Review(stars, comment);
        restaurant.addReview(review);
        this.restaurantRepository.update(restaurant);
        return restaurant;
    }

    @Nullable
    public Restaurant logIn(String email, String password) throws Exception {
        String id = this.getIdFromEmail(email);
        Restaurant restaurant = this.restaurantRepository.get(id);
        if (PasswordUtils.verifyUserPassword(password, restaurant.getPassword(), App.SALT)) {
            return restaurant;
        }
        return null;
    }

    public Boolean removeRestaurant(String restaurantID) throws Exception {
        Restaurant curRestaurant = this.getRestaurant(restaurantID);
        if (curRestaurant != null) {
            restaurantRepository.delete(restaurantID);
            return true;
        }
        return false;
    }

    public Restaurant addMenuItem(String restaurantID, Dish dish) throws Exception {
        Restaurant restaurant = this.restaurantRepository.get(restaurantID);
        List<Dish> menu = restaurant.getMenu();
        menu.add(dish);
        this.restaurantRepository.update(restaurant);
        return restaurant;
    }

    public Restaurant addMenuItemList(String restaurantID, List<Dish> dishList) throws Exception {
        Restaurant restaurant = this.restaurantRepository.get(restaurantID);
        List<Dish> menu = restaurant.getMenu();
        menu.addAll(dishList);
        this.restaurantRepository.update(restaurant);
        return restaurant;
    }

    @Nullable
    public List<RestaurantBrief> getRestaurantBriefs() {
        List<Restaurant> restaurants = (List<Restaurant>) this.restaurantRepository.getAll();
        List<RestaurantBrief> res = new ArrayList<RestaurantBrief>();
        for (Restaurant r : restaurants) {
            RestaurantBrief brief = new RestaurantBrief(r.getId(), r.getName(), r.getUrl());
            res.add(brief);
        }
        return res;
    }

    public Restaurant addMenuItem(String restaurantID, String name, String price, String imageUrl)
            throws Exception {
        Dish dish = new Dish(name, Float.parseFloat(price), imageUrl);
        return addMenuItem(restaurantID, dish);
    }

    // remove a Menu item
    public Boolean removeMenuItem(String restaurantID, String name) throws Exception {
        Restaurant restaurant = this.restaurantRepository.get(restaurantID);
        if (restaurant == null) {
            return false;
        }
        Boolean successfullRemoval = false;
        List<Dish> menu = restaurant.getMenu();
        Iterator<Dish> itr = menu.iterator();
        while (itr.hasNext()) {
            if (itr.next().getName().equals(name)) {
                System.out.println(menu.size());
                itr.remove();
                successfullRemoval = true;
                System.out.println(menu.size());
            }
        }
        this.restaurantRepository.update(restaurant);
        return successfullRemoval;
    }

    public List<Dish> getMenu(String restaurantID) throws Exception {
        Restaurant restaurant = getRestaurant(restaurantID);
        return restaurant.getMenu();
    }

    private String getIdFromEmail(String email) {
        String id = UUID.nameUUIDFromBytes(email.getBytes()).toString();
        return id;
    }

    public List<Restaurant> getAll() {
        return (List<Restaurant>) restaurantRepository.getAll();
    }
}
