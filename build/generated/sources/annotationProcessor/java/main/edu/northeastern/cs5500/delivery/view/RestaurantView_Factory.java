package edu.northeastern.cs5500.delivery.view;

import dagger.internal.Factory;
import edu.northeastern.cs5500.delivery.JsonTransformer;
import edu.northeastern.cs5500.delivery.controller.RestaurantController;
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
public final class RestaurantView_Factory implements Factory<RestaurantView> {
  private final Provider<JsonTransformer> jsonTransformerProvider;

  private final Provider<RestaurantController> restaurantControllerProvider;

  public RestaurantView_Factory(Provider<JsonTransformer> jsonTransformerProvider,
      Provider<RestaurantController> restaurantControllerProvider) {
    this.jsonTransformerProvider = jsonTransformerProvider;
    this.restaurantControllerProvider = restaurantControllerProvider;
  }

  @Override
  public RestaurantView get() {
    RestaurantView instance = newInstance();
    RestaurantView_MembersInjector.injectJsonTransformer(instance, jsonTransformerProvider.get());
    RestaurantView_MembersInjector.injectRestaurantController(instance, restaurantControllerProvider.get());
    return instance;
  }

  public static RestaurantView_Factory create(Provider<JsonTransformer> jsonTransformerProvider,
      Provider<RestaurantController> restaurantControllerProvider) {
    return new RestaurantView_Factory(jsonTransformerProvider, restaurantControllerProvider);
  }

  public static RestaurantView newInstance() {
    return new RestaurantView();
  }
}
