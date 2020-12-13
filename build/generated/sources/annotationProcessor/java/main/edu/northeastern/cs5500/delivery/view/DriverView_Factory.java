package edu.northeastern.cs5500.delivery.view;

import dagger.internal.Factory;
import edu.northeastern.cs5500.delivery.JsonTransformer;
import edu.northeastern.cs5500.delivery.controller.DriverController;
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
public final class DriverView_Factory implements Factory<DriverView> {
  private final Provider<JsonTransformer> jsonTransformerProvider;

  private final Provider<DriverController> driverControllerProvider;

  public DriverView_Factory(Provider<JsonTransformer> jsonTransformerProvider,
      Provider<DriverController> driverControllerProvider) {
    this.jsonTransformerProvider = jsonTransformerProvider;
    this.driverControllerProvider = driverControllerProvider;
  }

  @Override
  public DriverView get() {
    DriverView instance = newInstance();
    DriverView_MembersInjector.injectJsonTransformer(instance, jsonTransformerProvider.get());
    DriverView_MembersInjector.injectDriverController(instance, driverControllerProvider.get());
    return instance;
  }

  public static DriverView_Factory create(Provider<JsonTransformer> jsonTransformerProvider,
      Provider<DriverController> driverControllerProvider) {
    return new DriverView_Factory(jsonTransformerProvider, driverControllerProvider);
  }

  public static DriverView newInstance() {
    return new DriverView();
  }
}
