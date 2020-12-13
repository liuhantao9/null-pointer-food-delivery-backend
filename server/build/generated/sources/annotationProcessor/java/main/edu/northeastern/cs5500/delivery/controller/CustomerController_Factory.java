package edu.northeastern.cs5500.delivery.controller;

import dagger.internal.Factory;
import edu.northeastern.cs5500.delivery.model.Customer;
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
public final class CustomerController_Factory implements Factory<CustomerController> {
  private final Provider<GenericRepository<Customer>> customeRepositoryProvider;

  public CustomerController_Factory(
      Provider<GenericRepository<Customer>> customeRepositoryProvider) {
    this.customeRepositoryProvider = customeRepositoryProvider;
  }

  @Override
  public CustomerController get() {
    return newInstance(customeRepositoryProvider.get());
  }

  public static CustomerController_Factory create(
      Provider<GenericRepository<Customer>> customeRepositoryProvider) {
    return new CustomerController_Factory(customeRepositoryProvider);
  }

  public static CustomerController newInstance(GenericRepository<Customer> customeRepository) {
    return new CustomerController(customeRepository);
  }
}
