package edu.northeastern.cs5500.delivery.view;

import dagger.MembersInjector;
import dagger.internal.InjectedFieldSignature;
import edu.northeastern.cs5500.delivery.JsonTransformer;
import edu.northeastern.cs5500.delivery.controller.CustomerController;
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
public final class CustomerView_MembersInjector implements MembersInjector<CustomerView> {
  private final Provider<JsonTransformer> jsonTransformerProvider;

  private final Provider<CustomerController> customerControllerProvider;

  public CustomerView_MembersInjector(Provider<JsonTransformer> jsonTransformerProvider,
      Provider<CustomerController> customerControllerProvider) {
    this.jsonTransformerProvider = jsonTransformerProvider;
    this.customerControllerProvider = customerControllerProvider;
  }

  public static MembersInjector<CustomerView> create(
      Provider<JsonTransformer> jsonTransformerProvider,
      Provider<CustomerController> customerControllerProvider) {
    return new CustomerView_MembersInjector(jsonTransformerProvider, customerControllerProvider);}

  @Override
  public void injectMembers(CustomerView instance) {
    injectJsonTransformer(instance, jsonTransformerProvider.get());
    injectCustomerController(instance, customerControllerProvider.get());
  }

  @InjectedFieldSignature("edu.northeastern.cs5500.delivery.view.CustomerView.jsonTransformer")
  public static void injectJsonTransformer(CustomerView instance, JsonTransformer jsonTransformer) {
    instance.jsonTransformer = jsonTransformer;
  }

  @InjectedFieldSignature("edu.northeastern.cs5500.delivery.view.CustomerView.customerController")
  public static void injectCustomerController(CustomerView instance,
      CustomerController customerController) {
    instance.customerController = customerController;
  }
}
