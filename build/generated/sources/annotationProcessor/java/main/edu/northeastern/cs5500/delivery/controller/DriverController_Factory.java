package edu.northeastern.cs5500.delivery.controller;

import dagger.internal.Factory;
import edu.northeastern.cs5500.delivery.model.Driver;
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
public final class DriverController_Factory implements Factory<DriverController> {
  private final Provider<GenericRepository<Driver>> driverRepositoryProvider;

  public DriverController_Factory(Provider<GenericRepository<Driver>> driverRepositoryProvider) {
    this.driverRepositoryProvider = driverRepositoryProvider;
  }

  @Override
  public DriverController get() {
    return newInstance(driverRepositoryProvider.get());
  }

  public static DriverController_Factory create(
      Provider<GenericRepository<Driver>> driverRepositoryProvider) {
    return new DriverController_Factory(driverRepositoryProvider);
  }

  public static DriverController newInstance(GenericRepository<Driver> driverRepository) {
    return new DriverController(driverRepository);
  }
}
