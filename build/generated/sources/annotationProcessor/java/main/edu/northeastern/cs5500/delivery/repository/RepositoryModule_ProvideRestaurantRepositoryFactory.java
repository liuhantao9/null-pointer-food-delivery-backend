package edu.northeastern.cs5500.delivery.repository;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import edu.northeastern.cs5500.delivery.model.Restaurant;
import edu.northeastern.cs5500.delivery.service.MongoDBService;
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
public final class RepositoryModule_ProvideRestaurantRepositoryFactory implements Factory<GenericRepository<Restaurant>> {
  private final RepositoryModule module;

  private final Provider<MongoDBService> mongoDBServiceProvider;

  public RepositoryModule_ProvideRestaurantRepositoryFactory(RepositoryModule module,
      Provider<MongoDBService> mongoDBServiceProvider) {
    this.module = module;
    this.mongoDBServiceProvider = mongoDBServiceProvider;
  }

  @Override
  public GenericRepository<Restaurant> get() {
    return provideRestaurantRepository(module, mongoDBServiceProvider.get());
  }

  public static RepositoryModule_ProvideRestaurantRepositoryFactory create(RepositoryModule module,
      Provider<MongoDBService> mongoDBServiceProvider) {
    return new RepositoryModule_ProvideRestaurantRepositoryFactory(module, mongoDBServiceProvider);
  }

  public static GenericRepository<Restaurant> provideRestaurantRepository(RepositoryModule instance,
      MongoDBService mongoDBService) {
    return Preconditions.checkNotNull(instance.provideRestaurantRepository(mongoDBService), "Cannot return null from a non-@Nullable @Provides method");
  }
}
