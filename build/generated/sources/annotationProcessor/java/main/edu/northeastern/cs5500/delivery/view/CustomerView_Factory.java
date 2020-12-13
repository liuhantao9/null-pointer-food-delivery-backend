package edu.northeastern.cs5500.delivery.view;

import dagger.internal.Factory;
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
public final class CustomerView_Factory implements Factory<CustomerView> {
  private final Provider<JsonTransformer> jsonTransformerProvider;

  private final Provider<CustomerController> customerControllerProvider;

  public CustomerView_Factory(Provider<JsonTransformer> jsonTransformerProvider,
      Provider<CustomerController> customerControllerProvider) {
    this.jsonTransformerProvider = jsonTransformerProvider;
    this.customerControllerProvider = customerControllerProvider;
  }

  @Override
  public CustomerView get() {
    CustomerView instance = newInstance();
    CustomerView_MembersInjector.injectJsonTransformer(instance, jsonTransformerProvider.get());
    CustomerView_MembersInjector.injectCustomerController(instance, customerControllerProvider.get());
    return instance;
  }

  public static CustomerView_Factory create(Provider<JsonTransformer> jsonTransformerProvider,
      Provider<CustomerController> customerControllerProvider) {
    return new CustomerView_Factory(jsonTransformerProvider, customerControllerProvider);
  }

  public static CustomerView newInstance() {
    return new CustomerView();
  }
}
