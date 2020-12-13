package edu.northeastern.cs5500.delivery.view;

import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
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
public final class RestaurantView_MembersInjector implements MembersInjector<RestaurantView> {
  private final Provider<JsonTransformer> jsonTransformerProvider;

  private final Provider<RestaurantController> restaurantControllerProvider;

  public RestaurantView_MembersInjector(Provider<JsonTransformer> jsonTransformerProvider,
      Provider<RestaurantController> restaurantControllerProvider) {
    this.jsonTransformerProvider = jsonTransformerProvider;
    this.restaurantControllerProvider = restaurantControllerProvider;
  }

  public static MembersInjector<RestaurantView> create(
      Provider<JsonTransformer> jsonTransformerProvider,
      Provider<RestaurantController> restaurantControllerProvider) {
    return new RestaurantView_MembersInjector(jsonTransformerProvider, restaurantControllerProvider);}

  @Override
  public void injectMembers(RestaurantView instance) {
    injectJsonTransformer(instance, jsonTransformerProvider.get());
    injectRestaurantController(instance, restaurantControllerProvider.get());
  }

  @InjectedFieldSignature("edu.northeastern.cs5500.delivery.view.RestaurantView.jsonTransformer")
  public static void injectJsonTransformer(RestaurantView instance,
      JsonTransformer jsonTransformer) {
    instance.jsonTransformer = jsonTransformer;
  }

  @InjectedFieldSignature("edu.northeastern.cs5500.delivery.view.RestaurantView.restaurantController")
  public static void injectRestaurantController(RestaurantView instance,
      RestaurantController restaurantController) {
    instance.restaurantController = restaurantController;
  }
}
