package edu.northeastern.cs5500.delivery.view;

import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
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
public final class DriverView_MembersInjector implements MembersInjector<DriverView> {
  private final Provider<JsonTransformer> jsonTransformerProvider;

  private final Provider<DriverController> driverControllerProvider;

  public DriverView_MembersInjector(Provider<JsonTransformer> jsonTransformerProvider,
      Provider<DriverController> driverControllerProvider) {
    this.jsonTransformerProvider = jsonTransformerProvider;
    this.driverControllerProvider = driverControllerProvider;
  }

  public static MembersInjector<DriverView> create(
      Provider<JsonTransformer> jsonTransformerProvider,
      Provider<DriverController> driverControllerProvider) {
    return new DriverView_MembersInjector(jsonTransformerProvider, driverControllerProvider);}

  @Override
  public void injectMembers(DriverView instance) {
    injectJsonTransformer(instance, jsonTransformerProvider.get());
    injectDriverController(instance, driverControllerProvider.get());
  }

  @InjectedFieldSignature("edu.northeastern.cs5500.delivery.view.DriverView.jsonTransformer")
  public static void injectJsonTransformer(DriverView instance, JsonTransformer jsonTransformer) {
    instance.jsonTransformer = jsonTransformer;
  }

  @InjectedFieldSignature("edu.northeastern.cs5500.delivery.view.DriverView.driverController")
  public static void injectDriverController(DriverView instance,
      DriverController driverController) {
    instance.driverController = driverController;
  }
}
