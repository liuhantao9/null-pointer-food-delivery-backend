package edu.northeastern.cs5500.delivery.controller;

import dagger.internal.Factory;
import edu.northeastern.cs5500.delivery.model.Restaurant;
import edu.northeastern.cs5500.delivery.repository.GenericRepository;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class RestaurantController_Factory implements Factory<RestaurantController> {
  private final Provider<GenericRepository<Restaurant>> restaurantRepositoryProvider;

  public RestaurantController_Factory(
      Provider<GenericRepository<Restaurant>> restaurantRepositoryProvider) {
    this.restaurantRepositoryProvider = restaurantRepositoryProvider;
  }

  @Override
  public RestaurantController get() {
    return newInstance(restaurantRepositoryProvider.get());
  }

  public static RestaurantController_Factory create(
      Provider<GenericRepository<Restaurant>> restaurantRepositoryProvider) {
    return new RestaurantController_Factory(restaurantRepositoryProvider);
  }

  public static RestaurantController newInstance(
      GenericRepository<Restaurant> restaurantRepository) {
    return new RestaurantController(restaurantRepository);
  }
}
