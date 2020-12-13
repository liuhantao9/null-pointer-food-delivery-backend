package edu.northeastern.cs5500.delivery.view;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class ViewModule_ProvideRestaurantViewFactory implements Factory<View> {
  private final ViewModule module;

  private final Provider<RestaurantView> restaurantViewProvider;

  public ViewModule_ProvideRestaurantViewFactory(ViewModule module,
      Provider<RestaurantView> restaurantViewProvider) {
    this.module = module;
    this.restaurantViewProvider = restaurantViewProvider;
  }

  @Override
  public View get() {
    return provideRestaurantView(module, restaurantViewProvider.get());
  }

  public static ViewModule_ProvideRestaurantViewFactory create(ViewModule module,
      Provider<RestaurantView> restaurantViewProvider) {
    return new ViewModule_ProvideRestaurantViewFactory(module, restaurantViewProvider);
  }

  public static View provideRestaurantView(ViewModule instance, RestaurantView restaurantView) {
    return Preconditions.checkNotNull(instance.provideRestaurantView(restaurantView), "Cannot return null from a non-@Nullable @Provides method");
  }
}
